/**
 * Created by john on 2017/9/8.
 */
import React from 'react';
import { connect } from 'dva';
import { Row, Col, notification, Table } from 'antd';
import MainLayout from '../../components/MainLayout/MainLayout';
import EntrustForm from '../../components/EntrustForm/EntrustForm';
import HoldingTable from '../../components/HoldingTable/HodingTable';

class Entrust extends React.Component {

  state = {
    index: 0,
    information: [],
  };
  componentWillMount() {
    console.log('will mount');
    setInterval(() => {
      this.props.dispatch({
        type: 'userInvest/fetchInformation',
      });
    }, 10000);
  }
  componentWillReceiveProps(nextProps) {
    const infoList = nextProps.information;
    const notifiList = [];
    if (infoList !== undefined && infoList.length > 0) {
      for (const info of infoList) {
        const data = [];
        data[0] = {
          code: info.code,
          name: info.optionName,
          type: info.isBuy ? '买入' : '卖出',
          price: info.price,
          profitPerPiece: info.profitPerPiece,
        };
        data[1] = {
          code: info.upCode,
          name: info.upOptionName,
          type: info.upIsBuy ? '买入' : '卖出',
          price: info.upPrice,
          profitPerPiece: info.profitPerPiece,
        };
        data[2] = {
          code: info.downCode,
          name: info.downOptionName,
          type: info.downIsBuy ? '买入' : '卖出',
          price: info.downPrice,
          profitPerPiece: info.profitPerPiece,
        };
        notifiList.push(data);
      }
      const i = this.state.index += 1;
      this.setState({
        information: notifiList,
        index: i,
      });
    }
  }
  componentWillUpdate(nextProps, nextState) {
    if (nextState.information.length > 0) {
      const columns = [{
        title: '代码',
        dataIndex: 'code',
      }, {
        title: '名称',
        dataIndex: 'name',
      }, {
        title: '类型',
        dataIndex: 'type',
      }, {
        title: '价格',
        dataIndex: 'price',
      }, {
        title: '每份获利',
        dataIndex: 'profitPerPiece',
        render(value, row, i) {
          const obj = {
            children: value,
            props: {},
          };
          if (i === 0) {
            obj.props.rowSpan = 3;
          } else {
            obj.props.rowSpan = 0;
          }
          return obj;
        },
      }];
      const len = nextState.information.length;
      const eachNotification = [
        <Table
          dataSource={nextState.information[len - 1]}
          columns={columns}
          bordered
          pagination={false}
          style={{ marginTop: 10, marginBottom: 20 }}
          key="0"
        />];
      console.log('index', nextState.index);
      const type = 'info';
      notification[type]({
        key: nextState.index,
        message: '为您推荐最优套利组合',
        description: eachNotification,
        duration: 3,
        top: 100,
        style: { width: 520, marginLeft: -180, paddingTop: 30, paddingBottom: 20, backgroundColor: '#F3F3F3' },
      });
    }
  }
  render() {
    return (
      <MainLayout location={this.props.location}>
        <Row style={{ marginTop: 25 }}>
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
