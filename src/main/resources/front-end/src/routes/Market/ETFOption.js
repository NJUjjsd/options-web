/**
 * Created by a297 on 17/8/17.
 */
import React from 'react';
import { connect } from 'dva';
import { Row, Col } from 'antd';
import MainLayout from '../../components/MainLayout/MainLayout';
import OptionForm from '../../components/OptionForm/OptionForm';

function ETFOption({ location }) {
  const time = '2017-08-17 15:15:04';
  return (
    <MainLayout location={location}>
      <Row>
        <Col offset={4} span={15}>
          <p style={{ float: 'right', opacity: 0.8, marginTop: 18, marginBottom: 10 }}>更新时间：{ time } </p>
        </Col>
      </Row>
      <Row>
        <Col offset={4} span={15}>
          <OptionForm />
        </Col>
      </Row>
    </MainLayout>
  );
}

export default connect()(ETFOption);
