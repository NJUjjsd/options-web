/**
 * Created by john on 2017/8/13.
 */
import request from '../utils/request';

export function getNewsList(page) {
  const promise = request(`/api/news/list?page=${page - 1}`);
  return promise.then((v) => {
    const data = v.data;
    const newsList = data.content;
    for (let i = 0; i < newsList.length; i += 1) {
      if (newsList[i].text.length > 160) {
        newsList[i].text = `${newsList[i].text.substring(0, 160)}...`;
      }
    }
    const allNum = data.totalElements;
    return { newsList, allNum };
  });
}
