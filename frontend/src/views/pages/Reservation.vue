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
              <q-stepper
                v-model="step"
                vertical
                color="primary"
                animated
              >
                <q-step
                  :name="1"
                  title="Selecciona una fecha"
                  icon="settings"
                  :done="step > 1"
                >
                  <q-date
                    v-model="date"
                    landscape
                  />

                  <q-stepper-navigation>
                    <q-btn v-if="date" @click="step = 2" color="primary" label="Continue" />
                  </q-stepper-navigation>
                </q-step>

                <q-step
                  :name="2"
                  title="Selecciona una comida"
                  icon="create_new_folder"
                  :done="step > 2"
                >
                  <q-select filled v-model="mealOption" :options="optionsMeal" label="Filled" />

                  <q-stepper-navigation>
                    <q-btn v-if="mealOption" @click="step = 3; loadTurns()" color="primary" label="Continue" />
                    <q-btn flat @click="step = 1" color="primary" label="Back" class="q-ml-sm" />
                  </q-stepper-navigation>
                </q-step>

                <q-step
                  :name="3"
                  title="Selecciona un turno"
                  icon="assignment"
                  :done="step > 3"
                >
                  <q-select filled v-model="turnOption" :options="optionsTurn" label="Filled" />

                  <q-stepper-navigation>
                    <q-btn v-if="turnOption" @click="step = 4;" color="primary" label="Continue" />
                    <q-btn flat @click="step = 2" color="primary" label="Back" class="q-ml-sm" />
                  </q-stepper-navigation>
                </q-step>

                <q-step
                  :name="4"
                  title="Selecciona una capacidad"
                  icon="add_comment"
                >
                  <q-select filled v-model="capacityOption" :options="optionsCapacity" label="Filled" />

                  <q-stepper-navigation>
                    <q-btn v-if="capacityOption" @click="aplicarFiltros()" color="primary" label="Finish" />
                    <q-btn flat @click="step = 3" color="primary" label="Back" class="q-ml-sm" />
                  </q-stepper-navigation>
                </q-step>
              </q-stepper>
            </div>

            <div class="q-pa-md">
              <!-- Aquí puedes agregar tus filtros de búsqueda -->
              <q-input filled v-model="filtro" label="Buscar" debounce="300" />
              <!-- Otros componentes de filtro pueden ir aquí -->
            </div>
          </q-col>


          <!-- Div grande para mostrar los datos -->
          <q-col cols="8" sm="8">
            <div class="q-pa-md full-width">
              <!-- Aquí se mostrarán los datos -->
              <div v-if="datosCargados">
                <!-- Renderizar datos aquí -->
              </div>
              <div v-else>
                Cargando datos...
                {{ state.tables }}
              </div>
            </div>
          </q-col>
        </q-grid>
      </q-page>
    </q-page-container>
  </q-layout>
</template>

<script setup>

    import TableCard from '../../components/tables/tableCard.vue';
    import { useStore } from 'vuex';
    import adminConstant from '../../store/modules/admin/adminConstant'
    import clientConstant from '../../store/modules/client/clientConstant'
    import { computed, reactive } from 'vue';
    import { useRouter, useRoute } from 'vue-router';
    import { ref } from 'vue'
    import { useTableFilters } from '../../composables/tables/useTable';

    let step = ref(1);

    const date = ref('');


    const mealOption = ref(null);

    const optionsMeal = ['Lunch', 'Dinner']

    const turnOption = ref(null);

    let optionsTurn = ref([]);

    const optionsTurnDB = [
      {
        meal: 'Lunch',
        turn: '12:00 - 14:00'
      },
      {
        meal: 'Dinner',
        turn: '20:00 - 22:00'
      }
    ]

    const optionsCapacity = [1, 2, 4, 8]

    const capacityOption = ref(null);

    const loadTurns = () => {
      optionsTurn = optionsTurnDB.filter(turn => turn.meal === mealOption.value).map(function(obj) {return obj.turn});
    }

    const store = useStore();

    //store.dispatch(`tables/${adminConstant.INITIALIZE_TABLES}`);

    store.dispatch(`menu/${clientConstant.INITIALIZE_MENU}`);

    const state = reactive({
        //tables: computed(() => store.getters['tables/GetTables']),
        tables: useTableFilters(),
        menu: computed(() => store.getters['menu/GetMenus']?.map(item => item.name))
    });

    const route = useRoute();

    try {
        if (route.params.filters !== '') {
            console.log(JSON.parse(atob(route.params.filters)));
            // filters_URL = JSON.parse(atob(route.params.filters));
        }
    } catch (error) {
    }

    const aplicarFiltros = () => {
      const filters = {
                turn: turnOption._value.toString(),
                capacity: capacityOption._value,
                date: date._value.replaceAll('/','-'),
            };
      state.tables = useTableFilters(filters);
      console.log(state.tables);
    }
</script>

<style lang="css">

</style>