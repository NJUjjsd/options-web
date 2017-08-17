/**
 * Created by john on 2017/8/4.
 */
import React from 'react';
import { Layout, Menu } from 'antd';
import { connect } from 'dva';
import styles from './MainLayout.css';
import MainContent from '../MainContent/MainContent';

const { Header, Footer } = Layout;

function MainLayout({ children, location }) {
  let content = (
    <MainContent location={location}>
      {children}
    </MainContent>
  );
  if (location.pathname === '/') {
    content = (
      <div className={styles.black}>{children}</div>
    );
  }
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
          <Menu.Item key="register">登录／注册</Menu.Item>
        </Menu>
      </Header>
      {content}
      <Footer className={styles.footer}>
        Options ©2017 Created by NJU JJSD
      </Footer>
    </Layout>
  );
}

export default connect()(MainLayout);
