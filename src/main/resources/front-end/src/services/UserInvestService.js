/**
 * Created by a297 on 17/9/9.
 */
import request from '../utils/requst';

export function getHoldingData(email) {
  const promise = request(`api/invest/holding?email=${email}}`);
  return promise.then((v) => {
    const holding = v.data;
    return holding;
  });
}
