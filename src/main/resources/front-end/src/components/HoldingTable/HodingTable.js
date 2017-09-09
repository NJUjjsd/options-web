/**
 * Created by a297 on 17/9/8.
 */
import React from 'react';
import { connect } from 'dva';
import { Table } from 'antd';

class HoldingTable extends React.Component {
  state = {
    holding: [],
  }
  componentWillReceiveProps(nextProps) {
    if (nextProps.holding !== undefined) {
      this.setState({
        holding: nextProps.holding,
      });
    }
  }
  render() {
    const columns = [{
      title: '代码',
      dataIndex: 'code',
    }, {
      title: ' 名称',
      dataIndex: 'name',
    }, {
      title: '数量',
      dataIndex: 'number',
    }, {
      title: '可卖数量',
      dataIndex: 'numberToSell',
    }, {
      title: '当前价',
      dataIndex: 'currentPrice',
    }, {
      title: '涨跌幅',
      dataIndex: 'fluctuation',
    }, {
      title: '盈亏',
      dataIndex: 'profitAndLoss',
    }];
    // const data = [{
    //   key: '1',
    //   code: '510050C1709M02250',
    //   name: '50ETF购9月2800',
    //   number: '1000',
    //   numberToSell: '200',
    //   currentPrice: '2.45',
    //   fluctuation: '+0.34',
    //   profitAndLoss: '+10000',
    // }, {
    //   key: '2',
    //   code: '510050',
    //   name: '50ETF',
    //   number: '30000',
    //   numberToSell: '10000',
    //   currentPrice: '2.33',
    //   fluctuation: '-0.34',
    //   profitAndLoss: '-9900',
    // }];
    return (
      <Table
        columns={columns}
        dataSource={this.state.holding}
        bordered
      />
    );
  }
}
export default connect()(HoldingTable);
