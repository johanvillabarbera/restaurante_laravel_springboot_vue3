<template>
    <font-awesome-icon icon="fa-solid fa-arrow-left" class="fa-2x" style="cursor:pointer; margin-left:0.2em;"
        @click="redirects.return()" />
    <FormTablesDashboard :data="state.table" :key="state.table?.id" @emitAction="updateTables" />
</template>

<script setup>
import { reactive, computed } from 'vue';
import { useStore } from 'vuex';
import adminConstant from '../../store/modules/admin/adminConstant';
import { useRouter } from 'vue-router';
import { useRoute } from 'vue-router';
import FormTablesDashboard from '../../components/dashboard/formTablesDashboard.vue';

    const store = useStore();
    const route = useRoute();
    const router = useRouter();

    store.dispatch(`tables/${adminConstant.INITIALIZE_TABLE}`, route.params.id);

    const state = reactive({
        table: computed(() => store.getters['tables/GetTable'])
    });

    const updateTables = (data) => {
        console.log(data);
        store.dispatch(`tables/${adminConstant.UPDATE_TABLES}`, data);
        redirects.return();
    }

    const redirects = {
        return: () => router.push({ name: 'dashboard' }),
    };
</script>