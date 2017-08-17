import React from 'react';
import { Router, Route } from 'dva/router';
import IndexPage from './routes/Home/Home';
import RealTimeNews from './routes/News/News';
import NewsDetails from './routes/NewsDetails/NewsDetails';
import Market from './routes/Market/Market';

function RouterConfig({ browserHistory }) {
  return (
    <Router history={browserHistory}>
      <Route path="/" component={IndexPage} />
      <Route path="/news" components={RealTimeNews} />
      <Route path="/news/details" component={NewsDetails} />
      <Route path="/market" components={Market} />
    </Router>
  );
}

export default RouterConfig;
