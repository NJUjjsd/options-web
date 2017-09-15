/**
 * Created by john on 2017/9/10.
 */
import { routerRedux } from 'dva/router';
import { Modal } from 'antd';
import * as userService from '../services/userService';

export default {
  namespace: 'users',
  state: {
    user: {},
    loginResult: {},
    isLogin: (window.sessionStorage.getItem('email') !== null),
    signUpResult: {},
    showModal: false,
  },

  subscriptions: {
    setup({ dispatch, history }) {
      return history.listen(({ pathname }) => {
        if (pathname === '/users/basicInfo') {
          const account = {
            email: window.sessionStorage.getItem('email'),
          };
          dispatch({
            type: 'getUserInfo',
            payload: { account },
          });
        }
      });
    },
  },

  effects: {
    * getUserInfo({ payload: { account } }, { call, put }) {
      console.log('getUserInfo');
      const result = yield call(userService.getUserInfo, account);
      console.log(result);
      const user = result.result;
      yield put({
        type: 'saveUser',
        payload: { user },
      });
      if (result.status !== 0) {
        const message = result.message;
        Modal.warning({
          content: message,
        });
      }
    },

    * modifyUserInfo({ payload: { user } }, { call, put }) {
      console.log('modifyUserInfo');
      const result = yield call(userService.modifyUserInfo, user);
      const message = result.message;
      if (result.status === 0) {
        const flag = {
          isSetCost: true,
          isSetProperty: true,
        };
        const newUser = { ...user, ...flag };
        console.log(newUser);
        yield put({
          type: 'saveUser',
          payload: { user },
        });
        Modal.success({
          content: message,
        });
      } else {
        Modal.error({
          content: message,
        });
      }
    },

    * changePassword({ payload: { values } }, { call }) {
      console.log('changePassword');
      const pswObj = {
        email: window.sessionStorage.getItem('email'),
        prePassword: values.prePassword,
        newPassword: values.password,
      };
      const result = yield call(userService.changePassword, pswObj);
      const message = result.message;
      if (result.status === 0) {
        Modal.success({
          content: message,
        });
      } else {
        Modal.error({
          content: message,
        });
      }
    },

    * signUp({ payload: { account } }, { call, put }) {
      console.log(window.sessionStorage.getItem('email'));
      const signUpResult = yield call(userService.signUp, account);
      yield put({
        type: 'saveSignUpResult',
        payload: { signUpResult },
      });
    },

    * clearSignUpResult({ payload }, { put }) {
      yield put({
        type: 'saveSignUpResult',
        payload: {
          signUpResult: {},
        },
      });
    },

    * clearLoginResult({ payload }, { put }) {
      yield put({
        type: 'saveLoginResult',
        payload: {
          loginResult: {},
        },
      });
    },

    * clearShowModal({ payload }, { put }) {
      yield put({
        type: 'saveShowModal',
        payload: {
          showModal: false,
        },
      });
    },

    * logout({ payload }, { put }) {
      window.sessionStorage.removeItem('email');
      console.log(window.sessionStorage.getItem('email') === null);
      //  抹去用户的信息
      yield put({
        type: 'saveIsLogin',
        payload: {
          isLogin: false,
        },
      });
      yield put({
        type: 'saveUser',
        payload: {
          user: {},
        },
      });
      //  跳转到首页
      yield put(routerRedux.push({
        pathname: '/',
      }));
    },

    * login({ payload: { account } }, { call, put }) {
      yield put({
        type: 'saveShowModal',
        payload: {
          showModal: true,
        },
      });
      const loginResult = yield call(userService.login, account);
      yield put({
        type: 'saveLoginResult',
        payload: { loginResult },
      });
      //  如果登录成功
      if (loginResult.result) {
        yield put({
          type: 'saveShowModal',
          payload: {
            showModal: false,
          },
        });
        window.sessionStorage.setItem('email', account.email);
        yield put({
          type: 'saveIsLogin',
          payload: {
            isLogin: true,
          },
        });
        yield put(routerRedux.push({
          pathname: '/',
        }));
        yield put({
          type: 'saveLoginResult',
          payload: {
            loginResult: {},
          },
        });
      }
    },
  },

  reducers: {
    saveUser(state, { payload: { user } }) {
      return { ...state, user };
    },
    saveSignUpResult(state, { payload: { signUpResult } }) {
      return { ...state, signUpResult };
    },
    saveLoginResult(state, { payload: { loginResult } }) {
      return { ...state, loginResult };
    },
    saveIsLogin(state, { payload: { isLogin } }) {
      return { ...state, isLogin };
    },
    saveShowModal(state, { payload: { showModal } }) {
      return { ...state, showModal };
    },
  },
};
