/**
 * Created by john on 2017/9/8.
 */
import React from 'react';
import { Form, Input, Button, Checkbox } from 'antd';
import { connect } from 'dva';
import styles from './Login.css';


const FormItem = Form.Item;

class Login extends React.Component {
  handleSubmit = (e) => {
    e.preventDefault();
    this.props.form.validateFields((err, values) => {
      if (!err) {
        console.log('Received values of form: ', values);
      }
    });
  };
  render() {
    const { getFieldDecorator } = this.props.form;
    return (
      <div>
        <div className={styles.beginLogin}>开始登录</div>
        <Form onSubmit={this.handleSubmit} className={styles.loginForm}>
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
          <div>
            <span>
              <Checkbox style={{ fontSize: 18 }}>记住密码</Checkbox>
            </span>
            <span style={{ float: 'right', fontSize: 18 }}>忘记密码</span>
          </div>
          <FormItem>
            <Button type="primary" htmlType="submit" className={styles.loginFormButton}>
              开始登录
            </Button>
          </FormItem>
          <div className={styles.beginLogin}>没有账号？<span>立即注册--</span></div>
        </Form>
      </div>
    );
  }
}

const LoginForm = Form.create()(Login);

export default connect()(LoginForm);

