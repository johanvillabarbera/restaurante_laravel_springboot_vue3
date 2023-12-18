<template>
    <formAuth :isLogin="true" @send="login" />
</template>

<script setup>
import formAuth from '../../components/auth/formAuth.vue';
import authConstant from '../../store/modules/auth/authConstant';
import { useStore } from 'vuex';

    const store = useStore();
    const login = (data) => {
        store.dispatch(`auth/${authConstant.LOGIN}`, data);
    }
    console.log(login);
</script>

<style lang="css" scoped>
body {
    margin: 0px;
    padding: 0px;
}
    .header {
        display: none;
    }

    .q-pa-md{
        padding: 0px;
    }
</style>