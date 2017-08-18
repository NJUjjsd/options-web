import React from 'react';
import { connect } from 'dva';
import { Row, Col } from 'antd';
import { routerRedux } from 'dva/router';
import { defaultStockCode, defaultType, defaultNewsPath, defaultMarketPath } from '../../constant';
import MainLayout from '../../components/MainLayout/MainLayout';
import styles from './Home.css';

function IndexPage({ location, dispatch }) {
  function toNews() {
    dispatch(routerRedux.push({
      pathname: '/news',
      query: {
        code: defaultStockCode,
        type: defaultType,
        path: defaultNewsPath,
      },
    }));
  }
  function toMarket() {
    dispatch(routerRedux.push({
      pathname: '/market/ETF',
      query: {
        path: defaultMarketPath,
      },
    }));
  }
  return (
    <MainLayout location={location} >
      <div className={styles.title}>为您更好地决策ETF投资</div>
      <div className={styles.subtitle}>模型组合，优化方案，实时追踪</div>
      <Row className={styles.row}>
        <Col offset={5} span={4}>
          <div
            className={styles.real_time_news}
            onClick={toNews.bind(this)}
          />
        </Col>
        <Col offset={1} span={4}>
          <div
            className={styles.latest_market}
            onClick={toMarket.bind(this)}
          />
        </Col>
        <Col offset={1} span={4}>
          <div className={styles.start_invest} />
        </Col>
      </Row>
    </MainLayout>
  );
}

IndexPage.propTypes = {
};

export default connect()(IndexPage);
