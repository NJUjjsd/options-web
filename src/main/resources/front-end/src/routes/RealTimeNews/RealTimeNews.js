/**
 * Created by john on 2017/8/6.
 */
import React from 'react';
import { connect } from 'dva';
import { Row, Col } from 'antd';
import MainLayout from '../../components/MainLayout/MainLayout';
import Separate from '../../components/Separate/Separate';
import styles from './RealTimeNews.css';

function RealTimeNews() {
  return (
    <MainLayout>
      <div className={styles.content}>
        <div className={styles.main}>
          <Separate className={styles.separate} />
          <Row>
            <Col offset={3} span={18} />
          </Row>
        </div>
      </div>
    </MainLayout>
  );
}

export default connect()(RealTimeNews);
