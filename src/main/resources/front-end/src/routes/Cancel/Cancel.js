/**
 * Created by john on 2017/9/8.
 */
import React from 'react';
import { connect } from 'dva';
import MainLayout from '../../components/MainLayout/MainLayout';

function Cancel({ location }) {
  return (
    <MainLayout location={location}>撤单</MainLayout>
  );
}

export default connect()(Cancel);
