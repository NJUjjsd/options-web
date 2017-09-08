/**
 * Created by a297 on 17/9/8.
 */
import React from 'react';
import { connect } from 'dva';
import { Table } from 'antd';

class CancelTable extends React.Component {
  render() {
    const columns = [{
      title: '委托序号',
      dataIndex: 'entrustID',
    }, {
      title: '代码',
      dataIndex: 'code',
    }, {
      title: '名称',
      dataIndex: 'name',
    }, {
      title: '交易类型',
      dataIndex: 'type',
    }, {
      title: '价格',
      dataIndex: 'price',
    }];
    const data = [{
      key: '1',
      entrustID: '00',
      code: '510050C1709M02250',
      name: '50ETF购9月2800',
      type: '卖出',
      price: '2.75',
    }, {
      key: '2',
      entrustID: '01',
      code: '510050',
      name: '50ETF',
      type: '买入',
      price: '2.23',
    }];
    return (
      <Table
        columns={columns}
        dataSource={data}
        bordered
        rowKey={record => record.key}
      />
    );
  }
}
export default connect()(CancelTable);
