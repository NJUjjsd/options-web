/**
 * Created by john on 2017/8/14.
 */
import React from 'react';
import { Layout, Menu } from 'antd';
import { Link } from 'dva/router';
import { connect } from 'dva';
import styles from './Nav.css';

const Header = Layout.Header;

function Nav() {
  return (
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
  );
}

export default connect()(Nav);
