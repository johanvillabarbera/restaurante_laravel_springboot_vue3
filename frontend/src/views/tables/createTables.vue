<template>
    <font-awesome-icon icon="fa-solid fa-arrow-left" class="fa-2x" style="cursor:pointer; margin-left:0.2em;"
        @click="redirects.return()" />
    <FormTablesDashboard @emitAction="createTables" />
</template>

<script setup>
import { useStore } from 'vuex';
import adminConstant from '../../store/modules/admin/adminConstant';
import { useRouter } from 'vue-router';
import FormTablesDashboard from '../../components/dashboard/formTablesDashboard.vue';

    const store = useStore();
    const router = useRouter();

    const createTables = (data) => {
        store.dispatch(`tables/${adminConstant.CREATE_TABLES}`, data);
        redirects.return();
    }

    const redirects = {
        return: () => router.push({ name: 'dashboard' }),
    };
</script>