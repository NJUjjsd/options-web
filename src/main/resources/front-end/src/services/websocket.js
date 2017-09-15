/**
 * Created by a297 on 17/9/15.
 */
import socket from 'ws';

export function listen(action) {
  socket.on('message', (data) => {
    action(data);
  });
}
