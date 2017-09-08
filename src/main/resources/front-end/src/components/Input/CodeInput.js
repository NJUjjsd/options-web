/**
 * Created by a297 on 17/9/8.
 */
import React from 'react';
import { connect } from 'dva';
import { Input } from 'antd';

class CodeInput extends React.Component {
  render() {
    return (
      <Input type="text" />
    );
  }
}
export default connect()(CodeInput);
