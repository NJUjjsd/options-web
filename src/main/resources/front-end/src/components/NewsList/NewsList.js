/**
 * Created by john on 2017/8/13.
 */
import React from 'react';
import { connect } from 'dva';
import { Row, Col, Pagination } from 'antd';
import NewsItem from './NewsItem';
import styles from './NewsList.css';

function RealTimeNewsList({ dispatch, newsList, allNum, current }) {
  function pageChangeHandler(page) {
    const currentPage = page;
    dispatch({
      type: 'news/getList',
      payload: { page: currentPage },
    });
  }
  return (
    <Row>
      <Col offset={5} span={14}>
        {
          newsList.map((v) => {
            return (
              <NewsItem data={v} />
            );
          })
        }
        <Pagination
          defaultCurrent={1}
          total={allNum}
          current={current}
          className={styles.page}
          onChange={pageChangeHandler}
        />
      </Col>
    </Row>
  );
}

function mapStateToProps(state) {
  const { newsList, allNum, current } = state.news;
  return { newsList, allNum, current };
}

export default connect(mapStateToProps)(RealTimeNewsList);
