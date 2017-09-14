/**
 * Created by a297 on 17/8/15.
 */
import * as marketService from '../services/marketService';

export default {
  namespace: 'market',
  state: {
    ETFKLineRawData: [],
    ETFKLineTab: 'monthly',
    dueMonths: [],
    ETFUpdateTime: '',
    basicInfo: {},
    contactUpdateTime: [],
    contactInfo: [],
    selectedMonthIndex: 0,
  },

  subscriptions: {
    setup({ dispatch, history }) {
      return history.listen(({ pathname, query }) => {
        if (pathname === '/market/ETF') {
          dispatch({ type: 'fetchETFKLineRawData', payload: query });
        } else if (pathname === '/market/ETFOption') {
          dispatch({ type: 'fetchETFOptionData', payload: query });
        }
      });
    },
  },

  effects: {
    * fetchETFKLineRawData({ payload: { tab = 'monthly' } }, { call, put }) {
      const ETFKLineRawData = yield call(marketService.getETFKLineRawData, tab);
      yield put({ type: 'saveETFKLineRawData', payload: ETFKLineRawData });
      yield put({ type: 'saveETFKLineTab', payload: tab });
    },
    * fetchETFOptionData({ payload }, { call, put }) {
      const
        { dueMonths, ETFUpdateTime, basicInfo,
          contactUpdateTime, contactInfo } =
        yield call(marketService.getETFOptionData);
      yield put({
        type: 'saveETFOptionData',
        payload: {
          dueMonths,
          ETFUpdateTime,
          basicInfo,
          contactUpdateTime,
          contactInfo,
        },
      });
    },
    * changeSelectedMonthIndex({ payload: { selectedMonthIndex } }, { put }) {
      yield put({
        type: 'saveSelectedMonthIndex', payload: { selectedMonthIndex },
      });
    },
  },

  reducers: {
    saveETFKLineRawData(state, { payload: ETFKLineRawData }) {
      return { ...state, ETFKLineRawData };
    },
    saveETFKLineTab(state, { payload: ETFKLineTab }) {
      return { ...state, ETFKLineTab };
    },
    saveETFOptionData(state, {
      payload: {
        dueMonths,
        ETFUpdateTime,
        basicInfo,
        contactUpdateTime,
        contactInfo,
      },
    }) {
      return {
        ...state,
        dueMonths,
        ETFUpdateTime,
        basicInfo,
        contactUpdateTime,
        contactInfo,
      };
    },
    saveSelectedMonthIndex(state, { payload: { selectedMonthIndex } }) {
      return { ...state, selectedMonthIndex };
    },
  },
};
