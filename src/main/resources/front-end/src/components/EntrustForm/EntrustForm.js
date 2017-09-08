/**
 * Created by a297 on 17/9/8.
 */
import React from 'react';
import { connect } from 'dva';
import { Form, Input, Button } from 'antd';
import CodeInput from '../../components/Input/CodeInput';
import PriceInput from '../../components/Input/PriceInput';

const FormItem = Form.Item;

class EntrustForm extends React.Component {
  render() {
    return (
      <Form layout="inline" onSubmit={this.handleSubmit}>
        <FormItem label="代码">
          <CodeInput />
        </FormItem>
        <br />
        <FormItem label="名称">50ETF9月购2800</FormItem>
        <br />
        <FormItem label="价格">
          <PriceInput />
        </FormItem>
        <br />
        <FormItem label="可用余额">8888.99</FormItem>
        <br />
        <FormItem label="最大可买">56000 手</FormItem>
        <br />
        <FormItem label="买入数量">
          <span>
            <Input addonAfter="手" />
          </span>
        </FormItem>
        <br />
        <FormItem>
          <Button
            style={{ backgroundColor: '#865454', color: '#fff', width: 350, marginTop: 10 }}
            htmlType="submit"
          >买入
          </Button>
        </FormItem>
      </Form>
    );
  }
}
export default connect()(EntrustForm);
