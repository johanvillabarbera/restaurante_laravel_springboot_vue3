import clientConstant from './clientConstant';
import clientMenuService from '../../../services/client/menusService';
import SockJS from 'sockjs-client';
import Stomp from 'webstomp-client';

// import VueSocketIO from 'vue-socket.io';

// const socketOptions = {
//     debug: true,
//     connection: 'http://bellidel.eu:7800/api/websocket-endpoint',
//   };
  

//   const socket = new VueSocketIO(socketOptions);


export const menu = {
    state: {
        menu: [],
        stompClient: null,
        messages: [] // Para almacenar los mensajes recibidos
    },
    namespaced: true,
    actions: {
        [clientConstant.INITIALIZE_MENU]: async (store) => {
            try {
                const response = await clientMenuService.GetMenus();
                store.commit(clientConstant.INITIALIZE_MENU, response.data);
                store.dispatch(clientConstant.CONNECT_WEBSOCKET);
            } catch (error) {
                console.error(error);
            }
        },
        [clientConstant.CONNECT_WEBSOCKET]: ({ commit, state }) => {
            const socket = new SockJS('http://bellidel.eu:7800/api/websocket-endpoint');
            state.stompClient = Stomp.over(socket);

            state.stompClient.connect({}, () => {
                // Suscribirse a un tema específico
                state.stompClient.subscribe('/topic', message => {
                    console.log(message.body);
                    commit(clientConstant.RECEIVE_MESSAGE, message.body);
                });
            }, error => {
                console.error('Error en la conexión WebSocket:', error);
            });
        }
    },
    mutations: {
        [clientConstant.INITIALIZE_MENU]: (state, payload) => {
            state.menu = payload;
        },
        [clientConstant.RECEIVE_MESSAGE]: (state, message) => {
            state.messages.push(message);
        }
    },
    getters: {
        GetMenus(state) {
            return state.menu;
        },
        GetMessages(state) {
            return state.messages;
        }
    }
};