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
      return history.listen(({ pathname, query }) => {
        if (pathname === '/users/basicInfo') {
          dispatch({
            type: 'getUserInfo',
            payload: {
              // email: window.sessionStorage.getItem('email'),
              email: '1512592323@qq.com',
            },
          });
        } else if (pathname === '/users/activatemail') {
          console.log(query.token);
          console.log(query.email);
        }
      });
    },
  },

  effects: {
    * getUserInfo({ payload: { email } }, { call, put }) {
      console.log(Encrypt(email));
      const { user } = yield call(userService.getUserInfo, Encrypt(email));
      yield put({
        type: 'saveUser',
        payload: { user },
      });
    },

    * signUp({ payload: { account } }, { call }) {
      const { result } = yield call(userService.signUp, account);
      console.log(result);
      if (result) {
        //  todo
      }
    },

    * login({ payload: { account } }, { call }) {
      const { result } = yield call(userService.login, account);
      if (result) {
        //  todo
      }
    },
  },

  reducers: {
    saveUser(state, { payload: { user } }) {
      return { ...state, user };
    },
  },
};
