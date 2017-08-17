/**
 * Created by a297 on 17/8/12.
 */
import React from 'react';
import { connect } from 'dva';
import MainLayout from '../../components/MainLayout/MainLayout';
import NewsBody from '../../components/NewsBody/NewsBody';

function NewsDetails({ location }) {
  return (
    <MainLayout location={location}>
      <NewsBody />
    </MainLayout>
  );
}

export default connect()(NewsDetails);
