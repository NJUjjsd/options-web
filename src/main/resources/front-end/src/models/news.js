/**
 * Created by john on 2017/8/13.
 */
import { routerRedux } from 'dva/router';
import * as newsService from '../services/newsService';

export default {
  namespace: 'news',
  state: {
    newsList: [],
    allNum: 0,
    current: 1,
    detail: {},
  },

  subscriptions: {
    setup({ dispatch, history }) {
      return history.listen(({ pathname, query }) => {
        if (pathname === '/news') {
          dispatch({ type: 'getList', payload: query });
        }
      });
    },
  },

  effects: {
    * getList({ payload: { page = 1 } }, { call, put }) {
      const { newsList, allNum } = yield call(newsService.getNewsList, page);
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
      yield put(routerRedux.push('/newsDetails'));
    },
  },

  reducers: {
    saveList(state, { payload: { newsList, allNum, current } }) {
      return { ...state, newsList, allNum, current };
    },
    saveDetail(state, { payload: { detail } }) {
      return { ...state, detail };
    },
  },
};
