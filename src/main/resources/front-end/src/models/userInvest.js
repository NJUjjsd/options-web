/**
 * Created by a297 on 17/9/9.
 */
import * as userInvestService from '../services/userInvestService';

export default {
  namespace: 'userInvest',
  state: {
    /**
     *  委托
     */
    email: '',
    contractCodeAndName: {},
    // 可用余额
    balance: '',

    /**
     * 个人资产（持有）
     */
    holding: [],
    noRiskRate: '',
    // 本金
    principal: '',
    // 总资产
    assets: '',
    /**
     * 套利提醒
     */
    information: [],
    /**
     * 用户委托
     */
    orders: [],
  },
  subscriptions: {
    setup({ dispatch, history }) {
      return history.listen(({ pathname, query }) => {
        if (pathname === '/invest/entrust') {
          dispatch({ type: 'fetchInvestBasicInfo', payload: query });
          // dispatch({ type: 'openNotification', payload: query });
        }
      });
    },
  },
  effects: {
    *fetchInvestBasicInfo({ payload }, { call, put }) {
      const email = '875928078@qq.com';
      const investBasicInfo = yield call(userInvestService.getInvestBasicInfo, email);
      yield put({ type: 'saveInvestBasicInfo', payload: investBasicInfo });
    },
    *openNotification({ payload }, { call }) {
      const path = 'http://localhost:8000';
      const email = '875928078@qq.com';
      yield call(userInvestService.getNotification, { path, email });
    },
    *userEntrust({ payload: content }, { call }) {
      const email = '875928078@qq.com';
      Object.assign(content, { email });
      const userEntrustResult = yield call(userInvestService.userEntrust, content);
      console.log(userEntrustResult);
    },
    *combinationEntrust({ payload: content }, { call }) {
      const email = '875928078@qq.com';
      Object.assign(content, { email });
      const combinationEntrustResult = yield call(userInvestService.combinationEntrust, content);
      console.log(combinationEntrustResult);
    },
  },
  reducers: {
    saveInvestBasicInfo(state, { payload: investBasicInfo }) {
      return { ...state, ...investBasicInfo };
    },
  },
};
