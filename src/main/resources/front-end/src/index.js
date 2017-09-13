import dva from 'dva';
import browserHistory from 'dva/router';
import createLoading from 'dva-loading';
import 'antd/dist/antd.less';
import './index.css';

// 1. Initialize
const app = dva({
  history: browserHistory,
  // onError(e){
  //
  // }
});

// 2. Plugins
app.use(createLoading());

// 3. Model
// app.model(require('./models/example'));
app.model(require('./models/news'));
app.model(require('./models/market'));
app.model(require('./models/userInvest'));
app.model(require('./models/users'));
app.model(require('./models/email'));

// 4. Router
app.router(require('./router'));


// 5. Start
app.start('#root');
