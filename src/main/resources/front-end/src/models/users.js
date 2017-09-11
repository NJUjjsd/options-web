/**
 * Created by john on 2017/9/10.
 */
import * as userService from '../services/userService';
import { Encrypt } from '../utils/aes';

export default {
  namespace: 'users',
  state: {
    user: {},
  },

  subscriptions: {
    setup({ dispatch, history }) {
      return history.listen(({ pathname }) => {
        if (pathname === '/users/basicInfo') {
          dispatch({
            type: 'getUserInfo',
            payload: {
              // email: window.sessionStorage.getItem('email'),
              email: '1512592323@qq.com',
            },
          });
        }
      });
    },
  },

  effects: {
    * getUserInfo({ payload: { email } }, { call, put }) {
      const { user } = yield call(userService.getUserInfo,
        (Encrypt(encodeURI(email))));
      yield put({
        type: 'saveUser',
        payload: { user },
      });
    },
  },

  reducers: {
    saveUser(state, { payload: { user } }) {
      return { ...state, user };
    },
  },
};
