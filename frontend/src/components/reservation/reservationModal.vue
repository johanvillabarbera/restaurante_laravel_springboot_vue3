<template>
    <q-dialog v-model="props.model" :style="{ backdropFilter: 'blur(5px)', backgroundColor: 'rgba(0, 0, 0, 0.5)' }">
        <q-card style="width: 700px">
        <q-card-section>
            <div class="text-h6">Confirmar reserva</div>
        </q-card-section>

        <q-select color="dark" bg-color="grey" filled v-model="menuOption" 
        :options="optionsMenus" option-value="id" option-label="name" label="Selecciona un menú" style="padding: 0.5rem;"/>

        <q-card-section class="q-pt-none">
            <h5>Tus preferencias</h5>

            <p>Fecha: {{ props.filters.date }}</p>
            <p>Turno: {{ props.filters.turn }}</p>
            <p>Capacidad: {{ props.filters.capacity }}</p>
        </q-card-section>

        <q-card-actions align="right">
            <q-btn flat label="Cancelar" color="warning" v-close-popup @click="close" />
            <q-btn flat label="Confirmar" color="primary" v-close-popup @click="reservar" />
        </q-card-actions>

        </q-card>
    </q-dialog>
</template>

<script setup>
    import { defineProps, getCurrentInstance, ref, computed, reactive, watch } from 'vue'
    import { useStore } from 'vuex';
    import clientConstant from '../../store/modules/client/clientConstant';
    import { useRouter } from 'vue-router';
    import { useQuasar } from 'quasar'

    const router = useRouter();
    const store = useStore();
    const q = useQuasar();

    let props = defineProps(['model','filters'])
    const emit = defineEmits(['close']);

    const localFilters = reactive({
        ...props.filters
    });

    watch(() => props.filters, (newFilters) => {
        Object.assign(localFilters, newFilters);
    }, { deep: true });

    const close = () => {
        emit('close', true)
    }

    const reservar = () => {
        localFilters.menuID = menuOption.value.id;
        localFilters.booking_day = localFilters.date;
        localFilters.diners_number = localFilters.capacity;
        console.log(localFilters);
        store.dispatch(`reservation/${clientConstant.CREATE_RESERVATION}`, localFilters);
        router.push({ name: "home" });
        q.notify({
          message: 'Se ha realizado la reserva',
          color: 'green'
    })
    }

    store.dispatch(`menu/${clientConstant.INITIALIZE_MENU}`);
    const menuOption = ref(null);
    const optionsMenus = computed(() => store.getters['menu/GetMenus'].map(item => { return { name: item.name, id: item.menuID }}));
</script>


<style>
.q-dialog__backdrop {
  -webkit-backdrop-filter: blur(5px) !important;
  backdrop-filter: blur(5px) !important;
  background-color: rgba(0, 0, 0, 0.5) !important; 
}
</style>