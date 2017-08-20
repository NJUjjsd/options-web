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
  constructor(props) {
    super(props);
    this.state = {
      types: ['新闻', '研报', '公告'],
      activeKey: '新闻',
    };
  }
  onChange = (activeKey) => {
    const code = this.props.location.query.code;
    const type = activeKey;
    this.props.dispatch(routerRedux.push({
      pathname: '/news',
      query: {
        code,
        type,
        path: this.props.location.query.path,
      },
    }));
    this.setState({ activeKey });
  };
  render() {
    const isDescByReadNum = <Checkbox className={styles.checkBox}>按热度</Checkbox>;
    return (
      <MainLayout location={this.props.location}>
        <Row>
          <Col span={15}>
            <Tabs
              hideAdd
              onChange={this.onChange}
              tabBarStyle={{ color: '#a3a3a3', boxShadow: '0 0 6px #b8b8b8;' }}
              className={styles.tabPanes}
              tabBarExtraContent={isDescByReadNum}
            >
              {this.state.types.map(type =>
                <TabPane tab={type} key={type}>
                  <NewsList location={this.props.location} type={type} />
                </TabPane>)
              }
            </Tabs>
          </Col>
          <Col span={8}>
            <div className={styles.bar} />
          </Col>
        </Row>
      </MainLayout>
    );
  }
}


export default connect()(News);
