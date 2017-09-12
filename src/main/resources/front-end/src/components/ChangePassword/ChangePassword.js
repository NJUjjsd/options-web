/**
 * Created by john on 2017/9/10.
 */
import React from 'react';
import { Form, Input, Button } from 'antd';
import { connect } from 'dva';
import styles from './ChangePassword.css';


const FormItem = Form.Item;

class ChangePassword extends React.Component {
  state = {
    confirmDirty: false,
  };
  handleSubmit = (e) => {
    e.preventDefault();
    this.props.form.validateFields((err, values) => {
      if (!err) {
        console.log('Received values of form: ', values);
      }
    });
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
              rules: [
                { required: true, message: '请输入新密码' },
                { validator: this.checkConfirm }],
            })(
              <Input className={styles.item} type="password" placeholder="新密码" />,
            )}
          </FormItem>
          <FormItem>
            {getFieldDecorator('confirmPassword', {
              rules: [
                { required: true, message: '请确认新密码' },
                { validator: this.checkPassword }],
            })(
              <Input className={styles.item} type="password" placeholder="确认新密码" onBlur={this.handleConfirmBlur} />,
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

