<template>
    <q-dialog v-model="props.model">
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
    import { defineProps, getCurrentInstance, ref, computed, reactive } from 'vue'
    import { useStore } from 'vuex';
    import clientConstant from '../../store/modules/client/clientConstant';


    const store = useStore();

    let props = defineProps(['model','filters'])

    const emit = defineEmits(['close']);

    const close = () => {
        emit('close', true)
    }

    const reservar = () => {
        props.filters.menuID = menuOption.value.id;
        props.filters.booking_day = props.filters.date;
        props.filters.diners_number = props.filters.capacity;
        console.log(props.filters);
        store.dispatch(`reservation/${clientConstant.CREATE_RESERVATION}`, props.filters);
    }

    store.dispatch(`menu/${clientConstant.INITIALIZE_MENU}`);
    const menuOption = ref(null);
    const optionsMenus = computed(() => store.getters['menu/GetMenus'].map(item => { return { name: item.name, id: item.menuID }}));
</script>