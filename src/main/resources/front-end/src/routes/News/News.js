/**
 * Created by john on 2017/8/6.
 */
import React from 'react';
import { connect } from 'dva';
import MainLayout from '../../components/MainLayout/MainLayout';
import NewsGuide from '../../components/NewsGuide/NewsGuide';
import NewsList from '../../components/NewsList/NewsList';

function News({ location }) {
  return (
    <MainLayout location={location} >
      <NewsGuide />
      <NewsList />
    </MainLayout>
  );
}

export default connect()(News);
