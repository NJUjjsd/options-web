/**
 * Created by john on 2017/9/8.
 */
import React from 'react';
import { connect } from 'dva';
import { Row, Col } from 'antd';
import MainLayout from '../../components/MainLayout/MainLayout';
import EntrustForm from '../../components/EntrustForm/EntrustForm';
import HoldingTable from '../../components/HoldingTable/HodingTable';
import InformTable from '../../components/InformTable/InformTable';

function Entrust({ location }) {
  return (
    <MainLayout location={location}>
      <Row style={{ marginTop: 50 }}>
        <Col offset={4} span={7}>
          <h1 style={{ marginBottom: 20 }}>委托买入</h1>
          <EntrustForm />
        </Col>
        <Col span={1}>
          <hr style={{ width: 0.5, height: 280, marginLeft: 24, marginTop: 10, opacity: 0.3 }} />
        </Col>
        <Col span={7}>
          <h1 style={{ marginBottom: 20 }}>委托买入</h1>
          <EntrustForm />
        </Col>
      </Row>
      <Row style={{ marginTop: 50 }}>
        <Col offset={4} span={15}>
          <h1 style={{ marginBottom: 20 }}>目前持有</h1>
          <HoldingTable />
        </Col>
      </Row>
      <Row style={{ marginTop: 50 }}>
        <Col offset={4} span={15}>
          <h1 style={{ marginBottom: 20 }}>您有新的组合套利提醒</h1>
          <InformTable />
        </Col>
      </Row>
    </MainLayout>
  );
}

export default connect()(Entrust);
