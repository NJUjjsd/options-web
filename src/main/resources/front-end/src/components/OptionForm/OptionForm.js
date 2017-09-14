/**
 * Created by a297 on 17/8/17.
 */
import React from 'react';
import { connect } from 'dva';
import { Table } from 'antd';

const { Column, ColumnGroup } = Table;

class OptionForm extends React.Component {
  state = {
    dueMonths: [],
    contactInfo: [],
    selectedMonthIndex: 0,
  };
  componentWillReceiveProps(nextProps) {
    if (nextProps.dueMonths.length !== 0) {
      this.setState({
        dueMonths: nextProps.dueMonths,
        contactInfo: nextProps.contactInfo,
        selectedMonthIndex: nextProps.selectedMonthIndex,
      });
    }
  }

  render() {
    let dueMonth = '月份';
    let contacts = [];
    if (this.state.dueMonths.length !== 0) {
      dueMonth = `${this.state.dueMonths[this.state.selectedMonthIndex].split('-')[1]}月份`;
      // console.log('this is contact info');
      // console.log(this.state.contactInfo);
      contacts = this.state.contactInfo[this.state.selectedMonthIndex];
    }
    contacts.map((v, i) => {
      return Object.assign(v, { key: i });
    });
    return (
      <Table
        dataSource={contacts}
        bordered
        footer={() => ''}
        bodyStyle={{ fontSize: '14px' }}
        rowKey={record => record.key}
        loading={this.props.loading}
      >
        <ColumnGroup title="认购">
          <Column title="合约交易代码" dataIndex="upTradingCode" key="upTradingCode" />
          <Column title="当前价" dataIndex="upCurrentPrice" key="upCurrentPrice" />
          <Column title="涨跌幅" dataIndex="upFluctuation" key="upFluctuation" />
          <Column title="前结价" dataIndex="upPreClosingPrice" key="upPreClosingPrice" />
        </ColumnGroup>
        <ColumnGroup title={dueMonth}>
          <Column title="行权价" dataIndex="upExercisePrice" key="upExercisePrice" />
        </ColumnGroup>
        <ColumnGroup title="认购">
          <Column title="合约交易代码" dataIndex="downTradingCode" key="downTradingCode" />
          <Column title="当前价" dataIndex="downCurrentPrice" key="downCurrentPrice" />
          <Column title="涨跌幅" dataIndex="downFluctuation" key="downFluctuation" />
          <Column title="前结价" dataIndex="downPreClosingPrice" key="downPreClosingPrice" />
        </ColumnGroup>
      </Table>
    );
  }
}
function mapStateToProps(state) {
  return { loading: state.loading.models.market };
}
export default connect(mapStateToProps)(OptionForm);

