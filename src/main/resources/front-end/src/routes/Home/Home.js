import React from 'react';
import { connect } from 'dva';
import { Row, Col } from 'antd';
import { Link } from 'dva/router';
import MainLayout from '../../components/MainLayout/MainLayout';
import styles from './Home.css';

function IndexPage({ location }) {
  return (
    <MainLayout location={location} >
      <div className={styles.title}>为您更好地决策ETF投资</div>
      <div className={styles.subtitle}>模型组合，优化方案，实时追踪</div>
      <Row className={styles.row}>
        <Col offset={5} span={4}>
          <Link to="/news">
            <div className={styles.real_time_news} />
          </Link>
        </Col>
        <Col offset={1} span={4}>
          <Link to="market">
            <div className={styles.latest_market} />
          </Link>
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