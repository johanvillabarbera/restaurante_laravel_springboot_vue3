import { createApp } from 'vue';
import App from './App.vue';
import router from './router';
import store from './store';
import './style.css';

// import VueSocketIO from 'vue-socket.io';
// const VueSocket = new VueSocketIO({
//     debug: true,
//     connection: 'http://bellidel.eu:7800/app/websocket-endpoint'
// });

const app = createApp(App);
app.use(router);
app.use(store);
// app.use(VueSocket);
app.mount('#app');
