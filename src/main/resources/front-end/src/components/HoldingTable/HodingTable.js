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
    return (
      <Table
        columns={columns}
        dataSource={this.state.holding}
        bordered
      />
    );
  }
}
function mapStateToProps(state) {
  const { holding } = state.userInvest;
  return { holding };
}
export default connect(mapStateToProps)(HoldingTable);
