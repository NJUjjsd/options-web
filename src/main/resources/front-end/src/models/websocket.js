/**
 * Created by a297 on 17/9/15.
 */
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
          const email = window.sessionStorage.getItem('email');
          const url = 'ws://localhost:8080/websocket';
          const ws = new WebSocket(url);
          ws.onopen = () => {
            console.log('socket open');
            ws.send(email);
          };
          ws.onerror = (e) => {
            console.log('socket error', e.message);
          };
          ws.onclose = (e) => {
            console.log(e.code, e.reason);
          };
          ws.onmessage = (e) => {
            const message = e.data;
            console.log('onmessage:', message);
            dispatch({ type: 'saveNotificationMessage', payload: { message } });
          };
        }
      });
    },
  },
  effects: {},
  reducers: {
    saveNotificationMessage(state, { payload: { message } }) {
      const notificationMessage = JSON.parse(message);
      // const notificationMessage = message;
      // ArrayList from behind
      console.log(' save message in reducers ', notificationMessage);
      console.log(' save message in reducers type of it ', typeof notificationMessage);
      return { ...state, notificationMessage };
    },
  },
};
