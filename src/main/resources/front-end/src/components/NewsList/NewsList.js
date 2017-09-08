/**
 * Created by john on 2017/8/13.
 */
import React from 'react';
import { connect } from 'dva';
import { Row, Col, Pagination, Spin } from 'antd';
import { defaultPageSize, defaultIsDescByReadNum } from '../../constant';
import NewsItem from './NewsItem';
import styles from './NewsList.css';

function NewsList({ dispatch, location, loading, newsList, allNum, current }) {
  function pageChangeHandler(page) {
    const currentPage = page;
    if (location.pathname === '/news') {
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
    } else if (location.pathname === '/news/searchResult') {
      dispatch({
        type: 'news/getList',
        payload: {
          page: currentPage,
          pageSize: defaultPageSize,
          keyword: location.query.value,
        },
      });
    }
  }
  return (
    <Row
      className={styles.list}
    >
      <Col offset={2} span={19}>
        <Spin spinning={loading} size="large" tip="正在加载，请稍后...">
          {
            newsList.map((v, i) => {
              return (
                <NewsItem key={i} data={v} />
              );
            })
          }
        </Spin>
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
  return {
    loading: state.loading.models.news,
    newsList,
    allNum,
    current,
  };
}

export default connect(mapStateToProps)(NewsList);
