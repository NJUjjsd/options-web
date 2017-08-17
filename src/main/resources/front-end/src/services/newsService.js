/**
 * Created by john on 2017/8/13.
 */
import request from '../utils/request';

export function searchNews(page, pageSize, keyword) {
  const promise = request(`/api/news/search?page=${page - 1}&&pageSize=${pageSize}&&keyword=${keyword}`);
  return promise.then((v) => {
    const data = v.data;
    const newsList = data.content;
    const allNum = data.totalElements;
    return { newsList, allNum };
  });
}

export function getClassifiedNews(page, pageSize, code, type, isDescByReadNum) {
  const promise = request(`/api/news/classifiedNews?page=${page - 1}&&pageSize=${pageSize}&&code=${code}&&type=${type}&&isDescByReadNum=${isDescByReadNum}`);
  return promise.then((v) => {
    const data = v.data;
    const newsList = data.content;
    const allNum = data.totalElements;
    return { newsList, allNum };
  });
}

export function getStockCode() {
  const promise = request('/api/news/stockCode');
  return promise.then((v) => {
    const stockCode = v.data;
    return { stockCode };
  });
}

