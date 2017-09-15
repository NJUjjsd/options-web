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
      const email = window.sessionStorage.getItem('email');
      const investBasicInfo = yield call(userInvestService.getInvestBasicInfo, email);
      yield put({ type: 'saveInvestBasicInfo', payload: investBasicInfo });
    },
    // * openNotification({ payload }, { call, put }) {
    //   console.log('openNotification in effects');
    //   const email = window.sessionStorage.getItem('email');
    //
    //
    //   const info = yield call(userInvestService.getNotification, { email });
    //   yield put({ type: 'saveInformation', payload: info });
    // },
    * userEntrust({ payload: content }, { call }) {
      const email = window.sessionStorage.getItem('email');
      Object.assign(content, { email });
      const userEntrustResult = yield call(userInvestService.userEntrust, content);
      console.log(userEntrustResult);
    },
    * combinationEntrust({ payload: content }, { call }) {
      const email = window.sessionStorage.getItem('email');
      Object.assign(content, { email });
      const combinationEntrustResult = yield call(userInvestService.combinationEntrust, content);
      console.log(combinationEntrustResult);
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
    saveUserEntrust(state, { payload: userEntrust }) {
      console.log('reducers', userEntrust);
      return { ...state, userEntrust };
    },
    // saveInformation(state, { payload: info }) {
    //   console.log(info);
    //   const information = [{
    //     code: '510050',
    //     optionName: '上证50ETF',
    //     price: '2.3',
    //     optionNum: '130000',
    //     isBuy: true,
    //
    //     upCode: '510050C1709M02250',
    //     upOptionName: 'name1',
    //     upPrice: '2.1',
    //     upOptionNum: '100',
    //     upIsBuy: true,
    //
    //     downCode: '510050P1709M02250',
    //     downOptionName: 'name2',
    //     downPrice: '2.3',
    //     downOptionNum: '130000',
    //     downIsBuy: false,
    //
    //     totalProfit: '2333333',
    //   }];
    //   console.log('reducers');
    //   return { ...state, information };
    // },
  },
};
