<template>
  <div class="q-pa-md">
    <q-layout view="hHh Lpr lff" container style="height: 100vh">
      <q-header elevated :class="$q.dark.isActive ? 'bg-secondary' : 'bg-black'">
        <q-toolbar>
          <q-toolbar-title>Dashboard Tables</q-toolbar-title>
        </q-toolbar>
      </q-header>

      <Sidebar :drawer="drawer" />

      <q-page-container>
        <q-page padding>
          <!-- Contenido -->
          <gridTables v-if="state.tables" :data="state.tables" />
          <gridSkeleton v-else />
        </q-page>
      </q-page-container>
    </q-layout>
  </div>
</template>
 
<script setup>
  import Sidebar from '../../components/dashboard/Sidebar.vue';
  import { ref } from 'vue'
  import { useStore } from 'vuex';
  import adminConstant from '../../store/modules/admin/adminConstant'
  import { computed, reactive } from 'vue';
  import gridTables from '../../components/dashboard/gridTables.vue';
  import gridSkeleton from '../../components/dashboard/gridSkeleton.vue';

  const drawer = ref(false);
  const store = useStore();

  store.dispatch(`tables/${adminConstant.INITIALIZE_TABLES}`);

  const state = ref(reactive({
    tables: computed(() => store.getters['tables/GetTables'])
  }));

  console.log(state.tables);


</script>
 
<style lang="css">
  body {
    margin: 0px;
    padding: 0px;
    background: linear-gradient(to bottom, #f9f9f9, #e0e0e0)
  }

  .header {
    display: none;
  }

  .q-pa-md {
    padding: 0px;
  }
</style>