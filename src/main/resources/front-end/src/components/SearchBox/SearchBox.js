/**
 * Created by john on 2017/8/17.
 */
import React from 'react';
import { AutoComplete, Input, Icon } from 'antd';
import { connect } from 'dva';
import { routerRedux } from 'dva/router';
import styles from './SearchBox.css';

const Option = AutoComplete.Option;

class Complete extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      value: '',
    };
    this.props.dispatch({
      type: 'news/getTitles',
    });
  }

  onChange = (value) => {
    this.setState({
      value: this.props.titles[value],
    });
  };

  search = () => {
    let keyword = this.state.value;
    if (keyword.length > 32) {
      keyword = `${keyword.substring(0, 32)}...`;
    }
    this.props.dispatch(routerRedux.push({
      pathname: '/news/searchResult',
      query: {
        value: this.state.value,
        path: `/新闻/搜索结果/${keyword}`,
      },
    }));
    this.setState({
      value: '',
    });
  };

  render() {
    const children = this.props.titles.map((item, i) => {
      return <Option key={i}>{item}</Option>;
    });
    return (
      <AutoComplete
        style={{ width: 300 }}
        dataSource={children}
        placeholder="输入关键字..."
        onChange={this.onChange}
      >
        <Input
          suffix={
            <Icon
              type="search"
              className={styles.certain_category_icon}
              onClick={this.search.bind(this)}
            />
          }
          onPressEnter={this.search.bind(this)}
        />
      </AutoComplete>
    );
  }
}

function mapStateToProps(state) {
  const { titles } = state.news;
  return { titles };
}

export default connect(mapStateToProps)(Complete);
