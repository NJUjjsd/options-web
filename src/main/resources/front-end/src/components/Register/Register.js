/**
 * Created by john on 2017/9/8.
 */
import React from 'react';
import { Form, Input, Button, Icon } from 'antd';
import { routerRedux } from 'dva/router';
import { connect } from 'dva';
import styles from './Register.less';
import { loginPath } from '../../constant';


const FormItem = Form.Item;

class Register extends React.Component {
  handleSubmit = (e) => {
    e.preventDefault();
    this.props.form.validateFields((err, values) => {
      if (!err) {
        console.log('Received values of form: ', values);
      }
    });
  };
  toLogin = () => {
    this.props.dispatch(routerRedux.push({
      pathname: '/users/login',
      query: {
        path: loginPath,
      },
    }));
  };
  render() {
    const { getFieldDecorator } = this.props.form;
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
              rules: [{ required: true, message: '请输入您的密码' }],
            })(
              <Input className={styles.item} type="password" placeholder="设置密码" />,
            )}
          </FormItem>
          <FormItem>
            {getFieldDecorator('confirmPassword', {
              rules: [{ required: true, message: '请确认您的密码' }],
            })(
              <Input className={styles.item} type="password" placeholder="确认密码" />,
            )}
          </FormItem>
          <FormItem>
            <Button type="primary" htmlType="submit" className={styles.registerFormButton}>
              完成注册
            </Button>
          </FormItem>
        </Form>
      </div>
    );
  }
}

const RegisterForm = Form.create()(Register);

export default connect()(RegisterForm);

