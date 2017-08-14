import React from 'react';
import { connect } from 'dva';
import { Row, Col } from 'antd';
import { Link } from 'dva/router';
import MainLayout from '../../components/MainLayout/MainLayout';
import styles from './IndexPage.css';

function IndexPage() {
  return (
    <MainLayout>
      <div className={styles.content}>
        <div className={styles.main}>
          <div className={styles.title}>为您更好地决策ETF投资</div>
          <div className={styles.subtitle}>模型组合，优化方案，实时追踪</div>
          <Row className={styles.row}>
            <Col offset={5} span={4}>
              <Link to="/news">
                <div className={styles.real_time_news} />
              </Link>
            </Col>
            <Col offset={1} span={4}>
              <div className={styles.latest_market} />
            </Col>
            <Col offset={1} span={4}>
              <Link to="/newsDetails">
                <div className={styles.start_invest} />
              </Link>
            </Col>
          </Row>
        </div>
      </div>
    </MainLayout>
  );
}

IndexPage.propTypes = {
};

export default connect()(IndexPage);
