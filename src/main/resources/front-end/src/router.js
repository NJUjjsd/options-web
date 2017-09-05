import React from 'react';
import { Router, Route } from 'dva/router';
import IndexPage from './routes/Home/Home';
import RealTimeNews from './routes/News/News';
import NewsDetails from './routes/NewsDetails/NewsDetails';
import SearchResult from './routes/SearchResult/SearchResult';
import ETF from './routes/Market/ETF';
import ETFOption from './routes/Market/ETFOption';

function RouterConfig({ browserHistory }) {
  return (
    <Router history={browserHistory}>
      <Route path="/" component={IndexPage} />
      <Route path="/news" components={RealTimeNews} />
      <Route path="/news/details" component={NewsDetails} />
      <Route path="/news/searchResult" component={SearchResult} />
      <Route path="/market/ETF" component={ETF} />
      <Route path="/market/ETFOption" component={ETFOption} />

    </Router>
  );
}

export default RouterConfig;
