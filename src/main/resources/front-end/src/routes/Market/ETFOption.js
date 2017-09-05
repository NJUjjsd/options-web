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

class ETFOption extends React.Component {
  state = {
    dueMonths: [],
    ETFUpdateTime: '',
    basicInfo: {},
    contactUpdateTime: [],
    contactInfo: [],
    selectedMonthIndex: 0,
  };

  componentWillReceiveProps(nextProps) {
    if (nextProps.dueMonths !== undefined) {
      this.setState({
        dueMonths: nextProps.dueMonths,
        ETFUpdateTime: nextProps.ETFUpdateTime,
        basicInfo: nextProps.basicInfo,
        contactUpdateTime: nextProps.contactUpdateTime,
        contactInfo: nextProps.contactInfo,
        selectedMonthIndex: nextProps.selectedMonthIndex,
      });
    }
  }
  /**
   *
   * select due month
   *
   * @param value selected month
   */
  dueMonthChangeHandler = (value) => {
    for (let i = 0; i < 4; i += 1) {
      if (value === this.state.dueMonths[i]) {
        this.props.dispatch({
          type: 'market/changeSelectedMonthIndex',
          payload: { selectedMonthIndex: i },
        });
        break;
      }
    }
  }

  render() {
    let defaultMonth = '';
    let contactTime = '';
    let options = [];
    if (this.state.dueMonths.length !== 0) {
      const months = this.state.dueMonths;
      const index = this.state.selectedMonthIndex;
      defaultMonth = this.state.dueMonths[index];
      contactTime = this.state.contactUpdateTime[index];
      options = months.map((v, i) => {
        return (
          <Option value={v} key={i}>
            {v.split('-')[0]}年{v.split('-')[1]}月
          </Option>
        );
      });
    }
    return (
      <MainLayout location={this.props.location}>
        <Row className={styles.selectMonth}>
          <Col offset={4} span={2}>
            <h3>合同到期月份</h3>
          </Col>
          <Col span={2}>
            <Select value={defaultMonth} onChange={this.dueMonthChangeHandler}>
              {options}
            </Select>
          </Col>
        </Row>
        <Row>
          <Col offset={4} span={15}>
            <p className={styles.updateTime}>更新时间：{this.state.ETFUpdateTime} </p>
          </Col>
        </Row>
        <Row>
          <Col offset={4} span={15}>
            <ETFInfoForm basicInfo={this.state.basicInfo} />
          </Col>
        </Row>
        <Row>
          <Col offset={4} span={15}>
            <p className={styles.updateTime}>更新时间：{contactTime} </p>
          </Col>
        </Row>
        <Row>
          <Col offset={4} span={15}>
            <OptionForm
              dueMonths={this.state.dueMonths}
              contactInfo={this.state.contactInfo}
              selectedMonthIndex={this.state.selectedMonthIndex}
            />
          </Col>
        </Row>
      </MainLayout>
    );
  }
}

function mapStateToProps(state) {
  const {
    dueMonths,
    ETFUpdateTime,
    basicInfo,
    contactUpdateTime,
    contactInfo,
    selectedMonthIndex,
  } = state.market;
  return {
    dueMonths,
    ETFUpdateTime,
    basicInfo,
    contactUpdateTime,
    contactInfo,
    selectedMonthIndex,
  };
}

export default connect(mapStateToProps)(ETFOption);
