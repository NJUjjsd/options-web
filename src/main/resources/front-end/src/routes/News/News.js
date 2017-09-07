/**
 * Created by john on 2017/8/6.
 */
import React from 'react';
import { Tabs, Checkbox, Row, Col } from 'antd';
import { connect } from 'dva';
import { routerRedux } from 'dva/router';
import MainLayout from '../../components/MainLayout/MainLayout';
import NewsList from '../../components/NewsList/NewsList';
import styles from './News.less';

const TabPane = Tabs.TabPane;

class News extends React.Component {
  onChange = (activeKey) => {
    this.props.dispatch(routerRedux.push({
      pathname: '/news',
      query: {
        code: this.props.location.query.code,
        type: activeKey,
        isDescByReadNum: this.props.location.query.isDescByReadNum,
        path: this.props.location.query.path,
      },
    }));
  };
  sort = (e) => {
    this.props.dispatch(routerRedux.push({
      pathname: '/news',
      query: {
        code: this.props.location.query.code,
        type: this.props.location.query.type,
        isDescByReadNum: e.target.checked,
        path: this.props.location.query.path,
      },
    }));
  };
  render() {
    const isDescByReadNum = (
      <Checkbox
        className={styles.checkBox}
        onChange={this.sort}
      >
        按热度
      </Checkbox>
      );
    const types = ['新闻', '研报', '公告'];
    return (
      <MainLayout location={this.props.location}>
        <Row>
          <Col span={18}>
            <Tabs
              hideAdd
              onChange={this.onChange}
              tabBarStyle={{ color: '#a3a3a3' }}
              defaultActiveKey={this.props.location.query.type}
              className={styles.tabPanes}
              tabBarExtraContent={isDescByReadNum}
            >
              {types.map(type =>
                <TabPane tab={type} key={type}>
                  <NewsList location={this.props.location} />
                </TabPane>)
              }
            </Tabs>
          </Col>
          <Col span={6}>
            <div className={styles.bar} />
          </Col>
        </Row>
      </MainLayout>
    );
  }
}


export default connect()(News);
