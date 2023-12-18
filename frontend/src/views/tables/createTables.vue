<template>
    <div class="dashboard-container">
        <font-awesome-icon icon="fa-solid fa-arrow-left" class="fa-2x" style="cursor:pointer; margin-left:0.2em;"
            @click="redirects.return()" />
            <div class="form-container">
                <FormTablesDashboard @emitAction="createTables" />
            </div>
    </div>
</template>

<script setup>
import { useStore } from 'vuex';
import adminConstant from '../../store/modules/admin/adminConstant';
import { useRouter } from 'vue-router';
import FormTablesDashboard from '../../components/dashboard/formTablesDashboard.vue';
import { useQuasar } from 'quasar'

    const store = useStore();
    const router = useRouter();

    const q = useQuasar();


    const createTables = async (data) => {
        q.notify({
          message: 'Mesa creada con éxito',
          color: 'green-13'
        })
        await store.dispatch(`tables/${adminConstant.CREATE_TABLES}`, data);
        redirects.return();
    }

    const redirects = {
        return: () => router.push({ name: 'tables' }),
    };
</script>

<style scoped>
.dashboard-container {
    display: flex;
    align-items: center;
    justify-content: space-around;
}

.header {
    display: none;
  }

.back-icon {
    cursor: pointer;
    margin-left: 0.2em;
    font-size: 2rem;
}

.form-container {
    width: 100%;
}
</style>