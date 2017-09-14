/**
 * Created by a297 on 17/9/10.
 */
import React from 'react';
import { connect } from 'dva';
import { Input, Select, AutoComplete } from 'antd';

const InputGroup = Input.Group;
const Option = Select.Option;

class CodeInput extends React.Component {

  state = {
    codeInputType: 0,
    dataSource: [], // 当前下拉框中显示的匹配代码
  };

  handleSearch = (value) => {
    const match = [];
    const options = Object.keys(this.props.contractCodeAndName);
    for (const v of options) {
      if (v.includes(value)) {
        if (v !== '510050') {
          match.push(v);
        }
      }
    }
    this.setState({
      dataSource: !value ? [] : match,
    });
  };
  handleProductTypeChange = (value) => {
    this.setState({
      codeInputType: value === 'ETF' ? 0 : 1,
    });
    this.props.handleProductTypeChange(value);
  };
  render() {
    const { dataSource } = this.state;
    const ETFCodeInput =
      (<Input
        style={{ width: 200, color: '#000' }}
        value="510050"
        disabled
      />);
    const ETFOptionCodeInput =
      (<AutoComplete
        dataSource={dataSource}
        style={{ width: 200 }}
        onSelect={this.props.handleCodeInputBlur}
        onSearch={this.handleSearch}
        onBlur={this.props.handleCodeInputBlur}
        placeholder={'请输入代码'}
      />);
    const codeInputs = [ETFCodeInput, ETFOptionCodeInput];

    return (
      <InputGroup compact>
        <Select onChange={this.handleProductTypeChange} defaultValue="ETF">
          <Option value="ETF">ETF&nbsp;&nbsp;&nbsp;</Option>
          <Option value="ETFOption">ETF期权 </Option>
        </Select>
        {codeInputs[this.state.codeInputType]}
      </InputGroup>
    );
  }
}
function mapStateToProps(state) {
  const { contractCodeAndName } = state.userInvest;
  return { contractCodeAndName };
}

export default connect(mapStateToProps)(CodeInput);
