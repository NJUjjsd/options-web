/**
 * Created by john on 2017/8/4.
 */
import React from 'react';
import { Layout, Menu } from 'antd';
import { Link } from 'dva/router';
import styles from './MainLayout.css';

const { Header, Footer, Content } = Layout;

function MainLayout({ children }) {
  return (
    <Layout className={styles.normal}>
      <Header className={styles.header}>
        <div className={styles.logo}>
          <span>LoGo</span>
          <span>ETF－ETF期权期现套利交易系统</span>
        </div>
        <Menu
          className={styles.menu}
          theme="dark"
          mode="horizontal"
          defaultSelectedKeys={[]}
        >
          <Menu.Item key="login">
            <Link to="/">首页</Link>
          </Menu.Item>
          <Menu.Item key="register">登录／注册</Menu.Item>
        </Menu>
      </Header>
      <Content style={{ background: '#fff', minHeight: 733 }}>
        {children}
      </Content>
      <Footer className={styles.footer}>
        Options ©2017 Created by NJU JJSD
      </Footer>
    </Layout>
  );
}

export default MainLayout;
