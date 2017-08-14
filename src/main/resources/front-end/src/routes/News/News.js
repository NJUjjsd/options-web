/**
 * Created by john on 2017/8/6.
 */
import React from 'react';
import { connect } from 'dva';
import MainLayout from '../../components/MainLayout/MainLayout';
import NewsGuide from '../../components/NewsGuide/NewsGuide';
import NewsList from '../../components/NewsList/NewsList';
import styles from './News.css';

function News() {
  return (
    <MainLayout>
      <div className={styles.content}>
        <NewsGuide />
        <NewsList />
      </div>
    </MainLayout>
  );
}

export default connect()(News);
