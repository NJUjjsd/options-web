/**
 * Created by a297 on 17/8/12.
 */
import React from 'react';
import { connect } from 'dva';
import { Row, Col } from 'antd';
import MainLayout from '../../components/MainLayout/MainLayout';
import Separate from '../../components/Separate/Separate';
import NewsBody from '../../components/NewsBody/NewsBody';
import styles from './NewsDetails.css';

function NewsDetails() {
  return (
    <MainLayout>
      <div className={styles.content}>
        <div className={styles.main}>
          <Row>
            <Col className={styles.cur_location} offset={4}>
              新闻 &gt; 正文
            </Col>
          </Row>
          <Separate />
          <NewsBody />
        </div>
      </div>
    </MainLayout>
  );
}

export default connect()(NewsDetails);
