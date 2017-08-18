/**
 * Created by a297 on 17/8/17.
 */
import React from 'react';
import { connect } from 'dva';
import { Table } from 'antd';

function ETFInfoForm() {
  const column = [{
    title: '代码',
    dataIndex: 'code',
  }, {
    title: '名称',
    dataIndex: 'name',
  }, {
    title: '当前价',
    dataIndex: 'curPrice',
  }, {
    title: '涨跌',
    dataIndex: 'fluPrice',
  }, {
    title: '涨跌幅',
    dataIndex: 'fluPercent',
  }, {
    title: '振幅',
    dataIndex: 'amplitude',
  }, {
    title: '成交量(手)',
  }, {
    title: '成交额(万元)',
  }];

  const data = [{
    key: '1',
    code: 510050,
    name: '上证50ETF',
  }];

  return (<Table dataSource={data} columns={column} />);
}

export default connect()(ETFInfoForm);
