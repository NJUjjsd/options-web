/**
 * Created by a297 on 17/9/8.
 */
import React from 'react';
import { connect } from 'dva';
import { Table, Button, notification } from 'antd';

function Notification({ index, content, dispatch }) {
  const onClose = () => {
    notification.close(index);
  };
  const onEntrust = () => {
    dispatch({
      type: 'userInvest/combinationEntrust',
      payload: content,
    });
    notification.close(index);
  };
  const columns = [{
    title: '代码',
    dataIndex: 'code',
  }, {
    title: '名称',
    dataIndex: 'name',
  }, {
    title: '类型',
    dataIndex: 'type',
  }, {
    title: '价格',
    dataIndex: 'price',
  }, {
    title: '数量',
    dataIndex: 'numberToDeal',
  }, {
    title: '总获利',
    dataIndex: 'totalProfit',
    render(value, row, i) {
      const obj = {
        children: value,
        props: {},
      };
      if (i === 0) {
        obj.props.rowSpan = 3;
      } else {
        obj.props.rowSpan = 0;
      }
      return obj;
    },
  }];
  /**
   *  code: '510050',
   optionName: '上证50ETF',
   price: '2.3',
   optionNum: '130000',
   isBuy: true,
   * @type {Array}
   */
  const data = [];
  data[0] = {
    code: content.code,
    name: content.optionName,
    type: content.isBuy ? '买入' : '卖出',
    price: content.price,
    numberToDeal: content.optionNum,
    totalProfit: content.totalProfit,
  };
  data[1] = {
    code: content.upCode,
    name: content.upOptionName,
    type: content.upIsBuy ? '买入' : '卖出',
    price: content.upPrice,
    numberToDeal: content.upOptionNum,
    totalProfit: content.totalProfit,
  };
  data[2] = {
    code: content.downCode,
    name: content.downOptionName,
    type: content.downIsBuy ? '买入' : '卖出',
    price: content.downPrice,
    numberToDeal: content.downOptionNum,
    totalProfit: content.totalProfit,
  };
  const eachNotification = [
    <Table
      dataSource={data}
      columns={columns}
      bordered
      pagination={false}
      style={{ marginTop: 10, marginBottom: 20 }}
      key="0"
    />,
    <span key="1">
      <Button
        key="2"
        style={{ backgroundColor: '#F3F3F3', width: 214, height: 24, fontSize: 14, fontFamily: 'SongTi', float: 'right' }}
        htmlType="submit"
        onClick={onEntrust}
      >委托
      </Button>
      <Button
        key="3"
        style={{ backgroundColor: '#F3F3F3', width: 214, height: 24, fontSize: 14, fontFamily: 'SongTi', float: 'right', marginRight: 10 }}
        htmlType="submit"
        onClick={onClose}
      >忽略
      </Button>
    </span>,
  ];
  const openNotification = (type) => {
    notification[type]({
      key: index,
      message: '为您推荐最优套利组合',
      description: eachNotification,
      duration: 10,
      top: 100,
      style: { width: 520, marginLeft: -180, paddingTop: 30, paddingBottom: 20, backgroundColor: '#F3F3F3' },
    });
  };
  return (
    <Button type="primary" onClick={() => openNotification('info')}>here</Button>
  );
}

export default connect()(Notification);
