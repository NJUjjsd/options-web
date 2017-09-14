/**
 * Created by john on 2017/8/4.
 */
import React from 'react';
import { Layout, BackTop, Button } from 'antd';
import { routerRedux, Link } from 'dva/router';
import { connect } from 'dva';
import styles from './MainLayout.css';
import MainContent from '../MainContent/MainContent';
import { basicInfoPath } from '../../constant';

const { Header, Footer } = Layout;

class MainLayout extends React.Component {
  loginOrSignUp = () => {
    this.props.dispatch(routerRedux.push({
      pathname: '/users/login',
    }));
  };
  logout = () => {
    this.props.dispatch({
      type: 'users/logout',
    });
  };
  toUserInfo = () => {
    if (this.props.location.pathname !== '/users/basicInfo') {
      this.props.dispatch(routerRedux.push({
        pathname: '/users/basicInfo',
        query: {
          path: basicInfoPath,
        },
      }));
    }
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

    return (
      <Layout className={styles.topLayout}>
        <Header className={styles.header}>
          <div className={styles.logo}>
            <span>LoGo</span>
            <span>ETF－ETF期权期现套利交易系统</span>
          </div>
          {
            this.props.isLogin ?
              <div className={styles.headerDiv}>
                <span className={styles.headerSpan}>
                  <Button className={styles.headerMenu} onClick={this.logout.bind(this)} ghost>
                    退出
                  </Button>
                </span>
                <span className={styles.headerSpan}>
                  <Button className={styles.headerMenu} onClick={this.toUserInfo.bind(this)} ghost>
                    个人信息
                  </Button>
                </span>
              </div>
              :
              this.props.location.pathname === '/users/login' ||
              this.props.location.pathname === '/users/register' ?
                <Button className={styles.headerMenu} ghost>
                  <Link to="/" className={styles.link}>
                    首页
                  </Link>
                </Button>
                :
                <Button className={styles.headerMenu} onClick={this.loginOrSignUp.bind(this)} ghost>
                  登录／注册
                </Button>
          }
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

function mapStateToProps(state) {
  const { isLogin } = state.users;
  return { isLogin };
}

export default connect(mapStateToProps)(MainLayout);
