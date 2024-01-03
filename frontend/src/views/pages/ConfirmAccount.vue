<template>
    <AccountVerified/>
</template>

<script setup>

import { useRoute } from 'vue-router';
import  AccountVerified from '../../components/auth/AccountVerified.vue';
import authConstant from '../../store/modules/auth/authConstant';
import { useStore } from 'vuex';

const route = useRoute();

const token = route.params.token;

const store = useStore();

store.dispatch(`auth/${authConstant.CONFIRM_ACCOUNT}`, token);

</script>