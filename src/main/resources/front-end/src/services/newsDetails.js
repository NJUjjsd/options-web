/**
 * Created by a297 on 17/8/13.
 */

import request from '../utils/request';

export function getNewsDetailsByID(news) {
  const promise = request(`/api/newsDetails?newsID=${news.newsID}`);
  return (promise.then(
    (v) => {
      return v.data;
    },
  ));
}
