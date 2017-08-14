/**
 * Created by john on 2017/8/13.
 */
import * as newsService from '../services/newsService';

export default {
  namespace: 'newsList',
  state: {
    newsList: [],
    allNum: 0,
    current: 1,
  },

  subscriptions: {
    setup({ dispatch, history }) {
      return history.listen(({ pathname, query }) => {
        if (pathname === '/news') {
          dispatch({ type: 'fetch', payload: query });
        }
      });
    },
  },

  effects: {
    * fetch({ payload: { page = 1 } }, { call, put }) {
      const { newsList, allNum } = yield call(newsService.getNewsList, page);
      yield put({
        type: 'save',
        payload: {
          newsList,
          allNum,
          current: page,
        },
      });
    },
  },

  reducers: {
    save(state, { payload: { newsList, allNum, current } }) {
      return { ...state, newsList, allNum, current };
    },
  },
};
