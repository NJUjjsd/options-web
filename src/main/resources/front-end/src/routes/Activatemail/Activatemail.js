/**
 * Created by john on 2017/9/13.
 */
import React from 'react';
import { Row, Col, Spin, Icon } from 'antd';
import { routerRedux } from 'dva/router';
import { connect } from 'dva';
import styles from './Activatemail.css';

function Activatemail({ dispatch, loading, result }) {
  function toLogin() {
    dispatch(routerRedux.replace({
      pathname: '/users/login',
    }));
  }
  return (
    <Row>
      <Col offset={6} span={12}>
        <Spin
          spinning={loading}
          tip="正在验证，请稍后..."
          size="small"
          style={{ marginTop: 200 }}
        >
          <div className={styles.content}>
            <p style={{ fontSize: 42, fontWeight: 300 }}>{result.message}</p>
            <div style={{ height: 20 }} />
            <a
              onClick={toLogin.bind(this)}
            >
              {
                result.result ?
                  <p
                    style={{ fontSize: 24, fontWeight: 300 }}
                  >
                    立即登录
                    <Icon type="arrow-right" style={{ paddingLeft: 15 }} />
                  </p>
                  :
                  <div />
              }
            </a>
          </div>
        </Spin>
      </Col>
    </Row>
  );
}

function mapStateToProps(state) {
  const { result } = state.email;
  return {
    loading: state.loading.models.email,
    result,
  };
}

export default connect(mapStateToProps)(Activatemail);
