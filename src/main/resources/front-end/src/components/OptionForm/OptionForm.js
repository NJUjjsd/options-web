/**
 * Created by a297 on 17/8/17.
 */
import React from 'react';
import { connect } from 'dva';
import { Table } from 'antd';

const { Column, ColumnGroup } = Table;

function OptionForm({ dueMonthList, optionFormData, selectedMonthIndex }) {
  const transactionMonth = `${dueMonthList[selectedMonthIndex].split('-')[1]}月份`;

  return (<Table
    dataSource={optionFormData}
    bordered
    footer={() => ''}
    style={{ textAlign: 'center' }}
    bodyStyle={{ fontSize: '14px' }}
  >
    <ColumnGroup title="认购">
      <Column title="合约交易代码" dataIndex="UpTradingCode" key="UpTradingCode" />
      <Column title="当前价" dataIndex="UpCurrentPrice" key="UpCurrentPrice" />
      <Column title="涨跌幅" dataIndex="UpFluctuation" key="UpFluctuation" />
      <Column title="前结价" dataIndex="UpPreClosingPrice" key="UpPreClosingPrice" />
    </ColumnGroup>
    <ColumnGroup title={transactionMonth}>
      <Column title="行权价" dataIndex="UpExercisePrice" key="UpExercisePrice" />
    </ColumnGroup>
    <ColumnGroup title="认购">
      <Column title="合约交易代码" dataIndex="DownTradingCode" key="DownTradingCode" />
      <Column title="当前价" dataIndex="DownCurrentPrice" key="DownCurrentPrice" />
      <Column title="涨跌幅" dataIndex="DownFluctuation" key="DownFluctuation" />
      <Column title="前结价" dataIndex="DownPreClosingPrice" key="DownPreClosingPrice" />
    </ColumnGroup>
  </Table>);
}

function mapStateToProps(state) {
  const { dueMonthList, optionFormData, selectedMonthIndex } = state.market;
  return { dueMonthList, optionFormData, selectedMonthIndex };
}

export default connect(mapStateToProps)(OptionForm);

