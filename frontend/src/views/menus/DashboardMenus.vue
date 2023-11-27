<template>

  <div class="q-pa-md">
    <q-layout view="hHh Lpr lff" container style="height: 100vh">
      <q-header elevated :class="$q.dark.isActive ? 'bg-secondary' : 'bg-black'">
        <q-toolbar>
          <q-toolbar-title>Dashboard Menus</q-toolbar-title>
        </q-toolbar>
      </q-header>

      <Sidebar :drawer="drawer" />

      <q-page-container>
        <q-page padding>
          <!-- Contenido -->
          <gridMenus v-if="state.menus && state.menus.length > 0"  :data="state.menus"/>
        </q-page>
      </q-page-container>
    </q-layout>
  </div>

</template>

<script setup>

  import Sidebar from '../../components/dashboard/Sidebar.vue';
  import gridMenus from '../../components/dashboard/gridMenus.vue';
  import { ref } from 'vue';
  import { useStore } from 'vuex';
  import { computed, reactive } from 'vue';
  import adminConstant from '../../store/modules/admin/adminConstant';
  
  const drawer = ref(false);
  const store = useStore();

  store.dispatch(`menus/${adminConstant.INITIALIZE_MENUS}`);

  const state = reactive({
    menus: computed(() => store.getters['menus/GetMenus'])
  });

</script>

<style lang="css">

    body {
        margin: 0px;
        padding: 0px;
    }

    .header {
        display: none;
    }

    .q-pa-md {
        padding: 0px;
    }

</style>