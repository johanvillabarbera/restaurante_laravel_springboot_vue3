<template>
    <font-awesome-icon icon="fa-solid fa-arrow-left" class="fa-2x" style="cursor:pointer; margin-left:0.2em;"
        @click="redirects.return()" />
    <FormTablesDashboard v-if="state.table != undefined" :data="state.table" :key="state.table?.id" @emitAction="updateTables" />
</template>

<script setup>
import { reactive, computed } from 'vue';
import { createLogger, useStore } from 'vuex';
import adminConstant from '../../store/modules/admin/adminConstant';
import { useRouter } from 'vue-router';
import { useRoute } from 'vue-router';
import FormTablesDashboard from '../../components/dashboard/formTablesDashboard.vue';
import { useQuasar } from 'quasar'

    const store = useStore();
    const route = useRoute();
    const router = useRouter();

    const q = useQuasar();

    store.dispatch(`tables/${adminConstant.INITIALIZE_TABLE}`, route.params.id);

    const state = reactive({
        table: computed(() => store.getters['tables/GetTable'])
    });
    
    const updateTables = async (data) => {
        
        q.notify({
          message: 'Mesa actualizada con éxito',
          color: 'blue'
        })

        await store.dispatch(`tables/${adminConstant.UPDATE_TABLES}`, data);
        redirects.return();
    }

    const redirects = {
        return: () => router.push({ name: 'tables' }),
    };
</script>