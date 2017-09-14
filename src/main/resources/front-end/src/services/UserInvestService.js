/**
 * Created by a297 on 17/9/9.
 */
import { WebSocket } from 'react';
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

export function getNotification({ path, email }) {
  const webSocket = new WebSocket(path);
  webSocket.onerror = function (event) {
    onerror(event);
  };
  webSocket.onopen = function (event) {
    onopen(event);
  };
  webSocket.onmessage = function (event) {
    onmessage(event);
  };
  webSocket.send(`hello server, this is ${email}`);
  return false;
}
function onerror(event) {
  console.log(event.data);
}
function onopen(event) {
  console.log('connection established', event.data);
}
function onmessage(event) {
  console.log(event.data);
}
