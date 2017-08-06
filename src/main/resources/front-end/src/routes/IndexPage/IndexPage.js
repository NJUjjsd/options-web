import React from 'react';
import { connect } from 'dva';
import { Row, Col } from 'antd';
import MainLayout from '../../components/MainLayout/MainLayout';
import styles from './IndexPage.css';
// import IconRealTimeNews from '../../components/IconRealTimeNews/IconRealTimeNews'
// import { Link } from 'dva/router';

function IndexPage() {
  return (
    <MainLayout>
      <div className={styles.content}>
        <div className={styles.title}>为您更好地决策ETF投资</div>
        <div className={styles.subtitle}>模型组合，优化方案，实时追踪</div>
        <Row className={styles.row}>
          <Col offset={5} span={4}>
            <div className={styles.real_time_news} />
          </Col>
          <Col offset={1} span={4}>
            <div className={styles.latest_market} />
          </Col>
          <Col offset={1} span={4}>
            <div className={styles.start_invest} />
          </Col>
        </Row>
      </div>
    </MainLayout>
  );
}

IndexPage.propTypes = {
};

export default connect()(IndexPage);
