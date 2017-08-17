/**
 * Created by john on 2017/8/17.
 */
import React from 'react';
import { AutoComplete, Input, Icon } from 'antd';
import { connect } from 'dva';
import styles from './SearchBox.css';

const dataSource = ['Burns Bay Road', 'Downing Street', 'Wall Street'];

function Complete() {
  return (
    <AutoComplete
      style={{ width: 300 }}
      dataSource={dataSource}
      placeholder="输入关键字..."
      filterOption={
        (inputValue, option) => option.props.children
          .toUpperCase().indexOf(inputValue.toUpperCase()) !== -1
      }
    >
      <Input suffix={<Icon type="search" className={styles.certain_category_icon} />} />
    </AutoComplete>
  );
}

export default connect()(Complete);
