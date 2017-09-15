/**
 * Created by a297 on 17/9/15.
 */
import * as service from '../services/websocket';

export default {
  namespace: 'websocket',
  state: {
  },
  subscriptions: {
    socketMessage({ dispatch, history }) {
      return history.listen((pathname) => {
        if (pathname === '/invest/entrust') {
          dispatch({ type: 'open', payload: '' });
        }
      });
    },
  },
  effects: {
    * open({ payload }, { call }) {
      yield call(service.listen, payload);
    },
  },
  reducers: {
  },
};
