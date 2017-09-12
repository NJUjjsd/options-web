/**
 * Created by john on 2017/9/10.
 */
import request from '../utils/request';

export function getUserInfo(email) {
  const promise = request('/api/users/getUserInfo', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify(email),
  });
  return promise.then((v) => {
    const user = v.data;
    return { user };
  });
}

export function signUp(account) {
  return request('/api/users/signUp', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify(account),
  });
}

export function login(account) {
  console.log(account);
  return request('/api/users/login', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify(account),
  });
}
