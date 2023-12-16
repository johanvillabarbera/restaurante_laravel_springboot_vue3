<script setup>
import HeaderView from './components/layouts/Header.vue'
import { RouterView } from 'vue-router'
import { useStore } from 'vuex';
import authConstant from './store/modules/auth/authConstant';
const store = useStore();

if (localStorage.getItem("token")) {
  store.dispatch(`auth/${authConstant.INITIALIZE_PROFILE}`);
}
</script>

<template>
  <HeaderView/>
  <RouterView/>
</template>

<style scoped>
/* .logo {
  height: 6em;
  padding: 1.5em;
  will-change: filter;
  transition: filter 300ms;
}
.logo:hover {
  filter: drop-shadow(0 0 2em #646cffaa);
}
.logo.vue:hover {
  filter: drop-shadow(0 0 2em #42b883aa);
} */
</style>
