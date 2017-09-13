/**
 * Created by john on 2017/9/8.
 */
import React from 'react';
import { Form, Input, Button, Checkbox, Icon } from 'antd';
import { routerRedux } from 'dva/router';
import { connect } from 'dva';
import styles from './Login.css';
import { Encrypt } from '../../utils/aes';


const FormItem = Form.Item;

class Login extends React.Component {
  handleSubmit = (e) => {
    e.preventDefault();
    this.props.form.validateFields((err, values) => {
      if (!err) {
        const account = {
          email: Encrypt(values.email),
          password: Encrypt(values.password),
        };
        this.props.dispatch({
          type: 'users/login',
          payload: { account },
        });
      }
    });
  };
  toRegister = () => {
    this.props.dispatch(routerRedux.push({
      pathname: '/users/register',
    }));
  };
  render() {
    const { getFieldDecorator } = this.props.form;
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
          <div className={styles.passwordIssue}>
            <span>
              <Checkbox style={{ fontSize: 18 }}>记住密码</Checkbox>
            </span>
            <span style={{ float: 'right', fontSize: 18, color: '#5EA9E8', cursor: 'pointer' }}>忘记密码</span>
          </div>
          <FormItem>
            <Button type="primary" htmlType="submit" className={styles.loginFormButton}>
              开始登录
            </Button>
          </FormItem>
        </Form>
      </div>
    );
  }
}

const LoginForm = Form.create()(Login);

export default connect()(LoginForm);

