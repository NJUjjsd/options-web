/**
 * Created by john on 2017/9/8.
 */
import React from 'react';
import { connect } from 'dva';
import MainLayout from '../../components/MainLayout/MainLayout';

function Entrust({ location }) {
  return (
    <MainLayout location={location}>委托</MainLayout>
  );
}

export default connect()(Entrust);
