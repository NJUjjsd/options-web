/**
 * Created by a297 on 17/9/9.
 */
// import { WebSocket } from 'ws';
import request from '../utils/request';

export function getInvestBasicInfo(email) {
  const promise = request(`api/userInvest/investBasicInfo?email=${email}`);
  return promise.then((v) => {
    const investBasicInfo = v.data;
    console.log('investBasicInfo in front-end service', investBasicInfo);
    return investBasicInfo;
  });
}

export function getInformation(email) {
  const promise = request(`api/userInvest/getInformation?email=${email}`);
  return promise.then((v) => {
    const information = v.data;
    console.log('information in front-end service', information);
    return information;
  });
}

export function userEntrust(entrust) {
  console.log('传给后端的委托信息', entrust);
  return request('api/userInvest/entrust', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify(entrust),
  });
}

export function getUserEntrust(email) {
  console.log('getUserEntrust in service', email);
  const promise = request(`api/userInvest/getUserEntrust?email=${email}`);
  return promise.then((v) => {
    const theEntrust = v.data;
    console.log('userEntrust in front-end service', theEntrust);
    return theEntrust;
  });
}

export function cancelEntrust(cancelList) {
  console.log('传给后端的撤单信息', cancelList);
  return request('api/userInvest/cancelEntrust', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify(cancelList),
  });
}
