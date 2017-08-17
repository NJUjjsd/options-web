/**
 * Created by john on 2017/8/13.
 */
import React from 'react';
import { connect } from 'dva';
import { Row, Col, Pagination } from 'antd';
import { defaultPageSize, defaultIsDescByReadNum } from '../../constant';
import NewsItem from './NewsItem';
import styles from './NewsList.css';

function NewsList({ dispatch, location, newsList, allNum, current }) {
  function pageChangeHandler(page) {
    const currentPage = page;
    dispatch({
      type: 'news/getClassifiedNews',
      payload: {
        page: currentPage,
        pageSize: defaultPageSize,
        code: location.query.code,
        type: location.query.type,
        isDescByReadNum: defaultIsDescByReadNum,
      },
    });
  }
  return (
    <Row className={styles.list}>
      <Col offset={4} span={16}>
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

export default connect(mapStateToProps)(NewsList);
