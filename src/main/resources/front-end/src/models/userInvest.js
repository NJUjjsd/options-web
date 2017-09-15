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
     * 用户委托
     */
    userEntrust: [],

    /**
     * 通知
     */
    information: [],
  },
  subscriptions: {
    setup({ dispatch, history }) {
      return history.listen(({ pathname, query }) => {
        if (pathname === '/invest/entrust') {
          dispatch({ type: 'fetchInvestBasicInfo', payload: query });
        } else if (pathname === '/invest/cancel') {
          dispatch({ type: 'fetchUserEntrust', payload: query });
        }
      });
    },
  },
  effects: {
    * fetchInvestBasicInfo({ payload }, { call, put }) {
      console.log('this is fetch info in effects');
      const email = window.sessionStorage.getItem('email');
      const investBasicInfo = yield call(userInvestService.getInvestBasicInfo, email);
      yield put({ type: 'saveInvestBasicInfo', payload: investBasicInfo });
    },
    * fetchInformation({ payload }, { call, put }) {
      console.log('定时器啦啦啦啦');
      const email = window.sessionStorage.getItem('email');
      const informaiton = yield call(userInvestService.getInformation, email);
      yield put({ type: 'saveInformation', payload: informaiton });
    },
    * userEntrust({ payload: content }, { call }) {
      const email = window.sessionStorage.getItem('email');
      Object.assign(content, { email });
      const userEntrustResult = yield call(userInvestService.userEntrust, content);
      console.log(userEntrustResult);
    },
    * fetchUserEntrust({ payload }, { call, put }) {
      console.log('user entrust in effects');
      const email = window.sessionStorage.getItem('email');
      const userEntrust = yield call(userInvestService.getUserEntrust, email);
      yield put({ type: 'saveUserEntrust', payload: userEntrust });
    },
    * cancelEntrust({ payload: list }, { call }) {
      console.log('cancel in  effects');
      yield call(userInvestService.cancelEntrust, list);
    },
  },
  reducers: {
    saveInvestBasicInfo(state, { payload: investBasicInfo }) {
      return { ...state, ...investBasicInfo };
    },
    saveInformation(state, { payload: information }) {
      return { ...state, information };
    },
    saveUserEntrust(state, { payload: userEntrust }) {
      console.log('reducers', userEntrust);
      return { ...state, userEntrust };
    },
  },
};
