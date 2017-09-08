/**
 * Created by john on 2017/9/8.
 */
import React from 'react';
import { Row, Col } from 'antd';
import { connect } from 'dva';
import MainLayout from '../../components/MainLayout/MainLayout';
import RegisterForm from '../../components/Register/Register';

function Register({ location }) {
  return (
    <MainLayout location={location}>
      <Row>
        <Col offset={8} span={8}>
          <RegisterForm />
        </Col>
      </Row>
    </MainLayout>
  );
}

export default connect()(Register);
