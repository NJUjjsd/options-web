/**
 * Created by a297 on 17/8/17.
 */
import React from 'react';
import { connect } from 'dva';
import { Row, Col, Select } from 'antd';
import MainLayout from '../../components/MainLayout/MainLayout';
import OptionForm from '../../components/OptionForm/OptionForm';
import ETFInfoForm from '../../components/OptionForm/ETFInfoForm';
import styles from './Market.css';

const Option = Select.Option;

function ETFOption({
  dueMonthList,
  ETFTime,
  transactionTime,
  selectedMonthIndex,
  location,
  dispatch,
}) {
  /**
   * 当前所选合约到期月份
   */
  const curDueMonth = dueMonthList[selectedMonthIndex];
  /**
   * 当前所选合约到期月份的合约信息的更新时间
   */
  const updatetransactionTime = transactionTime[selectedMonthIndex];

  /**
   * set options in the selector
   */
  const monthList = dueMonthList;
  const monthOption = [];
  monthList.map((v, i) => {
    return (
      monthOption.push(<Option value={v} key={i}>
        {v.split('-')[0]}年{v.split('-')[1]}月
      </Option>)
    );
  });

  /**
   *
   * select due month
   *
   * @param value selected month
   */
  function dueMonthChangeHandler(value) {
    for (let i = 0; i < dueMonthList.length; i += 1) {
      if (value === dueMonthList[i]) {
        dispatch({
          type: 'market/changeSelectedMonthIndex',
          payload: { selectedMonthIndex: i },
        });
      }
    }
  }

  return (
    <MainLayout location={location}>
      <Row className={styles.selectMonth}>
        <Col offset={4} span={2}>
          <h3>合同到期月份</h3>
        </Col>
        <Col span={2}>
          <Select defaultValue={curDueMonth} onChange={dueMonthChangeHandler}>
            { monthOption }
          </Select>
        </Col>
      </Row>
      <Row>
        <Col offset={4} span={15}>
          <p className={styles.updateTime}>更新时间：{ ETFTime } </p>
        </Col>
      </Row>
      <Row>
        <Col offset={4} span={15}>
          <ETFInfoForm />
        </Col>
      </Row>
      <Row>
        <Col offset={4} span={15}>
          <p className={styles.updateTime}>更新时间：{ updatetransactionTime } </p>
        </Col>
      </Row>
      <Row>
        <Col offset={4} span={15}>
          <OptionForm />
        </Col>
      </Row>
    </MainLayout>
  );
}

function mapStateToProps(state) {
  const { dueMonthList, ETFTime, transactionTime, selectedMonthIndex } = state.market;
  return { dueMonthList, ETFTime, transactionTime, selectedMonthIndex };
}

export default connect(mapStateToProps)(ETFOption);
