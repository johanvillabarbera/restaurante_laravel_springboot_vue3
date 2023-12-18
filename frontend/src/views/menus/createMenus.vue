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
import { useQuasar } from 'quasar'

    const store = useStore();
    const router = useRouter();
    const q = useQuasar();

    const createMenus = async (data) => {
        q.notify({
          message: 'Menu creado con éxito',
          color: 'green-13'
        })
        await store.dispatch(`menus/${adminConstant.CREATE_MENU}`, data);
        redirects.return();
    }

    const redirects = {
        return: () => router.push({ name: 'menus' })
    }
    
</script>

<style>
.header {
    display: none;
  }
</style>