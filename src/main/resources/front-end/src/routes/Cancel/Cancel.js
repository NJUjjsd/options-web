/**
 * Created by john on 2017/9/8.
 */
import React from 'react';
import { connect } from 'dva';
import { Row, Col, Button } from 'antd';
import MainLayout from '../../components/MainLayout/MainLayout';
import CacelTable from '../../components/CancelTable/CancelTable';

function Cancel({ location }) {
  return (
    <MainLayout location={location}>
      <Row style={{ marginTop: 25 }}>
        <Col offset={4}>
          <Button style={{ fontSize: 14 }}>撤单</Button>
        </Col>
      </Row>
      <Row style={{ marginTop: 10 }}>
        <Col offset={4} span={15}>
          <CacelTable />
        </Col>
      </Row>
    </MainLayout>
  );
}

export default connect()(Cancel);
