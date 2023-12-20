<template>

<q-stepper
                v-model="step"
                vertical
                color="dark"
                animated
                class="fixed-size-stepper"
              >
                <q-step
                  :name="1"
                  title="Selecciona una fecha"
                  color="dark"
                  icon="settings"
                  :done="step > 1"
                  class="fixed-size-step"
                >
                  <q-date
                    v-model="date"
                    color="dark"
                    mask="YYYY-MM-DD"
                    landscape
                  />

                  <q-stepper-navigation>
                    <q-btn v-if="date" @click="step = 2" color="dark" label="Continue" />
                  </q-stepper-navigation>
                </q-step>

                <q-step
                  :name="2"
                  title="Selecciona una comida"
                  color="dark"
                  icon="create_new_folder"
                  :done="step > 2"
                  class="fixed-size-step"
                >
                  <q-select color="dark" filled v-model="mealOption" :options="optionsMeal" label="Filled" />

                  <q-stepper-navigation>
                    <q-btn v-if="mealOption" @click="step = 3; loadTurns()" color="dark" label="Continue" />
                    <q-btn flat @click="step = 1" color="dark" label="Back" class="q-ml-sm" />
                  </q-stepper-navigation>
                </q-step>

                <q-step
                  :name="3"
                  title="Selecciona un turno"
                  color="dark"
                  icon="assignment"
                  :done="step > 3"
                  class="fixed-size-step"
                >
                  <q-select color="dark" filled v-model="turnOption" :options="optionsTurn" label="Filled" />

                  <q-stepper-navigation>
                    <q-btn v-if="turnOption" @click="step = 4;" color="dark" label="Continue" />
                    <q-btn flat @click="step = 2" color="dark" label="Back" class="q-ml-sm" />
                  </q-stepper-navigation>
                </q-step>

                <q-step
                  :name="4"
                  title="Selecciona una capacidad"
                  color="dark"
                  icon="add_comment"
                  class="fixed-size-step"
                >
                  <q-select color="dark" filled v-model="capacityOption" :options="optionsCapacity" label="Filled" />

                  <q-stepper-navigation>
                    <q-btn v-if="capacityOption" @click="emitirFiltros()" color="dark" label="Finish" />
                    <q-btn flat @click="step = 3" color="dark" label="Back" class="q-ml-sm" />
                  </q-stepper-navigation>
                </q-step>
              </q-stepper>

</template>

<script setup>
    import { ref, defineEmits, reactive, watch, computed } from 'vue';
    import { useStore } from 'vuex';
    import clientConstant from '../../store/modules/client/clientConstant';

    const store = useStore();

    store.dispatch(`turns/${clientConstant.INITIALIZE_TURNS}`);

    const props = defineProps({
        filters: Object
    });


    const emit = defineEmits([
        'filters'
    ]);

    const state = reactive({
        filters: { ...props.filters }
    });

    let step = ref(1);

    const date = ref('');
    const mealOption = ref(null);
    const turnOption = ref(null);
    const capacityOption = ref(null);

    const optionsMeal = computed(() => store.getters['turns/GetTurns'].map(item => item.meal));
    let optionsTurn = ref([]);
    const optionsTurnDB = computed(() => store.getters['turns/GetTurns'].map(item => {return {meal: item.meal, turn: item.turn_hour}}));

    const optionsCapacity = [1, 2, 4, 8, 12]


    const loadTurns = () => {
      optionsTurn = optionsTurnDB.value.filter(turn => turn.meal === mealOption.value).map(function(obj) {return obj.turn});
    }

    // Observamos los cambios en los filtros y actualizamos el objeto
    watch(date, (newValue) => {
        state.filters.date = newValue;
    });

    watch(turnOption, (newValue) => {
        state.filters.turn = newValue;
    });

    watch(capacityOption, (newValue) => {
        state.filters.capacity = newValue;
    });

    // Emitimos los filtros al padre
    const emitirFiltros = () => {
      console.log(state.filters);
        // Emit the filters to the parent @filters
        emit('filters', state.filters);
    }

</script>

<style scoped>

.fixed-size-step {
  height: auto; 
  min-width: 30vw; 
  overflow: auto; 
}
</style>