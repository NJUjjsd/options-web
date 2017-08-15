/**
 * Created by a297 on 17/8/14.
 */
import React from 'react';
import { connect } from 'dva';
import MainLayout from '../../components/MainLayout/MainLayout';

function Market({ location }) {
  return (
    <MainLayout location={location}>
      <h1>here comes the KLine</h1>
    </MainLayout>
  );
}

export default connect()(Market);
