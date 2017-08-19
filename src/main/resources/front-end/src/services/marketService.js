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
  // todo 封装拿到的数据; 界面首字母大写的属性改成小写
  // dueMonths list
  // ETF basic info list
  // contact info update time list
  // contact info list
  return {
    dueMonthList: ['2017-08', '2017-09', '2017-12', '2018-03'],
    ETFTime: '2017-08-17 15:15:04',
    ETFBasicInfo: [{
      key: '1',
      code: '510050',
      name: '上证50ETF',
      curPrice: '2.645',
      fluPrice: '-0.007',
      fluPercent: '-0.26%',
      amplitude: '0.26%',
      turnOver: '258921',
      transaction: '6851.636',
    }],
    transactionTime: ['2017-08-17 15:15:04', '2017-08-17 15:15:05', '2017-08-17 15:15:06', '2017-08-17 15:15:07'],
    optionFormData: [{
      key: '1',
      UpTradingCode: '510050C1708M02450',
      UpCurrentPrice: 0.2017,
      UpFluctuation: '6.16%',
      UpPreClosingPrice: 0.1900,
      UpExercisePrice: 2.450,
      DownTradingCode: '510050P1708M02450',
      DownCurrentPrice: 0.0002,
      DownFluctuation: '-33.33%',
      DownPreClosingPrice: 0.0003,
    }, {
      key: '2',
      UpTradingCode: '510050C1708M02450',
      UpCurrentPrice: 0.2017,
      UpFluctuation: '6.16%',
      UpPreClosingPrice: 0.1900,
      UpExercisePrice: 2.450,
      DownTradingCode: '510050P1708M02450',
      DownCurrentPrice: 0.0002,
      DownFluctuation: '-33.33%',
      DownPreClosingPrice: 0.0003,
    }],
  };
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
