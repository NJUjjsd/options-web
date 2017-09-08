/**
 * Created by a297 on 17/9/8.
 */
import React from 'react';
import { connect } from 'dva';
import { Table } from 'antd';

class InformTable extends React.Component {
  render() {
    const columns = [{
      title: '交易代码',
      dataIndex: 'code',
    }, {
      title: '合约名称',
      dataIndex: 'name',
    }, {
      title: '交易类型',
      dataIndex: 'type',
    }, {
      title: '可交易数量',
      dataIndex: 'numberToDeal',
    }, {
      title: '总获利',
      dataIndex: 'totalProfit',
      render(value, row, index) {
        const obj = {
          children: value,
          props: {},
        };
        if (index === 0) {
          obj.props.rowSpan = 3;
        } else {
          obj.props.rowSpan = 0;
        }
        return obj;
      },
    }];

    const data = [{
      key: '1',
      code: '510050',
      name: '上证50ETF',
      type: '买入',
      numberToDeal: '3000',
      totalProfit: '23333',
    }, {
      key: '2',
      code: '510050C1709M02250',
      name: '50ETF购9月2800',
      type: '卖出',
      numberToDeal: '100',
      totalProfit: '23333',
    }, {
      key: '3',
      code: '510050C1709M02250',
      name: '50ETF购9月2800',
      type: '买入',
      numberToDeal: '100',
      totalProfit: '23333',
    }];

    return (
      <Table
        columns={columns}
        dataSource={data}
        bordered
        pagination="false"
      />
    );
  }
}
export default connect()(InformTable);
