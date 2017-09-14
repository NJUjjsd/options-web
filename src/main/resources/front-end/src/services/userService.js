/**
 * Created by john on 2017/9/10.
 */
import request from '../utils/request';

export function getUserInfo(account) {
  const promise = request('/api/users/getUserInfo', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify(account),
  });
  return promise.then((v) => {
    return v.data;
  });
}

export function modifyUserInfo(values) {
  console.log(values);
  const promise = request('/api/users/modifyUserInfo', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify(values),
  });
  return promise.then((v) => {
    return v.data;
  });
}

export function changePassword(pswObj) {
  const promise = request('/api/users/changePassword', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify(pswObj),
  });
  return promise.then((v) => {
    return v.data;
  });
}

export function signUp(account) {
  const promise = request('/api/users/signUp', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify(account),
  });
  console.log(promise);
  const p = Promise.resolve(promise);
  return p.then((v) => {
    return v.data;
  });
}

export function login(account) {
  console.log(account);
  const promise = request('/api/users/login', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify(account),
  });
  return promise.then((v) => {
    return v.data;
  });
}

export function activatemail(account) {
  const promise = request('/api/users/activatemail', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify(account),
  });
  return promise.then((v) => {
    return v.data;
  });
}
