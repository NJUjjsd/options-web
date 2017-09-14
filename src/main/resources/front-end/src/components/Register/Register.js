/**
 * Created by john on 2017/9/8.
 */
import React from 'react';
import { Form, Input, Button, Icon, Modal, Spin } from 'antd';
import { routerRedux } from 'dva/router';
import { connect } from 'dva';
import styles from './Register.less';

const FormItem = Form.Item;

class Register extends React.Component {
  state = {
    confirmDirty: false,
    showModal: false,
  };
  handleConfirmBlur = (e) => {
    const value = e.target.value;
    this.setState({ confirmDirty: this.state.confirmDirty || !!value });
  };
  checkPassword = (rule, value, callback) => {
    const form = this.props.form;
    if (value && value !== form.getFieldValue('password')) {
      callback('两次密码输入不一致');
    } else {
      callback();
    }
  };
  checkConfirm = (rule, value, callback) => {
    const form = this.props.form;
    if (value && this.state.confirmDirty) {
      form.validateFields(['confirmPassword'], { force: true });
    }
    callback();
  };

  handleSubmit = (e) => {
    e.preventDefault();
    this.props.form.validateFields((err, values) => {
      if (!err) {
        const account = {
          email: values.email,
          userName: values.userName,
          password: values.password,
        };
        this.props.dispatch({
          type: 'users/signUp',
          payload: { account },
        });
        this.setState({
          showModal: true,
        });
      }
    });
  };
  handleOk = () => {
    this.setState({
      showModal: false,
    });
    this.props.dispatch({
      type: 'users/clearSignUpResult',
    });
  };
  toLogin = () => {
    this.props.dispatch(routerRedux.push({
      pathname: '/users/login',
    }));
  };
  render() {
    const { getFieldDecorator } = this.props.form;
    const { loading, signUpResult } = this.props;
    return (
      <div>
        <div className={styles.beginLogin}>
          已有账号？
          <span
            style={{ color: '#5EA9E8', cursor: 'pointer' }}
            onClick={this.toLogin.bind(this)}
          >
            立即登录
            <Icon type="arrow-right" />
          </span>
        </div>
        <Form onSubmit={this.handleSubmit} className={styles.registerForm}>
          <FormItem>
            {getFieldDecorator('userName', {
              rules: [{ required: true, message: '请输入您的昵称' }],
            })(
              <Input className={styles.item} placeholder="昵称" />,
            )}
          </FormItem>
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
              rules: [
                { required: true, message: '请输入您的密码' },
                { validator: this.checkConfirm }],
            })(
              <Input className={styles.item} type="password" placeholder="设置密码" />,
            )}
          </FormItem>
          <FormItem>
            {getFieldDecorator('confirmPassword', {
              rules: [
                { required: true, message: '请确认您的密码' },
                { validator: this.checkPassword }],
            })(
              <Input className={styles.item} type="password" placeholder="确认密码" onBlur={this.handleConfirmBlur} />,
            )}
          </FormItem>
          <FormItem>
            <Button
              type="primary"
              htmlType="submit"
              className={styles.registerFormButton}
            >
              注册
            </Button>
          </FormItem>
        </Form>
        <Modal
          visible={this.state.showModal}
          style={{ top: 280 }}
          footer={null}
          onCancel={this.handleOk.bind(this)}
          closable={!loading}
        >
          <Spin spinning={loading} tip="正在发送激活链接，请稍后...">
            <div style={{ height: 50 }} />
            <div style={{ fontSize: 16, textAlign: 'center' }}>{signUpResult.message}</div>
            <div style={{ height: 50 }} />
          </Spin>
        </Modal>
      </div>
    );
  }
}

const RegisterForm = Form.create()(Register);

function mapStateToProps(state) {
  const { signUpResult } = state.users;
  return {
    loading: state.loading.models.users,
    signUpResult,
  };
}

export default connect(mapStateToProps)(RegisterForm);

