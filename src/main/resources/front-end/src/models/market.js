/**
 * Created by a297 on 17/8/15.
 */
import * as marketService from '../services/marketService';

export default {
  namespace: 'market',
  state: {
    ETFKLineRawData: [],
    ETFKLineTab: 'daily',
  },

  subscriptions: {
    setup({ dispatch, history }) {
      return history.listen(({ pathname, query }) => {
        if (pathname === '/market') {
          dispatch({ type: 'fetchETFKLineRawData', payload: query });
        }
      });
    },
  },

  effects: {
    *fetchETFKLineRawData({ payload: { tab = 'daily' } }, { call, put }) {
      const ETFKLineRawData = yield call(marketService.getETFKLineRawData, tab);
      yield put({ type: 'saveETFKLineRawData', payload: ETFKLineRawData });
      yield put({ type: 'saveETFKLineTab', payload: tab });
    },
  },

  reducers: {
    saveETFKLineRawData(state, { payload: ETFKLineRawData }) {
      return { ...state, ETFKLineRawData };
    },
    saveETFKLineTab(state, { payload: ETFKLineTab }) {
      return { ...state, ETFKLineTab };
    },
  },
};
