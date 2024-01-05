<template>
    <div class="reserva-container">
      <div class="reserva-card" v-for="reserva in state.reservas" :key="reserva.id">
        <div class="reserva-header">
          {{ reserva.nombre }}
        </div>
        <div class="reserva-body">
          <div class="reserva-info"><strong>Día:</strong> {{ reserva.bookingDay }}</div>
          <div class="reserva-info"><strong>Menú:</strong> {{ reserva.name_menu }}</div>
          <div class="reserva-info"><strong>Precio:</strong> {{ reserva.priceTotal }}</div>
          <div class="reserva-info"><strong>Comensales:</strong> {{ reserva.dinersNumber }}</div>
          <div class="reserva-estado">
            <strong>Estado: </strong>
            <span v-if="reserva.status == 0">Pendiente</span>
            <span v-else-if="reserva.status == 1">Confirmada</span>
            <span v-else-if="reserva.status == 2">Cancelada</span>
          </div>
        </div>
      </div>
    </div>
  </template>
  

<script setup>

// USe store to get the user's reservations
import { useStore } from 'vuex';
import authConstant from '../../store/modules/auth/authConstant';
import { computed, getCurrentInstance, reactive } from 'vue';

const store = useStore();

store.dispatch(`auth/${authConstant.BOOKINGS_HISTORY}`);

const state = reactive({
    reservas: computed(() => store.getters['auth/GetBookingsHistory'])
});



  const reservas = [
                { id: 1, nombre: 'Reserva 1', dia: 'Lunes', menu: 'Menú 1', precio: '$10', comensales: 2 },
                { id: 2, nombre: 'Reserva 2', dia: 'Martes', menu: 'Menú 2', precio: '$15', comensales: 4 },
                { id: 3, nombre: 'Reserva 3', dia: 'Miércoles', menu: 'Menú 3', precio: '$12', comensales: 3 }
            ]

</script>
<style scoped>
.reserva-container {
  display: flex;
  flex-wrap: wrap;
  justify-content: space-between;
  gap: 10px;
}

.reserva-header {
    font-size: 18px;
    font-weight: bold;
    margin-bottom: 10px;
    color: orangered;
}

.reserva-card {
  background-color: #f5f5f5;
  padding: 15px;
  border-radius: 8px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.15);
  width: calc(50% - 30px); /* Ajuste para el espacio entre tarjetas */
  margin-top: 10px;
  margin-left: 10px;
  margin-right: 10px;
}

.reserva-header {
  font-size: 18px;
  font-weight: bold;
  margin-bottom: 10px;
}

.reserva-body {
  font-size: 14px;
}

.reserva-info {
  margin-bottom: 5px;
  color: #555;
}

.reserva-estado{
  margin-bottom: 5px;
  color: #555;
}
</style>
