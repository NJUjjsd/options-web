/**
 * Created by a297 on 17/8/17.
 */
import React from 'react';
import { connect } from 'dva';
import { Table } from 'antd';

function OptionForm() {
  const header = ['认购', '08月份', '认沽'];
  const columns = [{
    title: '合约交易代码',
    dataIndex: 'UpTradingCode',
  }, {
    title: '当前价',
    dataIndex: 'UpCurrentPrice',
  }, {
    title: '涨跌幅',
    dataIndex: 'UpFluctuation',
  }, {
    title: '前结价',
    dataIndex: 'UpPreClosingPrice',
  }, {
    title: '行权价',
    dataIndex: 'UpExercisePrice',
  }, {
    title: '合约交易代码',
    dataIndex: 'DownTradingCode',
  }, {
    title: '当前价',
    dataIndex: 'DownCurrentPrice',
  }, {
    title: '涨跌幅',
    dataIndex: 'DownFluctuation',
  }, {
    title: '前结价',
    dataIndex: 'DownPreClosingPrice',
  }];

  const data = [{
    key: '1',
    UpTradingCode: '510050C1708M02450',
    UpCurrentPrice: 0.2017,
    UpFluctuation: '6.16%',
    UpPreClosingPrice: 0.1900,
    UpExercisePrice: 2.450,
    DownTradingCode: '510050P1708M02450',
    DownCurrentPrice: 0.0002,
    DownFluctuation: '-33.33%',
    DownPreClosingPrice: 0.0003,
  }, {
    key: '2',
    UpTradingCode: '510050C1708M02450',
    UpCurrentPrice: 0.2017,
    UpFluctuation: '6.16%',
    UpPreClosingPrice: 0.1900,
    UpExercisePrice: 2.450,
    DownTradingCode: '510050P1708M02450',
    DownCurrentPrice: 0.0002,
    DownFluctuation: '-33.33%',
    DownPreClosingPrice: 0.0003,
  }];

  return (<Table
    dataSource={data}
    columns={columns}
    bordered
    title={() => header}
    footer={() => 'Footer'}
    style={{ textAlign: 'center' }}
  />);
}

export default connect()(OptionForm);

