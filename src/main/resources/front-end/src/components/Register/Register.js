/**
 * Created by john on 2017/9/8.
 */
import React from 'react';
import { Form, Input, Button, Icon } from 'antd';
import { routerRedux } from 'dva/router';
import { connect } from 'dva';
import styles from './Register.less';
import { loginPath } from '../../constant';
import { Encrypt } from '../../utils/aes';


const FormItem = Form.Item;

class Register extends React.Component {
  state = {
    confirmDirty: false,
    autoCompleteResult: [],
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
          email: Encrypt(values.email),
          userName: Encrypt(values.userName),
          password: Encrypt(values.password),
        };
        this.props.dispatch({
          type: 'users/signUp',
          payload: { account },
        });
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

