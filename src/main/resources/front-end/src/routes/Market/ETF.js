/**
 * Created by a297 on 17/8/14.
 */
import React from 'react';
import { connect } from 'dva';
import { Row, Col, Menu } from 'antd';
import MainLayout from '../../components/MainLayout/MainLayout';
import KLineChart from '../../components/KLineChart/KLineChart';
import styles from './Market.css';

function ETF({ tab, location, dispatch }) {
  function KLineTabChangeHandler(param) {
    const time = param.key;
    if (time !== tab) {
      dispatch({
        type: 'market/fetchETFKLineRawData',
        payload: { tab: time },
      });
    }
  }
  return (
    <MainLayout location={location}>
      <Row>
        <Col offset={3} span={16}>
          <KLineChart />
        </Col>
        <Col span={2}>
          <Menu
            className={styles.KLineTabs}
            defaultSelectedKeys={['monthly']}
            onSelect={KLineTabChangeHandler}
          >
            <Menu.Item key="daily">日 K</Menu.Item>
            <Menu.Item key="weekly">周 K</Menu.Item>
            <Menu.Item key="monthly">月 K</Menu.Item>
          </Menu>
        </Col>
      </Row>
    </MainLayout>
  );
}

function mapStateToProps(state) {
  const props = state.market;
  console.log('ETF', props);
  const tab = props.ETFKLineTab;
  return { tab };
}

export default connect(mapStateToProps)(ETF);
