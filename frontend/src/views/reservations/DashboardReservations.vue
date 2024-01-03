<template>
    <div class="q-pa-md">
      <q-layout view="hHh Lpr lff" container style="height: 100vh">
        <q-header elevated :class="$q.dark.isActive ? 'bg-secondary' : 'bg-black'">
          <q-toolbar>
            <q-toolbar-title>Dashboard Reservations</q-toolbar-title>
          </q-toolbar>
        </q-header>
  
        <Sidebar :drawer="drawer" />
  
        <q-page-container>
          <q-page padding>
            <!-- Contenido -->
            <gridReservations v-if="state.reservations" :data="state.reservations" />
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
    import gridReservations from '../../components/dashboard/gridReservations.vue';
    import gridSkeleton from '../../components/dashboard/gridSkeleton.vue';
  
    const drawer = ref(false);
    const store = useStore();
  
    store.dispatch(`reservations/${adminConstant.INITIALIZE_RESERVATIONS}`);
  
    const state = ref(reactive({
      reservations: computed(() => store.getters['reservations/GetReservations'])
    }));
  
    console.log(state.reservations);
  
  
  </script>
   
  <style lang="css" scoped>
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