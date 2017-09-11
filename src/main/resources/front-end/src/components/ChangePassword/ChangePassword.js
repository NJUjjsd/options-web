/**
 * Created by john on 2017/9/10.
 */
import React from 'react';
import { Form, Input, Button } from 'antd';
import { connect } from 'dva';
import styles from './ChangePassword.css';


const FormItem = Form.Item;

class ChangePassword extends React.Component {
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
        <Form onSubmit={this.handleSubmit} className={styles.changePasswordForm}>
          <FormItem>
            {getFieldDecorator('prePassword', {
              rules: [{ required: true, message: '请输入原密码' }],
            })(
              <Input className={styles.item} type="password" placeholder="原密码" />,
            )}
          </FormItem>
          <FormItem>
            {getFieldDecorator('password', {
              rules: [{ required: true, message: '请输入新密码' }],
            })(
              <Input className={styles.item} type="password" placeholder="新密码" />,
            )}
          </FormItem>
          <FormItem>
            {getFieldDecorator('confirmPassword', {
              rules: [{ required: true, message: '请确认新密码' }],
            })(
              <Input className={styles.item} type="password" placeholder="确认新密码" />,
            )}
          </FormItem>
          <FormItem>
            <Button type="primary" htmlType="submit" className={styles.changePasswordFormButton}>
              修改密码
            </Button>
          </FormItem>
        </Form>
      </div>
    );
  }
}

const ChangePasswordForm = Form.create()(ChangePassword);

export default connect()(ChangePasswordForm);

