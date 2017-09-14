/**
 * Created by john on 2017/9/13.
 */
import * as userService from '../services/userService';

export default {
  namespace: 'email',
  state: {
    result: {},
  },

  subscriptions: {
    setup({ dispatch, history }) {
      return history.listen(({ pathname, query }) => {
        if (pathname === '/users/activatemail') {
          const account = {
            token: query.token,
            email: query.email,
          };
          console.log(account);
          dispatch({
            type: 'activatemail',
            payload: { account },
          });
        }
      });
    },
  },

  effects: {
    * activatemail({ payload: { account } }, { call, put }) {
      const result = yield call(userService.activatemail, account);

      yield put({
        type: 'saveUser',
        payload: { result },
      });
    },
  },

  reducers: {
    saveUser(state, { payload: { result } }) {
      return { ...state, result };
    },
  },
};
