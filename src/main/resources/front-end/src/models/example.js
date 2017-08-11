
export default {

  namespace: 'example',

  state: {},

  /*
  * 监听数据。
  *
  * 用于订阅一个数据源，然后根据需要dispatch相应的action。
  * 格式：({dispatch, history}, done) => unlistenFunction
  * */
  subscriptions: {
    setup({ dispatch, history }) {  // eslint-disable-line
    },
  },

  /*
  * 接收数据。
  *
  * 处理异步操作和业务逻辑，不直接修改state。由action触发，可疑触发action，可以和服务器交互，可以获取全局state的数据等。
  * */
  effects: {
    *fetch({ payload }, { call, put }) {  // eslint-disable-line
    /* 触发action */
      yield put({ type: 'save' });
    },
  },

  /*
  * 处理数据
  *
  * 处理同步操作，唯一可以修改state的地方，由action触发。
  * 格式：(state, action) => newState || [(state, action) => newState, enhancer]
  * */
  reducers: {
    save(state, action) {
      return { ...state, ...action.payload };
    },
  },

};
