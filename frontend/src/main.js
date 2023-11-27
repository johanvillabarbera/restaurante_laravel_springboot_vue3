import { createApp } from 'vue';
import App from './App.vue';
import router from './router';
import store from './store';
import './style.css';
import { Quasar } from 'quasar'
// Import icon libraries
import '@quasar/extras/material-icons/material-icons.css'

// Import Quasar css
import 'quasar/src/css/index.sass'

// import VueSocketIO from 'vue-socket.io';
// const VueSocket = new VueSocketIO({
//     debug: true,
//     connection: 'http://bellidel.eu:7800/app/websocket-endpoint'
// });

const app = createApp(App);
app.use(router);
app.use(store);
app.use(Quasar, {
    plugins: {}, // import Quasar plugins and add here
})
// app.use(VueSocket);
app.mount('#app');
