/**
 * Created by a297 on 17/9/8.
 */
import React from 'react';
import { connect } from 'dva';
import { Form, InputNumber, Button, Modal } from 'antd';
import PriceInput from '../../components/Input/PriceInput';
import CodeInput from '../../components/Input/CodeInput';

const FormItem = Form.Item;

function getMaxDealNum(holding, code) {
  for (const each of holding) {
    if (each.code === code) {
      return each.numberToSell;
    }
  }
  return '--';
}

class EntrustForm extends React.Component {
  state = {
    codeInputType: 0,

    code: '',
    name: '--',
    entrustPrice: '',
    dealNum: '',

    maxToDeal: '--',

    isCodeLegal: true,
    isPriceLegal: true,
    isDealNumLegal: true,

    modalLoading: false,
    modalVisible: false,
    modalContent: [],
  };
  componentWillReceiveProps(nextProps) {
    console.log(' will in 委托', nextProps);
    const deFaultCode = nextProps.codeInputType === 0 ? '510050' : '';
    // 最大交易数, 委托结果信息
    this.setState({
      codeInputType: nextProps.codeInputType,
      code: deFaultCode,
      name: nextProps.contractCodeAndName[deFaultCode],
      maxToDeal: nextProps.dealType === 1 ? getMaxDealNum(nextProps.holding, '510050') : '--',
    });
  }
  /**
   * 产品类型
   */
  handleProductTypeChange = (value) => {
    // 产品类型？名称？价格；交易数；最大可交易数量？代码合法；价格合法；交易数合法
    this.setState({
      codeInputType: value === 'ETF' ? 0 : 1,
      name: value === 'ETF' ? '上证50ETF' : '--',
      maxToDeal: (value === 'ETF' && this.props.dealType === 1)
        ? getMaxDealNum(this.props.holding, '510050') : '--',

      entrustPrice: '',
      dealNum: '',
      isCodeLegal: true,
      isPriceLegal: true,
      isDealNumLegal: true,
    });
  };
  /**
   *
   * code
   */
  handleCodeInputBlur = (value) => {
    if (value !== undefined) {
      // 有输入
      const nameGot = this.props.contractCodeAndName[value];
      // 代码；名称？代码合法？最大交易数？
      this.setState({
        code: value,
        name: nameGot === undefined ? '--' : nameGot,
        isCodeLegal: nameGot !== undefined,
        maxToDeal: (nameGot !== undefined && this.props.dealType === 1)
          ? getMaxDealNum(this.props.holding, value) : '--',
      });
    }
  };
  /**
   *
   * price
   */
  // 正在输入
  handlePriceChange = (value) => {
    this.setState({
      entrustPrice: value,
    });
  };
  // 得到价格
  handlePriceInput = (value) => {
    if (this.state.name === '--') {
      // code不合法
      this.setState({
        isCodeLegal: false,
        isPriceLegal: false,
        maxToDeal: '--',
      });
    } else {
      // code合法
      if (this.props.dealType === 0) {
        // 买入：最大交易数量?
        const eachShare = parseFloat(value);
        const maxToDeal = parseInt(this.props.balance / (eachShare * 100), 10);
        this.setState({
          maxToDeal: maxToDeal >= 0 ? `${maxToDeal}` : '--',
        });
      }
      this.setState({
        // 价格(float) >0
        isPriceLegal: this.state.entrustPrice !== undefined && this.state.entrustPrice > 0,
      });
    }
  };
  /**
   * 数量
   */
  handleDealNumChange = (value) => {
    this.setState({
      dealNum: value,
    });
    if (this.state.name === '--') {
      // code不合法
      this.setState({
        isCodeLegal: false,
        isPriceLegal: false,
        isDealNumLegal: false,
      });
    } else if (this.state.maxToDeal === '--') {
        // code合法
        // 最大交易数不合法，只有可能是［ 买入操作 > 价格 ］错误
        // 因为卖出只要code对了，最大可交易是后端给的数据
      this.setState({
        isPriceLegal: false,
        isDealNumLegal: false,
      });
    } else {
      // 最大交易数合法－－>格式检查
      this.setState({
        isDealNumLegal:
        (typeof value === 'number') && (value > 0) && (value <= this.state.maxToDeal) && (value % 1 === 0),
      });
    }
  };
  /**
   *
   * submit
   */
  handleEntrustSubmit = () => {
    // 代码（名称存在），名称（存在），价格（>=0），数量（[0，maxNum]），isBuy
    if (this.state.name === '--') {
      this.setState({
        isCodeLegal: false,
      });
    } else if (this.state.maxToDeal === '--' || this.state.entrustPrice === '') {
      this.setState({
        isPriceLegal: false,
      });
    } else if (this.state.dealNum === '') {
      this.setState({
        isDealNumLegal: false,
      });
    } else if (this.state.isCodeLegal && this.state.isPriceLegal && this.state.isDealNumLegal) {
      const fontColor = this.props.dealType === 0 ? '#865454' : '#678262';
      const modal = (<div><h1 style={{ textAlign: 'center', marginBottom: 10 }}>
        委托{this.props.dealType === 0 ? '买入' : '卖出'}</h1>
        <div style={{ marginBottom: 5, marginLeft: 155, fontSize: 16 }}>
          <h3 style={{ color: fontColor }}>代码： {this.state.code}</h3>
          <h3 style={{ color: fontColor }}>名称： {this.state.name}</h3>
          <h3 style={{ color: fontColor }}>价格： {this.state.entrustPrice}</h3>
          <h3 style={{ color: fontColor }}>数量： {this.state.dealNum}</h3>
        </div>
      </div>);
      this.setState({
        modalContent: modal,
        modalVisible: true,
      });
    }
  };
  handleOk = () => {
    const submitVO = {
      code: this.state.code,
      optionName: this.state.name,
      isBuy: this.props.dealType === 0,
      optionNum: this.state.dealNum,
      price: this.state.entrustPrice,
    };
    this.props.dispatch({
      type: 'userInvest/userEntrust',
      payload: submitVO,
    });
    this.setState({
      modalLoading: true,
    });
    setTimeout(() => {
      this.setState({
        // 初始化state
        codeInputType: 0,

        code: '',
        name: '--',
        entrustPrice: '',
        dealNum: '',

        maxToDeal: '--',

        isCodeLegal: true,
        isPriceLegal: true,
        isDealNumLegal: true,

        modalLoading: false,
        modalVisible: false,
        modalContent: [],
      });
    }, 5000);
    this.props.dispatch({
      type: 'userInvest/fetchInvestBasicInfo',
      payload: '',
    });
  };
  handleCancel = () => {
    this.setState({ modalVisible: false });
  };
  render() {
    const dealType = this.props.dealType;

    const maxToDealLabels = ['最大可买', '最大可卖'];
    const maxToDealLabel = maxToDealLabels[dealType];

    const userDealLabels = ['买入数量', '卖出数量'];
    const userDealLabel = userDealLabels[dealType];

    const btnTexts = ['买入', '卖出'];
    const btnText = btnTexts[dealType];

    const btnBgColors = ['#865454', '#678262'];
    const btnBgColor = btnBgColors[dealType];

    const measures = ['手', '张'];
    const measure = measures[this.state.codeInputType];

    return (
      <Form layout="inline" onSubmit={this.handleSubmit}>
        {this.state.isCodeLegal ? '' : <p style={{ color: 'red', marginLeft: 118 }}>请输入正确合约代码</p>}
        <FormItem label="代码">
          <CodeInput
            handleProductTypeChange={this.handleProductTypeChange}
            handleCodeInputBlur={this.handleCodeInputBlur}
          />
        </FormItem>
        <br />
        <FormItem label="名称">{this.state.name}</FormItem>
        <br />
        {this.state.isPriceLegal ? '' : <p style={{ color: 'red', marginLeft: 40 }}>请输入正确价格(大于0)</p>}
        <FormItem label="价格">
          <PriceInput
            value={this.state.entrustPrice}
            onChange={this.handlePriceChange}
            onBlur={this.handlePriceInput}
          />
        </FormItem>
        <br />
        {dealType === 0 ? <FormItem label="可用余额">{this.props.balance}</FormItem> : ''}
        {dealType === 0 ? <br /> : ''}
        <FormItem label={maxToDealLabel}>{this.state.maxToDeal} {measure}</FormItem>
        <br />
        {this.state.isDealNumLegal ? '' : <p style={{ color: 'red', marginLeft: 60 }}>请输入正整数 ({'<='}最大可交易数)</p>}
        <FormItem label={userDealLabel}>
          <span>
            <InputNumber value={this.state.dealNum} onChange={this.handleDealNumChange} />
            {measure}
          </span>
        </FormItem>
        <br />
        <FormItem>
          <Button
            style={{
              backgroundColor: btnBgColor,
              color: '#fff',
              width: 350,
              height: 40,
              marginTop: 10,
              fontSize: 20,
              fontFamily: 'SongTi',
            }} onClick={this.handleEntrustSubmit}
          >{btnText}
          </Button>
          <Modal
            visible={this.state.modalVisible}
            title="ETF－ETF期权期现套利交易系统"
            onOk={this.handleOk}
            onCancel={this.handleCancel}
            footer={[
              <Button key="back" size="large" onClick={this.handleCancel}>返回</Button>,
              <Button key="submit" type="primary" size="large" loading={this.state.modalLoading} onClick={this.handleOk}>
                {btnText}
              </Button>,
            ]}
          >
            {this.state.modalContent}
          </Modal>
        </FormItem>
      </Form>
    );
  }
}
function mapStateToProps(state) {
  const { balance, contractCodeAndName, holding } = state.userInvest;
  return { balance, contractCodeAndName, holding };
}
export default connect(mapStateToProps)(EntrustForm);
