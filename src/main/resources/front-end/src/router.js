import React from 'react';
import { Router, Route } from 'dva/router';
import IndexPage from './routes/IndexPage/IndexPage';

function RouterConfig({ browserHistory }) {
  return (
    <Router history={browserHistory}>
      <Route path="/" component={IndexPage} />
    </Router>
  );
}

export default RouterConfig;
