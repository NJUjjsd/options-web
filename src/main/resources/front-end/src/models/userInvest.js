/**
 * Created by a297 on 17/9/9.
 */
import * as userInvestService from '../services/userInvestService';

export default {
  namespace: 'userInvest',
  state: {
    email: '664625646@qq.com',
    holding: [],
    noRiskRate: '0.000',
    principal: '0.00',
    assets: '0.00',
    information: [],
  },
  subscriptions: {
    setup({ dispatch, history }) {
      return history.listen(({ pathname, query }) => {
        if (pathname === '/invest/entrust') {
          dispatch({ type: 'fetchHolding', payload: query });
          dispatch({ type: 'fetchInformation', payload: query });
        }
      });
    },
  },
  effects: {
    *fetchHolding({ payload }, { call, put }) {
      const email = '875928078@qq.com';
      const holdingData = yield call(userInvestService.getHoldingData, email);
      yield put({ type: 'saveHolding', payload: holdingData });
    },
    // *fetchInformation({ payload }, { call, put }) {},
  },
  reducers: {
    saveHolding(state, { payload: holding }) {
      return { ...state, holding };
    },
    // saveInformation() {},
  },
};
