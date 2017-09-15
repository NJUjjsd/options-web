/**
 * Created by a297 on 17/9/15.
 */
export function openOperation({ email, url }) {
  const ws = new WebSocket(url);
  // console.log('ws in services', ws);
  ws.onopen = () => {
    console.log('hhhhhhhhhhh open');
    ws.send(email);
  };
  ws.onmessage = (e) => {
    const notification = e.data;
    console.log('hhhhhhhhhhh onmessage:', notification);
    console.log('hhhhhhhhhhh onmessage type of it:', typeof notification);
    return notification;
  };
  ws.onerror = (e) => {
    console.log('hhhhhhhhhhh error', e.message);
  };
  ws.onclose = (e) => {
    console.log(e.code, e.reason);
  };
}
