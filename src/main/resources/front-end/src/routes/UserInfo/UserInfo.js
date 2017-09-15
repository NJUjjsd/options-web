/**
 * Created by john on 2017/9/10.
 */
import React from 'react';
import { connect } from 'dva';
import { Input, Button, Row, Col, Form, Slider, InputNumber, Modal, Icon } from 'antd';
import { routerRedux } from 'dva/router';
import MainLayout from '../../components/MainLayout/MainLayout';
import { changePasswordPath } from '../../constant';
import styles from './UserInfo.less';
import HoldingTable from '../../components/HoldingTable/HodingTable';

const FormItem = Form.Item;

class UserInfo extends React.Component {
  toChangePassword = () => {
    this.props.dispatch(routerRedux.push({
      pathname: '/users/changePassword',
      query: {
        path: changePasswordPath,
      },
    }));
  };
  //  表单提交
  save = () => {
    this.props.form.validateFields((err, values) => {
      if (err) {
        Modal.error({
          title: '修改失败',
          content: '请检查您是否有未填写的项以及输入格式是否正确',
        });
      } else {
        const user = { ...this.props.user, ...values };
        console.log(user);
        this.props.dispatch({
          type: 'users/modifyUserInfo',
          payload: { user },
        });
      }
    });
  };
  render() {
    const location = this.props.location;
    const { getFieldDecorator } = this.props.form;
    const user = this.props.user;
    const inputStyle = {
      paddingLeft: 42,
      width: 320,
    };
    const marks = { 0: '0', 0.025: '0.025', 0.05: '0.05', 0.075: '0.075', 0.1: '0.1' };
    return (
      <MainLayout location={location}>
        <Row style={{ marginTop: 54, marginBottom: 100 }}>
          <Col offset={2} span={14}>
            <Form>
              <Row className={styles.info}>
                <Col span={12}>
                  <div className={styles.headStyle}>基本资料</div>
                  <FormItem
                    style={{ marginTop: 24 }}
                  >
                    {getFieldDecorator('userName', {
                      rules: [{ required: true, message: '昵称不能为空' }],
                      initialValue: user.userName,
                    })(
                      <Input prefix={<label htmlFor="userName">昵称：</label>} style={inputStyle} />,
                    )}
                  </FormItem>
                  <div
                    style={{ marginLeft: 8, fontSize: 14, marginTop: 30 }}
                  >
                    邮箱：&nbsp;&nbsp;&nbsp;&nbsp;{user.email}
                  </div>
                </Col>
                <Col span={12}>
                  <div style={{ marginTop: 65, fontSize: 14 }}>
                    修改<Icon type="edit" />信息记得拉到底部保存哦！
                  </div>
                  <Button
                    className={styles.changePassword}
                    onClick={this.toChangePassword.bind(this)}
                  >
                    修改密码
                  </Button>
                </Col>
              </Row>
              <div className={styles.cost} style={{ marginTop: 50 }}>
                <div style={{ fontSize: 24, fontWeight: 500 }}>交易成本</div>
                <div style={{ marginTop: 26 }}>
                  <Row>
                    <Col span={12} className={styles.buyinHeader}>买入（元／张）</Col>
                    <Col span={12} className={styles.buyoutHeader}>卖出（元／张）</Col>
                  </Row>
                  <Row>
                    <Col span={6} className={styles.label}>认沽</Col>
                    <Col span={6} className={styles.inputLabel}>
                      <FormItem>
                        {getFieldDecorator('buyPut', {
                          rules: [{ required: true, message: ' ' }],
                          initialValue: user.buyPut,
                        })(
                          <Input className={styles.input} onChange={this.handleChange} />,
                        )}
                      </FormItem>
                    </Col>
                    <Col span={6} className={styles.label}>认沽</Col>
                    <Col span={6} className={styles.inputLabel}>
                      <FormItem>
                        {getFieldDecorator('sellPut', {
                          rules: [{ required: true, message: ' ' }],
                          initialValue: user.sellPut,
                        })(
                          <Input className={styles.input} onChange={this.handleChange} />,
                        )}
                      </FormItem>
                    </Col>
                  </Row>
                  <Row>
                    <Col span={6} className={styles.label}>认购</Col>
                    <Col span={6} className={styles.inputLabel}>
                      <FormItem>
                        {getFieldDecorator('buySubscribe', {
                          rules: [{ required: true, message: ' ' }],
                          initialValue: user.buySubscribe,
                        })(
                          <Input className={styles.input} onChange={this.handleChange} />,
                        )}
                      </FormItem>
                    </Col>
                    <Col span={6} className={styles.label}>认购</Col>
                    <Col span={6} className={styles.inputLabel}>
                      <FormItem>
                        {getFieldDecorator('sellSubscribe', {
                          rules: [{ required: true, message: ' ' }],
                          initialValue: user.sellSubscribe,
                        })(
                          <Input className={styles.input} onChange={this.handleChange} />,
                        )}
                      </FormItem>
                    </Col>
                  </Row>
                  <Row>
                    <Col span={6} className={styles.label}>ETF</Col>
                    <Col span={6} className={styles.inputLabel}>
                      <FormItem>
                        {getFieldDecorator('buyETF', {
                          rules: [{ required: true, message: ' ' }],
                          initialValue: user.buyETF,
                        })(
                          <Input className={styles.input} onChange={this.handleChange} />,
                        )}
                      </FormItem>
                    </Col>
                    <Col span={6} className={styles.label}>ETF</Col>
                    <Col span={6} className={styles.inputLabel}>
                      <FormItem>
                        {getFieldDecorator('sellETF', {
                          rules: [{ required: true, message: ' ' }],
                          initialValue: user.sellETF,
                        })(
                          <Input className={styles.input} onChange={this.handleChange} />,
                        )}
                      </FormItem>
                    </Col>
                  </Row>
                </div>
              </div>
              <div className={styles.holding} style={{ marginTop: 50 }}>
                <div style={{ fontSize: 24, fontWeight: 500 }}>目前持有</div>
                <div style={{ marginTop: 32 }}>
                  <HoldingTable />
                  <div className={styles.inputPart} style={{ marginTop: 24 }}>
                    <Row>
                      <Col span={5} className={styles.riskRate}>最高无风险利率：</Col>
                      <Col span={8}>
                        {getFieldDecorator('riskRate', {
                          rules: [{ required: true, message: '请输入最低风险利率' }],
                          initialValue: user.riskRate,
                        })(
                          <Slider
                            min={0}
                            max={0.1}
                            step={0.001}
                            marks={marks}
                          />,
                        )}
                      </Col>
                    </Row>
                    <Row style={{ marginTop: 14 }}>
                      <Col span={3}>本金: </Col>
                      <Col span={5}>
                        <FormItem style={{ marginLeft: -18 }} >
                          {getFieldDecorator('capital', {
                            rules: [{ required: true, message: '请输入成本' }],
                            initialValue: user.capital,
                          })(
                            <InputNumber className={styles.input} />,
                          )}
                        </FormItem>
                      </Col>
                      <Col span={1}>元</Col>
                    </Row>
                    <div>
                      总资产：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{user.total}
                      <span style={{ marginLeft: 20 }}>元</span>
                    </div>
                  </div>
                </div>
              </div>
              <div style={{ marginTop: 56 }}>
                <Button className={styles.save} onClick={this.save.bind(this)}>保存</Button>
              </div>
            </Form>
          </Col>
        </Row>
      </MainLayout>
    );
  }
}

function mapStateToProps(state) {
  const { user } = state.users;
  return { user };
}

const BasicInfo = Form.create()(UserInfo);

export default connect(mapStateToProps)(BasicInfo);
