/**
 * Created by john on 2017/8/13.
 */
import { routerRedux } from 'dva/router';
import { defaultPage, defaultPageSize, defaultIsDescByReadNum } from '../constant';
import * as newsService from '../services/newsService';

export default {
  namespace: 'news',
  state: {
    newsList: [],
    allNum: 0,
    current: 1,
    detail: {},
    stockCode: {},
  },

  subscriptions: {
    setup({ dispatch, history }) {
      return history.listen(({ pathname, query }) => {
        if (pathname === '/news') {
          dispatch({
            type: 'getClassifiedNews',
            payload: {
              page: defaultPage,
              pageSize: defaultPageSize,
              code: query.code,
              type: query.type,
              isDescByReadNum: defaultIsDescByReadNum,
            },
          });
          dispatch(
            { type: 'getStockCode' },
          );
        }
      });
    },
  },

  effects: {
    * getList({ payload: { page = 1, pageSize = 6, keyword = '上证50ETF(510050)' } }, { call, put }) {
      const { newsList, allNum } = yield call(newsService.searchNews, page, pageSize, keyword);
      yield put({
        type: 'saveList',
        payload: {
          newsList,
          allNum,
          current: page,
        },
      });
    },
    * putDetail({ payload: { data } }, { put }) {
      yield put({
        type: 'saveDetail',
        payload: {
          detail: data,
        },
      });
      yield put(routerRedux.push('/news/details'));
    },
    * getStockCode({ payload }, { call, put }) {
      const { stockCode } = yield call(newsService.getStockCode);
      yield put({
        type: 'saveStockCode',
        payload: {
          stockCode,
        },
      });
    },
    * getClassifiedNews(
      { payload: { page, pageSize, code, type, isDescByReadNum } }, { call, put },
      ) {
      const { newsList, allNum } = yield call(
        newsService.getClassifiedNews, page, pageSize, code, type, isDescByReadNum,
      );
      yield put({
        type: 'saveList',
        payload: {
          newsList,
          allNum,
          current: page,
        },
      });
    },
  },

  reducers: {
    saveList(state, { payload: { newsList, allNum, current } }) {
      return { ...state, newsList, allNum, current };
    },
    saveDetail(state, { payload: { detail } }) {
      return { ...state, detail };
    },
    saveStockCode(state, { payload: { stockCode } }) {
      return { ...state, stockCode };
    },
  },
};
