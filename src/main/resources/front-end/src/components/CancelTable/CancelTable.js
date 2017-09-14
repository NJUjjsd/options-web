/**
 * Created by a297 on 17/9/8.
 */
import React from 'react';
import { connect } from 'dva';
import { Table, Button } from 'antd';

class CancelTable extends React.Component {
  state = {
    data: [],
    selectedRowKeys: [],
    loading: false,
  };

  componentWillReceiveProps(nextProps) {
    const dataSource = nextProps.userEntrust;
    dataSource.map((v, i) => {
      const kind = v.isBuy ? '买入' : '卖出';
      return Object.assign(v, { key: i, isBuy: kind });
    });
    this.setState({
      data: dataSource,
    });
  }
  onSelectChange = (selectedRowKeys) => {
    this.setState({ selectedRowKeys });
  };
  getNewData = () => {
    console.log('get data');
    this.props.dispatch({
      type: 'userInvest/fetchUserEntrust',
      payload: '',
    });
  };
  handleCancel = () => {
    this.setState({ loading: true, data: [] });
    // ajax request after empty completing
    setTimeout(() => {
      this.setState({
        selectedRowKeys: [],
        loading: false,
      });
    }, 2000);

    const keys = this.state.selectedRowKeys;
    const list = [];
    keys.map((v) => {
      return list.push(this.props.userEntrust[v]);
    });
    this.props.dispatch({
      type: 'userInvest/cancelEntrust',
      payload: list,
    });

    setTimeout(this.getNewData, 2000);
  };

  render() {
    const columns = [{
      title: '委托序号',
      dataIndex: 'id',
    }, {
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

    const { loading, selectedRowKeys } = this.state;
    const rowSelection = {
      selectedRowKeys,
      onChange: this.onSelectChange,
    };

    return (
      <div>
        <Button
          style={{ marginTop: 20, marginBottom: 10, fontSize: 14 }}
          onClick={this.handleCancel}
          loading={loading}
        >撤单</Button>
        <Table
          columns={columns}
          dataSource={this.state.data}
          rowSelection={rowSelection}
          bordered
          rowKey={record => record.key}
        />
      </div>
    );
  }
}
function mapStateToProps(state) {
  const { userEntrust } = state.userInvest;
  return { userEntrust };
}
export default connect(mapStateToProps)(CancelTable);
