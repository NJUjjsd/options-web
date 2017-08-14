/**
 * Created by john on 2017/8/6.
 */
import React from 'react';
import { connect } from 'dva';
import MainLayout from '../../components/MainLayout/MainLayout';
import RealTimeNewsGuide from '../../components/NewsGuide/NewsGuide';
import RealTimeNewsList from '../../components/NewsList/NewsList';
import styles from './News.css';

function RealTimeNews() {
  return (
    <MainLayout>
      <div className={styles.content}>
        <div className={styles.main}>
          <RealTimeNewsGuide />
          <RealTimeNewsList />
        </div>
      </div>
    </MainLayout>
  );
}

export default connect()(RealTimeNews);
