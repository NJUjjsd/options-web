/**
 * Created by a297 on 17/8/17.
 */
import React from 'react';
import { connect } from 'dva';
import { Table } from 'antd';

function ETFInfoForm({ ETFBasicInfo }) {
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
    dataIndex: 'turnOver',
  }, {
    title: '成交额(万元)',
    dataIndex: 'transaction',
  }];

  return (
    <Table
      dataSource={ETFBasicInfo}
      columns={column}
      bordered
      bodyStyle={{ fontSize: '14px' }}
    />);
}

function mapStateToProps(state) {
  const { ETFBasicInfo } = state.market;
  return { ETFBasicInfo };
}

export default connect(mapStateToProps)(ETFInfoForm);
