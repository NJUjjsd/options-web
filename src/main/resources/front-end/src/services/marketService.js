/**
 * Created by a297 on 17/8/15.
 */
import request from '../utils/request';

export function getETFKLineRawData(tab) {
  const promise = request(`api/market/ETFKLine?tab=${tab}`);
  return promise.then((v) => {
    const data = v.data;
    return turnToRawData(data);
  });
}

export function getETFOptionData() {
  const promise = request('api/market/ETFOption');
  return promise.then((v) => {
    const promiseData = v.data;
    const ETFOptionData = {
      dueMonths: promiseData.dueMonths,
      ETFUpdateTime: promiseData.eTFUpdateTime,
      basicInfo: promiseData.basicInfo,
      contactUpdateTime: promiseData.contactUpdateTime,
      contactInfo: promiseData.contactInfo,
    };
    return ETFOptionData;
  });
}

function turnToRawData(data) {
  const res = [];
  for (let i = 0; i < data.length; i += 1) {
    const each = [];
    each[0] = data[i].date;
    each[1] = data[i].openPrice;
    each[2] = data[i].closePrice;
    each[3] = data[i].lowPrice;
    each[4] = data[i].highPrice;
    each[5] = data[i].turnOver;
    res.push(each);
  }
  return res;
}
