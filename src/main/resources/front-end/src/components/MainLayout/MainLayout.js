/**
 * Created by john on 2017/8/4.
 */
import React from 'react';
import { Layout, Menu, BackTop } from 'antd';
import { routerRedux } from 'dva/router';
import { connect } from 'dva';
import styles from './MainLayout.css';
import MainContent from '../MainContent/MainContent';

const { Header, Footer } = Layout;

class MainLayout extends React.Component {
  toLogin = () => {
    this.props.dispatch(routerRedux.push({
      pathname: '/users/login',
    }));
  };
  render() {
    const { location, children } = this.props;
    const pathname = location.pathname;
    //  区分是首页还是其他，content部分根据首页还是其他会有所不同
    let content;
    if (pathname === '/') {
      content = (
        <div className={styles.black}>{children}</div>
      );
    } else if (pathname === '/users/register' || pathname === '/users/login' || pathname === '/users/activatemail') {
      content = (
        <div className={styles.white}>{children}</div>
      );
    } else {
      content = (
        <MainContent location={location}>
          {children}
        </MainContent>
      );
    }

    //  todo 区分用户是否登录，如果登录，则显示"退出"；如果未登录，则显示"登录／注册"
    const loginMenu = '登录／注册';
    // const storage = window.sessionStorage;
    // const token = storage.getItem('token');
    // if (token && token.length > 0) {
    //   loginMenu = '退出';
    // }
    return (
      <Layout className={styles.topLayout}>
        <Header className={styles.header}>
          <div className={styles.logo}>
            <span>LoGo</span>
            <span>ETF－ETF期权期现套利交易系统</span>
          </div>
          <Menu
            className={styles.headerMenu}
            theme="dark"
            mode="horizontal"
            defaultSelectedKeys={[]}
          >
            <Menu.Item key="register">
              <div onClick={this.toLogin.bind(this)}>{loginMenu}</div>
            </Menu.Item>
          </Menu>
        </Header>
        {content}
        <BackTop />
        <Footer className={styles.footer}>
          Options ©2017 Created by NJU JJSD
        </Footer>
      </Layout>
    );
  }
}

export default connect()(MainLayout);
