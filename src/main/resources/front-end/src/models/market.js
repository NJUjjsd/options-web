/**
 * Created by a297 on 17/8/15.
 */
import * as marketService from '../services/marketService';

export default {
  namespace: 'market',
  state: {
    ETFKLineRawData: [],
    ETFKLineTab: 'daily',
    dueMonthList: [],
    ETFTime: '',
    ETFBasicInfo: [],
    transactionTime: [],
    optionFormData: [],
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
    *fetchETFKLineRawData({ payload: { tab = 'daily' } }, { call, put }) {
      const ETFKLineRawData = yield call(marketService.getETFKLineRawData, tab);
      yield put({ type: 'saveETFKLineRawData', payload: ETFKLineRawData });
      yield put({ type: 'saveETFKLineTab', payload: tab });
    },
    *fetchETFOptionData({ payload }, { call, put }) {
      const
        { dueMonthList, ETFTime, ETFBasicInfo,
          transactionTime, optionFormData, selectedMonthIndex } =
        yield call(marketService.getETFOptionData);
      yield put({
        type: 'saveETFOptionData',
        payload: {
          dueMonthList,
          ETFTime,
          ETFBasicInfo,
          transactionTime,
          optionFormData,
          selectedMonthIndex,
        },
      });
    },
    *changeSelectedMonthIndex({ payload: { selectedMonthIndex } }, { put }) {
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
        dueMonthList,
        ETFTime,
        ETFBasicInfo,
        transactionTime,
        optionFormData,
        selectedMonthIndex,
      },
    }) {
      return {
        ...state,
        dueMonthList,
        ETFTime,
        ETFBasicInfo,
        transactionTime,
        optionFormData,
        selectedMonthIndex,
      };
    },
    saveSelectedMonthIndex(state, { payload: { selectedMonthIndex } }) {
      return { ...state, selectedMonthIndex };
    },
  },
};
