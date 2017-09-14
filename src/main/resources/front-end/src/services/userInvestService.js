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

export function combinationEntrust(combination) {
  console.log('传给后端的套利委托', combination);
  return request('api/userInvest/combinationEntrust', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify(combination),
  });
}

export function getNotification({ email }) {
  console.log('getNotification');

  const ws = new WebSocket('ws://localhost:8080/websocket');
  console.log(ws);
  ws.onopen = () => {
    console.log('hhhhhhhhhhh open');
  };
  ws.onmessage = (e) => {
    console.log('hhhhhhhhhhh onmessage:', e.data);
    return [];
  };
  ws.onerror = (e) => {
    console.log('hhhhhhhhhhh error', e.message);
  };
  ws.onclose = (e) => {
    console.log(e.code, e.reason);
  };
  ws.send(email);

  return false;
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
  console.log('传给后端的取消委托信息', cancelList);
  return request('api/userInvest/cancelEntrust', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify(cancelList),
  });
}
