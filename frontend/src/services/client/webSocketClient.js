// import SockJS from 'sockjs-client';
// import Stomp from 'webstomp-client';
// import clientConstant from '../../store/modules/client/clientConstant';

// const WEBSOCKET_URL = 'http://bellidel.eu:7800/api/websocket-endpoint';

// export function initializeWebSocket(commit, state) {
//     const socket = new SockJS(WEBSOCKET_URL);
//     state.stompClient = Stomp.over(socket);

//     state.stompClient.connect({}, () => {
//         state.stompClient.subscribe('/topic', message => {
//             console.log(message.body);
//             commit(clientConstant.RECEIVE_MESSAGE, message.body);
//         });
//     }, error => {
//         console.error('Error en la conexión WebSocket:', error);
//     });
// }
