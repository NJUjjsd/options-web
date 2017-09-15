/**
 * Created by a297 on 17/9/15.
 */
import * as service from '../services/websocket';

export default {
  namespace: 'websocket',
  state: {
    notificationMessage: [],
  },
  subscriptions: {
    setup({ dispatch, history }) {
      return history.listen(({ pathname }) => {
        if (pathname === '/invest/entrust') {
          console.log('setup in websocket');
          dispatch({ type: 'open', payload: '' });
        }
      });
    },
  },
  effects: {
    * open({ payload }, { call, put }) {
      console.log('to open websocket in effects');
      const email = window.sessionStorage.getItem('email');
      const url = 'ws://localhost:8080/websocket';
      const notificationMessage = yield call(service.openOperation, { email, url });
      yield put({ type: 'saveNotificationMessage' }, notificationMessage);
    },
  },
  reducers: {
    saveNotificationMessage(state, { payload: notificationMessage }) {
      console.log(' save message in reducers ', notificationMessage);
      return { ...state, notificationMessage };
    },
  },
};
