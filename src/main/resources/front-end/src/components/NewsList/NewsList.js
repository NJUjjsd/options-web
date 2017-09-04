/**
 * Created by john on 2017/8/13.
 */
import React from 'react';
import { connect } from 'dva';
import { Row, Col, Pagination } from 'antd';
import { defaultPageSize, defaultIsDescByReadNum } from '../../constant';
import NewsItem from './NewsItem';
import styles from './NewsList.css';

function NewsList({ dispatch, location, newsList, allNum, current, loading }) {
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
    <Row
      className={styles.list}
      loading={loading}
    >
      <Col offset={2} span={19}>
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
  console.log('components/NewsList.js/mapStateToProps:');
  console.log(newsList);
  return {
    newsList,
    allNum,
    current,
    loading: state.loading.models.news,
  };
}

export default connect(mapStateToProps)(NewsList);
