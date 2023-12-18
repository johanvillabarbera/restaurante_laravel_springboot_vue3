<template>
   <div class="q-pa-md">
    <q-layout view="hHh Lpr lff" container style="height: 100vh">
      <q-header elevated :class="$q.dark.isActive ? 'bg-secondary' : 'bg-black'">
        <q-toolbar>
          <q-toolbar-title>Dashboard</q-toolbar-title>
        </q-toolbar>
      </q-header>

      <Sidebar :drawer="drawer" />

      <q-page-container>
        <q-page padding>
          <!-- Contenido -->
         
        </q-page>
      </q-page-container>
    </q-layout>
  </div>
</template>

<script setup>
    
    import Sidebar from '../../components/dashboard/Sidebar.vue';
    import gridTables from '../../components/dashboard/gridTables.vue';
    import { ref } from 'vue'
    const drawer = ref(false);



</script>

<style lang="css">
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