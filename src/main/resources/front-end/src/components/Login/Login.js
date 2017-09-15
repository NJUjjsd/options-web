/**
 * Created by john on 2017/9/8.
 */
import React from 'react';
import { Form, Input, Button, Icon, Modal, Spin } from 'antd';
import { routerRedux } from 'dva/router';
import { connect } from 'dva';
import styles from './Login.css';

const FormItem = Form.Item;

class Login extends React.Component {

  handleSubmit = (e) => {
    e.preventDefault();
    this.props.form.validateFields((err, values) => {
      if (!err) {
        const account = {
          email: values.email,
          password: values.password,
        };
        console.log(account);
        this.props.dispatch({
          type: 'users/login',
          payload: { account },
        });
      }
    });
  };
  handleOk = () => {
    this.props.dispatch({
      type: 'users/clearShowModal',
    });
    this.props.dispatch({
      type: 'users/clearLoginResult',
    });
  };
  toRegister = () => {
    this.props.dispatch(routerRedux.push({
      pathname: '/users/register',
    }));
  };
  render() {
    const { getFieldDecorator } = this.props.form;
    const { loginResult, loading, showModal } = this.props;
    return (
      <div>
        <div className={styles.beginRegister}>
          没有账号？
          <span
            style={{ color: '#5EA9E8', cursor: 'pointer' }}
            onClick={this.toRegister.bind(this)}
          >
            立即注册
            <Icon type="arrow-right" />
          </span>
        </div>
        <Form onSubmit={this.handleSubmit.bind(this)} className={styles.loginForm}>
          <FormItem>
            {getFieldDecorator('email', {
              rules: [{ type: 'email', message: '邮箱格式不正确' },
                { required: true, message: '请输入您的邮箱' }],
            })(
              <Input className={styles.item} placeholder="邮箱" />,
            )}
          </FormItem>
          <FormItem>
            {getFieldDecorator('password', {
              rules: [{ required: true, message: '请输入您的密码' }],
            })(
              <Input className={styles.item} type="password" placeholder="密码" />,
            )}
          </FormItem>
          <FormItem>
            <Button type="primary" htmlType="submit" className={styles.loginFormButton}>
              登录
            </Button>
          </FormItem>
        </Form>
        <Modal
          visible={showModal}
          style={{ top: 280 }}
          footer={null}
          onCancel={this.handleOk.bind(this)}
          closable={!loading}
        >
          <Spin spinning={loading} tip="正在登录，请稍后...">
            <div style={{ height: 50 }} />
            <div style={{ fontSize: 16, textAlign: 'center' }}>{loginResult.message}</div>
            <div style={{ height: 50 }} />
          </Spin>
        </Modal>
      </div>
    );
  }
}

const LoginForm = Form.create()(Login);

function mapStateToProps(state) {
  const { loginResult, showModal } = state.users;
  return {
    loading: state.loading.models.users,
    loginResult,
    showModal,
  };
}

export default connect(mapStateToProps)(LoginForm);

