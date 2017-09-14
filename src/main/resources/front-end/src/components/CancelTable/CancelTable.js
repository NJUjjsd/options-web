/**
 * Created by a297 on 17/9/8.
 */
import React from 'react';
import { connect } from 'dva';
import { Table } from 'antd';

class CancelTable extends React.Component {
  render() {
    const columns = [{
      title: '代码',
      dataIndex: 'code',
    }, {
      title: '名称',
      dataIndex: 'optionName',
    }, {
      title: '交易类型',
      dataIndex: 'isBuy',
    }, {
      title: '价格',
      dataIndex: 'price',
    }, {
      title: '数量',
      dataIndex: 'optionNum',
    }];
    const data = this.props.orders;
    data.map((v, i) => {
      return Object.assign(v, { key: i });
    });

    const rowSelection = {
      onChange: (selectedRowKeys, selectedRows) => {
        console.log(`selectedRowKeys: ${selectedRowKeys}`, 'selectedRows: ', selectedRows);
      },
      getCheckboxProps: record => ({
        disabled: record.name === 'Disabled User',    // Column configuration not to be checked
      }),
    };
    return (
      <Table
        columns={columns}
        dataSource={data}
        rowSelection={rowSelection}
        bordered
        rowKey={record => record.key}
      />
    );
  }
}
function mapStateToProps(state) {
  const { orders } = state.userInvest;
  // const orders = [{
  //   code: '510050',
  //   optionName: '上证50ETF',
  //   isBuy: '买入',
  //   optionNum: '200000',
  //   price: '3.33',
  // }, {
  //   code: '510050',
  //   optionName: '上证50ETF',
  //   isBuy: '卖出',
  //   optionNum: '200000',
  //   price: '2.22',
  // }, {
  //   code: '510050',
  //   optionName: '上证50ETF',
  //   isBuy: '买入',
  //   optionNum: '200000',
  //   price: '1.11',
  // }];
  return { orders };
}
export default connect(mapStateToProps)(CancelTable);
