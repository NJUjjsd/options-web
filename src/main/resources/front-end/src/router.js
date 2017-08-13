import React from 'react';
import { Router, Route } from 'dva/router';
import IndexPage from './routes/IndexPage/IndexPage';
import RealTimeNews from './routes/RealTimeNews/RealTimeNews';
import NewsDetails from './routes/NewsDetails/NewsDetails';

function RouterConfig({ browserHistory }) {
  return (
    <Router history={browserHistory}>
      <Route path="/" component={IndexPage} />
      <Route path="/real_time_news" components={RealTimeNews} />
      <Route path="/newsDetails" component={NewsDetails} />
    </Router>
  );
}

export default RouterConfig;
