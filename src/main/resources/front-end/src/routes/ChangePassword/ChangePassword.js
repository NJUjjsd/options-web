/**
 * Created by john on 2017/9/10.
 */
import React from 'react';
import { Row, Col } from 'antd';
import { connect } from 'dva';
import MainLayout from '../../components/MainLayout/MainLayout';
import ChangePasswordForm from '../../components/ChangePassword/ChangePassword';

function ChangePassword({ location }) {
  return (
    <MainLayout location={location}>
      <Row style={{ marginTop: 80 }}>
        <Col offset={8} span={8}>
          <ChangePasswordForm />
        </Col>
      </Row>
    </MainLayout>
  );
}

export default connect()(ChangePassword);
