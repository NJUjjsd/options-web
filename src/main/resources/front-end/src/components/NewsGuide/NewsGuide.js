/**
 * Created by john on 2017/8/13.
 */
import React from 'react';
import { Row, Col, Radio } from 'antd';
import { connect } from 'dva';
import Separate from '../../components/Separate/Separate';
import styles from './NewsGuide.css';

function RealTimeNewsGuide() {
  return (
    <div className={styles.etf_guide}>
      <Row>
        <Col offset={4} span={16}>
          <span className={styles.etf_guide_item}>ETF</span>
          <span className={styles.etf_guide_item_separate}>|</span>
          <span className={styles.etf_guide_item}>ETF期权</span>
          <span className={styles.etf_guide_item_separate}>|</span>
          <span className={styles.etf_guide_item}>个股资讯</span>
          <Radio className={styles.select}>按热度</Radio>
        </Col>
      </Row>
      <Separate />
    </div>
  );
}

export default connect()(RealTimeNewsGuide);
