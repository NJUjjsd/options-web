/**
 * Created by john on 2017/9/13.
 */
import * as userService from '../services/userService';

export default {
  namespace: 'email',
  state: {
    status: '',
  },

  subscriptions: {
    setup({ dispatch, history }) {
      return history.listen(({ pathname, query }) => {
        if (pathname === '/users/activatemail') {
          const account = {
            token: query.token,
            email: query.email,
          };
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
      const status = yield call(userService.activatemail, account);
      console.log(status);
      yield put({
        type: 'saveUser',
        payload: { status },
      });
    },
  },

  reducers: {
    saveUser(state, { payload: { status } }) {
      return { ...state, status };
    },
  },
};
