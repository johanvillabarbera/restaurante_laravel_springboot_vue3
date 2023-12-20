<template>
    <!-- <q-select filled v-model="model" :options="state.menu" label="Filled" />
    <TableCard :data="state.tables"/> -->
    <q-layout>
    <q-page-container>
      <q-page>
        <q-grid class="row">
          <!-- Columna de filtros a la izquierda -->
          <q-col cols="4" sm="4">
            <div class="q-pa-md">
              <tableFilters @filters="aplicarFiltros" :filters="filters_URL" />
            </div>
          </q-col>
          <q-grid class="column">
          <!-- Div para mostrar el contenido de los filtros seleccionados -->
          <q-col cols="8" sm="8" class="row inline">
            <q-chip v-if="filters_URL" v-for="filter in filters_URL" color="dark" text-color="orange" icon="bookmark">
              {{ filter }}
            </q-chip>
          </q-col>

          <!-- Div grande para mostrar los datos -->
          <q-col cols="8" sm="8">
            <div class="q-pa-md full-width">
              <!-- Aquí se mostrarán los datos -->
              <div v-if="state.tables.length > 0">
                <!-- Renderizar datos aquí -->
                <div class="tables-container">
                  <div class="table-container" v-for="(table, index) in state.tables" :key="table.id" @click="detectClickTable(table)">
                    <reservationModal @close="closeModal()" :model="alert" :filters="filters_URL"></reservationModal>
                    <div :class=" tableClasses[index]">
                      <!-- Mesa {{ item.tableID }} -->
                      <div class="chair chair-top"></div>
                      <div class="chair chair-right"></div>
                      <div class="chair chair-bottom"></div>
                      <div class="chair chair-left"></div>
                      <div v-if="!table.meets_filters" class="table-status"></div>
                    </div>
                  </div>
                </div>
                <!-- {{ state.tables }} -->
              </div>
              <div v-else>
                Cargando datos...
              </div>
            </div>
          </q-col>
          </q-grid>
        </q-grid>
      </q-page>
    </q-page-container>
  </q-layout>
</template>

<script setup>
    import tableFilters from '../../components/tables/tableFilters.vue';
    import { useRouter, useRoute } from 'vue-router';
    import { reactive, computed, ref } from 'vue';
    import { useTableFilters } from '../../composables/tables/useTable';
    import reservationModal from '../../components/reservation/reservationModal.vue';

    
    const route = useRoute();
    const router = useRouter();
    const alert = ref(false);

    // Seteamos el objeto de filtros
    let filters_URL = {
        turn: '',
        capacity: '',
        date: '',
        tableID: ''
    };

    // Si hay filtros en la URL, los cargamos
    try {
      const { filters } = route.params;
      if (filters) {
        filters_URL = JSON.parse(atob(filters));
      }
    } catch (error) {
      console.error(error);
    }

    // Creamos el estado y comprobamos si hay filtros para usar useTableFilters
    
    const state = reactive({
      tables: filters_URL.turn !== '' ? useTableFilters(filters_URL) : [],
    }) 

    const aplicarFiltros = (filters) => {
      console.log(filters);
      const filters_64 = btoa(JSON.stringify(filters));
      router.push({ name: 'reservationFilters', params: { filters: filters_64 } });
      state.tables = useTableFilters(filters);
      console.log('aplicarFiltros');
    }


    const tableClasses = computed(() => {
      return state.tables.map(table => {

        // Comprobamos que este disponible
        if (table.estado_mesa) {
          return 'table table-no-disponible';
        }

        // Comprobamos que no este reservada
        if (table.estado_reserva) {
          return 'table table-reservada';
        }

        // Comprobamos que cumpla con los filtros
        if (table.meets_filters) {
          return 'table meets-filters';
        }

        // Si no cumple con los filtros
        return 'table table-no-es-lo-que-buscas';
      });
    });


    const detectClickTable = (table) => {
      filters_URL.tableID = table.tableID;

      alert.value = true;
      if (table.estado_reserva) {
        console.log("Mesa no disponible");
      } else if (!table.meets_filters) {
        console.log("No cumple con los requisitos");
      }
    
    }

    const closeModal = () => {
      alert.value = false;
    }
</script>

<style lang="css" scoped>


.tables-container {
    display: flex;
    flex-wrap: wrap;
    justify-content: center;
    align-items: center;
    height: 100%;
}

.table-container {
  padding: 60px;
}

.table {
    position: relative !important;
    width: 30%;
    min-width: 200px;
    height: 100px;
    margin: 10px;
    background-color: #333;
    border-radius: 10px;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
    position: relative;
}

.chair {
    width: 15%;
    height: 30px;
    background-color: #777;
    border-radius: 5px;
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
    position: absolute;
}


.chair-top {
    top: -40%;
    left: 50%;
    transform: translateX(-50%);
}

.chair-right {
    right: -20%;
    top: 50%;
    transform: translateY(-50%);
}

.chair-bottom {
    bottom: -40%;
    left: 50%;
    transform: translateX(-50%);
}

.chair-left {
    left: -20%;
    top: 50%;
    transform: translateY(-50%);
}
.chair:hover {
    background-color: #555;
}

@media (max-width: 768px) {
    .table {
        width: 45%;
        height: 20%;
    }
}

@media (max-width: 480px) {
    .table {
        width: 90%;
        height: 30%;
    }
}

/* Estilos comunes para letreros */
.table-status {
  position: absolute;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  display: flex;
  align-items: center;
  justify-content: center;
  color: white;
  font-weight: bold;
  font-size: 1.5em;
  border-radius: 10px;
  backdrop-filter: blur(3px);
  background-color: rgba(0, 0, 0, 0.6); 
}


.table-reservada .table-status {
    background-color: rgba(0, 123, 255, 0.7); 
}

.table-reservada .table-status::before {
    content: 'Reservada';
    color: white;
}

.table-no-disponible .table-status {
    background-color: rgba(255, 0, 0, 0.7); 
}

.table-no-disponible .table-status::before {
    color: white;
    content: 'No Disponible';
}

.table-no-es-lo-que-buscas .table-status {
    background-color: rgba(255, 193, 7, 0.7); 
}

.table-no-es-lo-que-buscas .table-status::before {
    content: 'No filters';
    color: white;
}

.row {
  flex-wrap: nowrap;
}

body {
  background: linear-gradient(to bottom, #f9f9f9, #e0e0e0)
}

</style>