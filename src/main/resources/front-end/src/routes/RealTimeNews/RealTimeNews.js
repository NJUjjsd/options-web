/**
 * Created by john on 2017/8/6.
 */
import React from 'react';
import { connect } from 'dva';
import { Row, Col, Radio, Pagination } from 'antd';
import NewsItem from '../../components/NewsItem/NewsItem';
import MainLayout from '../../components/MainLayout/MainLayout';
import Separate from '../../components/Separate/Separate';
import styles from './RealTimeNews.css';

function RealTimeNews() {
  return (
    <MainLayout>
      <div className={styles.content}>
        <div className={styles.main}>
          <Row className={styles.etf_guide}>
            <Col offset={5} span={14}>
              <span className={styles.etf_guide_item}>ETF</span>
              <span className={styles.etf_guide_item_separate}>|</span>
              <span className={styles.etf_guide_item}>ETF期权</span>
              <span className={styles.etf_guide_item_separate}>|</span>
              <span className={styles.etf_guide_item}>个股资讯</span>
              <Radio className={styles.select}>按热度</Radio>
            </Col>
          </Row>
          <Separate />
          <Row className={styles.news}>
            <Col offset={5} span={14}>
              <NewsItem />
              <NewsItem />
              <NewsItem />
              <NewsItem />
              <NewsItem />
              <NewsItem />
              <Pagination defaultCurrent={6} total={500} className={styles.page} />
            </Col>
          </Row>
        </div>
      </div>
    </MainLayout>
  );
}

export default connect()(RealTimeNews);
