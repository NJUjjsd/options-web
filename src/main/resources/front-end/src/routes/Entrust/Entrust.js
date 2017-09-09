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

class Entrust extends React.Component {
  state = {
    holding: [],
    noRiskRate: '0.000',
    principal: '0.00',
    assets: '0.00',
    information: [],
  }

  componentWillReceiveProps(nextProps) {
    if (nextProps.holding !== undefined) {
      this.setState({
        holding: nextProps.holding,
        noRiskRate: nextProps.noRiskRate,
        principal: nextProps.principal,
        assets: nextProps.assets,
        information: nextProps.information,
      });
    }
  }

  render() {
    return (
      <MainLayout location={this.props.location}>
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
            <HoldingTable holding={this.state.holding} />
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
}
function mapStateToProps(state) {
  const {
    holding,
    noRiskRate,
    principal,
    assets,
    information,
  } = state.userInvest;
  return {
    holding,
    noRiskRate,
    principal,
    assets,
    information,
  };
}
export default connect(mapStateToProps)(Entrust);
