/**
 * Created by john on 2017/8/15.
 */
import React from 'react';
import { Layout, Menu, Icon, Breadcrumb, Input } from 'antd';
import { connect } from 'dva';
import { Link, routerRedux } from 'dva/router';
import { defaultType, defaultStockCode,
  defaultNewsPath, defaultMarketPath, defaultIsDescByReadNum } from '../../constant';
import styles from './MainContent.css';


const { Content, Sider } = Layout;
const SubMenu = Menu.SubMenu;

class MainContent extends React.Component {
  state = {
    collapsed: false,
  };
  toggle = () => {
    this.setState({
      collapsed: !this.state.collapsed,
    });
  };
  singleStocks = () => {
    const singleStocks = [];
    if (this.props.stockCode.length === 0) {
      this.props.dispatch({
        type: 'news/getStockCode',
      });
    }
    for (let i = 0; i < this.props.stockCode.length; i += 1) {
      const data = this.props.stockCode[i];
      const value = `${data.name}(${data.code})`;
      singleStocks.push(<Menu.Item key={value}>{value}</Menu.Item>);
    }
    return singleStocks;
  };
  defaultSelectedKeys = () => {
    const arr = this.props.location.query.path.split('/');
    const defaultSelectedKeys = [arr[arr.length - 1]];
    return defaultSelectedKeys;
  };
  defaultOpenKeys = () => {
    const arr = this.props.location.query.path.split('/');
    const defaultOpenKeys = arr.splice(1, arr.length - 1);
    return defaultOpenKeys;
  };

  //  根据输入搜索新闻
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
  handleClick = (e) => {
    const length = e.keyPath.length;
    let path = '';
    for (let i = 0; i < e.keyPath.length; i += 1) {
      path = `${path}/${e.keyPath[e.keyPath.length - i - 1]}`;
    }
    if (e.keyPath[length - 1] === '实时新闻') {
      const code = e.key.substring(e.key.length - 7, e.key.length - 1);
      let type = defaultType;
      let isDescByReadNum = defaultIsDescByReadNum;
      if (this.props.location.pathname === '/news') {
        type = this.props.location.query.type;
        isDescByReadNum = this.props.location.query.isDescByReadNum;
      }
      this.props.dispatch(routerRedux.push({
        pathname: '/news',
        query: {
          code,
          type,
          isDescByReadNum,
          path,
        },
      }));
    } else if (e.keyPath[length - 1] === '最新行情') {
      const pathname = e.key === 'ETF' ? 'ETF' : 'ETFOption';
      this.props.dispatch(routerRedux.push({
        pathname: `/market/${pathname}`,
        query: {
          path,
        },
      }));
    }
  };
  searchState = () => {
    let searchStyle = styles.hideSearch;
    if (this.props.location.pathname === '/news' ||
      this.props.location.pathname === '/news/details' ||
    this.props.location.pathname === '/news/searchResult') {
      searchStyle = styles.showSearch;
    }
    return searchStyle;
  };
  toNews = () => {
    if (this.props.location.pathname !== '/news') {
      this.props.dispatch(routerRedux.push({
        pathname: '/news',
        query: {
          code: defaultStockCode,
          type: defaultType,
          isDescByReadNum: defaultIsDescByReadNum,
          path: defaultNewsPath,
        },
      }));
    }
  };
  toMarket = () => {
    if (this.props.location.pathname.substring(0, 7) !== '/market') {
      this.props.dispatch(routerRedux.push({
        pathname: '/market/ETF',
        query: {
          path: defaultMarketPath,
        },
      }));
    }
  };
  render() {
    const defaultSelectedKeys = this.defaultSelectedKeys();
    const defaultOpenKeys = this.defaultOpenKeys();
    const singleStocks = this.singleStocks();
    return (
      <Layout className={styles.contentLayout}>
        <Sider
          trigger={null}
          collapsible
          collapsed={this.state.collapsed}
          className={styles.sider}
        >
          <Icon
            className={styles.trigger}
            type={this.state.collapsed ? 'menu-unfold' : 'menu-fold'}
            onClick={this.toggle}
          />
          <Menu
            defaultSelectedKeys={defaultSelectedKeys}
            defaultOpenKeys={defaultOpenKeys}
            onClick={this.handleClick}
            mode="inline"
          >
            <Menu.Item key="首页">
              <Link to="/" className={styles.link}>
                <Icon type="home" />
                <span>首页</span>
              </Link>
            </Menu.Item>
            <Menu.Item key="个人信息">
              <Link to="/" className={styles.link}>
                <Icon type="user" />
                <span>个人信息</span>
              </Link>
            </Menu.Item>
            <SubMenu
              key="实时新闻"
              title={
                <span onClick={this.toNews.bind(this)}>
                  <Icon type="link" />
                  <span>实时新闻</span>
                </span>
              }
            >
              <SubMenu
                key="ETF资讯"
                title="ETF资讯"
              >
                <Menu.Item key="上证50ETF(510050)">上证50ETF(510050)</Menu.Item>
              </SubMenu>
              <SubMenu
                key="个股资讯"
                title="个股资讯"
                className={styles.singleStocks}
              >
                {singleStocks}
              </SubMenu>
            </SubMenu>
            <SubMenu
              key="最新行情"
              title={
                <span onClick={this.toMarket.bind(this)}>
                  <Icon type="line-chart" />
                  <span>最新行情</span>
                </span>
              }
            >
              <Menu.Item key="ETF">ETF</Menu.Item>
              <Menu.Item key="ETF期权">ETF期权</Menu.Item>
            </SubMenu>
            <Menu.Item key="开始投资">
              <Icon type="bank" />
              <span>开始投资</span>
            </Menu.Item>
          </Menu>
        </Sider>
        <Layout className={styles.childrenLayout}>
          <Content className={styles.children}>
            <div className={styles.top}>
              <span className={styles.breadcrumb}>
                <Breadcrumb separator=">" className={styles.bread}>
                  {
                    this.props.location.query.path.split('/')
                      .splice(1, this.props.location.query.path.split('/').length - 1)
                      .map((item, i) =>
                        <Breadcrumb.Item key={i + 1}>{item}</Breadcrumb.Item>,
                      )
                  }
                </Breadcrumb>
              </span>
              <span className={this.searchState()}>
                <Input.Search
                  onSearch={this.handleSearch.bind(this)}
                />
              </span>
            </div>
            {this.props.children}
          </Content>
        </Layout>
      </Layout>
    );
  }
}

MainContent.prototypes = {
  stockCode: Array,
};

function mapStateToProps(state) {
  const { stockCode } = state.news;
  return { stockCode };
}

export default connect(mapStateToProps)(MainContent);
