/**
 * Created by john on 2017/9/10.
 */
import request from '../utils/request';

export function getUserInfo(email) {
  const promise = request(`/api/users/getUserInfo?email=${email}`);
  return promise.then((v) => {
    const user = v.data;
    return { user };
  });
}
