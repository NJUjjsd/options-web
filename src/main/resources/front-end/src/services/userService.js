/**
 * Created by john on 2017/9/10.
 */
import { Encrypt } from '../utils/aes';
import request from '../utils/request';

export function getUserInfo(account) {
  const body = {
    email: Encrypt(account.email),
  };
  console.log(body);
  const promise = request('/api/users/getUserInfo', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify(body),
  });
  return promise.then((v) => {
    return v.data;
  });
}

export function modifyUserInfo(user) {
  const emailObj = {
    email: Encrypt(user.email),
  };
  const body = { ...user, ...emailObj };
  console.log(body);
  const promise = request('/api/users/modifyUserInfo', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify(body),
  });
  return promise.then((v) => {
    return v.data;
  });
}

export function changePassword(pswObj) {
  const body = {
    email: Encrypt(pswObj.email),
    prePassword: Encrypt(pswObj.prePassword),
    newPassword: Encrypt(pswObj.newPassword),
  };
  console.log(body);
  const promise = request('/api/users/changePassword', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify(body),
  });
  return promise.then((v) => {
    return v.data;
  });
}

export function signUp(account) {
  const body = {
    email: Encrypt(account.email),
    password: Encrypt(account.password),
    userName: Encrypt(account.userName),
  };
  console.log(body);
  const promise = request('/api/users/signUp', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify(body),
  });
  console.log(promise);
  const p = Promise.resolve(promise);
  return p.then((v) => {
    return v.data;
  });
}

export function login(account) {
  const body = {
    email: Encrypt(account.email),
    password: Encrypt(account.password),
  };
  console.log(body);
  const promise = request('/api/users/login', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify(body),
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
