/**
 * Created by a297 on 17/9/9.
 */
import request from '../utils/request';

export function getHoldingData(email) {
  const promise = request(`api/invest/holding?email=${email}}`);
  return promise.then((v) => {
    const holding = v.data;
    return holding;
  });
  // return [{
  //   code: '000000C1709M02250',
  //   name: '50ETF购9月2800',
  //   number: '1000',
  //   numberToSell: '200',
  //   currentPrice: '2.45',
  //   fluctuation: '+0.34',
  //   profitAndLoss: '+10000',
  // }, {
  //   code: '111111',
  //   name: '50ETF',
  //   number: '30000',
  //   numberToSell: '10000',
  //   currentPrice: '2.33',
  //   fluctuation: '-0.34',
  //   profitAndLoss: '-9900',
  // }, {
  //   code: '222222',
  //   name: '50ETF',
  //   number: '30000',
  //   numberToSell: '10000',
  //   currentPrice: '2.33',
  //   fluctuation: '-0.34',
  //   profitAndLoss: '-9900',
  // }];
}
