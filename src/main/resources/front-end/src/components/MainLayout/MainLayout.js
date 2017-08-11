/**
 * Created by john on 2017/8/4.
 */
import React from 'react';
import { Layout, Menu } from 'antd';
import styles from './MainLayout.css';

const { Header, Footer } = Layout;

function MainLayout({ children }) {
  return (
    <Layout className={styles.normal}>
      <Header className={styles.header}>
        <div className={styles.logo} />
        <Menu
          className={styles.menu}
          theme="dark"
          mode="horizontal"
          defaultSelectedKeys={[]}
        >
          <Menu.Item key="login">首页</Menu.Item>
          <Menu.Item key="register">登录／注册</Menu.Item>
        </Menu>
      </Header>
      {children}
      <Footer className={styles.footer}>
        Options ©2017 Created by NJU JJSD
      </Footer>
    </Layout>
  );
}

export default MainLayout;
