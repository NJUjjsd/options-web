import React from 'react';
import { connect } from 'dva';
import { Row, Col, Modal } from 'antd';
import { routerRedux } from 'dva/router';
import { defaultStockCode, defaultInvestPath,
  defaultType, defaultNewsPath, defaultMarketPath,
  defaultIsDescByReadNum } from '../../constant';
import MainLayout from '../../components/MainLayout/MainLayout';
import styles from './Home.css';

function IndexPage({ location, dispatch, isLogin }) {
  function toNews() {
    dispatch(routerRedux.push({
      pathname: '/news',
      query: {
        code: defaultStockCode,
        type: defaultType,
        isDescByReadNum: defaultIsDescByReadNum,
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
  function toInvest() {
    if (isLogin) {
      dispatch(routerRedux.push({
        pathname: '/invest/entrust',
        query: {
          path: defaultInvestPath,
        },
      }));
    } else {
      loginRemind();
    }
  }
  function loginRemind() {
    Modal.warning({
      content: '您尚未登录，请先登录，再开始投资',
    });
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
          <div
            className={styles.start_invest}
            onClick={toInvest.bind(this)}
          />
        </Col>
      </Row>
    </MainLayout>
  );
}

IndexPage.propTypes = {
};

function mapStateToProps(state) {
  const { isLogin } = state.users;
  return { isLogin };
}

export default connect(mapStateToProps)(IndexPage);
