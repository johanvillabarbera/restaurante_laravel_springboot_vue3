<template>
    <font-awesome-icon icon="fa-solid fa-arrow-left" class="fa-2x" style="cursor:pointer; margin-left:0.2em;"
        @click="redirects.return()" />
    <FormMenusDashboard @emitAction="createMenus" />
</template>

<script setup>
import { useStore } from 'vuex';
import adminConstant from '../../store/modules/admin/adminConstant';
import { useRouter } from 'vue-router';
import FormMenusDashboard from '../../components/dashboard/formMenusDashboard.vue';

    const store = useStore();
    const router = useRouter();

    const createMenus = (data) => {
        store.dispatch(`menus/${adminConstant.CREATE_MENU}`, data);
        redirects.return();
    }

    const redirects = {
        return: () => router.push({ name: 'dashboard' })
    }
    
</script>