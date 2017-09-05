/**
 * Created by john on 2017/8/17.
 */
import React from 'react';
import { AutoComplete, Input } from 'antd';
import { connect } from 'dva';
import { routerRedux } from 'dva/router';

const Option = AutoComplete.Option;

class Complete extends React.Component {
  onSearch = (value) => {
    this.props.dispatch({
      type: 'news/getTitles',
      payload: { value },
    });
  };

  handleSearch = (value) => {
    let keyword = value;
    if (keyword.length > 32) {
      keyword = `${keyword.substring(0, 32)}...`;
    }
    this.props.dispatch(routerRedux.push({
      pathname: '/news/searchResult',
      query: {
        value,
        path: `/新闻/搜索结果/${keyword}`,
      },
    }));
  };

  render() {
    const children = this.props.titles.map((item, i) => {
      return <Option key={i}>{item}</Option>;
    });
    return (
      <AutoComplete
        style={{ width: 300 }}
        dataSource={children}
        onSearch={this.onSearch}
        placeholder="输入关键字..."
      >
        <Input.Search onSearch={this.handleSearch.bind(this)} />
      </AutoComplete>
    );
  }
}

function mapStateToProps(state) {
  const { titles } = state.news;
  return { titles };
}

export default connect(mapStateToProps)(Complete);
