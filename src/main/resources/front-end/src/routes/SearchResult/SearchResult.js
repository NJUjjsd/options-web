/**
 * Created by john on 2017/9/5.
 */
import React from 'react';
import { Row, Col } from 'antd';
import { connect } from 'dva';
import MainLayout from '../../components/MainLayout/MainLayout';
import NewsList from '../../components/NewsList/NewsList';
import styles from './SearchResult.less';

function SearchResult({ location }) {
  return (
    <MainLayout location={location}>
      <Row className={styles.resultContent}>
        <Col span={18}>
          <NewsList location={location} />
        </Col>
      </Row>
    </MainLayout>
  );
}


export default connect()(SearchResult);
