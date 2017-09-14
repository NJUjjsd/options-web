/**
 * Created by john on 2017/9/8.
 */
import React from 'react';
import { connect } from 'dva';
import { Row, Col } from 'antd';
import MainLayout from '../../components/MainLayout/MainLayout';
import EntrustForm from '../../components/EntrustForm/EntrustForm';
import HoldingTable from '../../components/HoldingTable/HodingTable';
import Notification from '../../components/InformTable/Notification';

class Entrust extends React.Component {
  render() {
    const { information } = this.props;

    const notifications = [];
    for (let i = 0; i < information.length; i += 1) {
      notifications.push(
        <Notification index={i} content={information[i]} />,
      );
    }

    return (
      <MainLayout location={this.props.location}>
        <Row style={{ marginTop: 25 }}>
          <Col offset={12} span={7}>
            {notifications}
          </Col>
          <Col offset={4} span={8}>
            <Row style={{ marginBottom: 30 }}>
              <h1 style={{ marginBottom: 20 }}>委托买入</h1>
              <EntrustForm dealType={0} codeInputType={0} />
            </Row>
            <Row>
              <h1 style={{ marginBottom: 20 }}>委托卖出</h1>
              <EntrustForm dealType={1} codeInputType={0} />
            </Row>
          </Col>
        </Row>
        <Row style={{ marginTop: 30 }}>
          <Col offset={4} span={15}>
            <hr style={{ opacity: 0.3 }} />
          </Col>
        </Row>
        <Row style={{ marginTop: 50 }}>
          <Col offset={4} span={15}>
            <h1 style={{ marginBottom: 20 }}>目前持有</h1>
            <HoldingTable />
          </Col>
        </Row>
        <Row>
          <Col offset={4}>
            <h3 style={{ marginBottom: 10, marginLeft: 5 }}>
              本金: {this.props.principal} 元 &nbsp;&nbsp;&nbsp;&nbsp; 总资产: {this.props.assets} 元
            </h3>
            <h3 style={{ marginBottom: 10, marginLeft: 5 }}>
              最高无风险利率: {this.props.noRiskRate}
            </h3>
          </Col>
        </Row>
        <Row style={{ marginTop: 30 }}>
          <Col offset={4} span={15}>
            <hr style={{ opacity: 0.3 }} />
          </Col>
        </Row>
        <div style={{ height: 50 }} />
      </MainLayout>
    );
  }
}
function mapStateToProps(state) {
  const {
    noRiskRate,
    principal,
    assets,
    information,
  } = state.userInvest;
  console.log('the information in page', information);
  return {
    noRiskRate,
    principal,
    assets,
    information,
  };
}
export default connect(mapStateToProps)(Entrust);
