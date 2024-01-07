<template>
    <div class="reserva-container">
      <div class="reserva-card" v-for="reserva in state.reservas" :key="reserva.id">
        <div class="reserva-header">
          {{ reserva.nombre }}
        </div>
        <div class="reserva-body">
          <div class="reserva-info"><strong>Día:</strong> {{ reserva.booking.bookingDay }}</div>
          <div class="reserva-info"><strong>Menú:</strong> {{ reserva.booking.name_menu }}</div>
          <div class="reserva-info"><strong>Precio:</strong> {{ reserva.booking.priceTotal }}</div>
          <div class="reserva-info"><strong>Comensales:</strong> {{ reserva.booking.dinersNumber }}</div>
          <div class="reserva-estado">
            <strong>Estado: </strong>
            <span v-if="reserva.booking.status == 0" style="color: orangered">Pendiente</span>
            <span v-else-if="reserva.booking.status == 1" style="color: green;">Confirmada</span>
            <span v-else-if="reserva.booking.status == 2" style="color: red">Cancelada</span>
          </div>
          <div class="reserva-factura">
            <a :href="reserva.facturas_pdf.pdf" :download="`Factura-Bellidel-${formatDate(reserva.booking.bookingDay)}.pdf`">Descargar Factura</a>
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

const formatDate = (dateString) => {
  return dateString.split('T')[0];
}

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
