<template>
    <div class="reserva-container">
      <div class="reserva-card" v-for="reserva in state.reservas" :key="reserva.id">
        <div class="reserva-header">
          {{ reserva.nombre }}
        </div>
        <div class="reserva-body">
          <div class="reserva-info"><strong>Día:</strong> {{ formatDate(reserva.booking.bookingDay) }}</div>
          <div class="reserva-info"><strong>Menú:</strong> {{ reserva.booking.name_menu }}</div>
          <div class="reserva-info"><strong>Precio:</strong> {{ reserva.booking.priceTotal }}</div>
          <div class="reserva-info"><strong>Comensales:</strong> {{ reserva.booking.dinersNumber }}</div>
          <div class="reserva-estado">
            <strong>Estado: </strong>
            <span v-if="reserva.booking.status == 0" style="color: orangered">Pendiente</span>
            <span v-else-if="reserva.booking.status == 1" style="color: green;">Confirmada</span>
            <span v-else-if="reserva.booking.status == 2" style="color: red">Cancelada</span>
          </div>
          <div class="reserva-cancelar">
            <button v-if="reserva.booking.status == 0" @click="cancelarReserva(reserva.booking.id)">Cancelar</button>
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
  gap: 20px;
  justify-content: center;
}

.reserva-card {
  background-color: white;
  padding: 20px;
  border-radius: 10px;
  box-shadow: 0 6px 12px rgba(0, 0, 0, 0.1);
  width: 45%;
  transition: transform 0.3s ease;
}

.reserva-card:hover {
  transform: translateY(-5px);
}

.reserva-header {
  font-size: 20px;
  color: #333;
  margin-bottom: 15px;
  font-weight: 500;
}

.reserva-body {
  font-size: 16px;
  color: #555;
}

.reserva-info {
  margin-bottom: 10px;
}

.reserva-estado {
  display: flex;
  align-items: center;
  gap: 10px;
  font-weight: 500;
}

.reserva-cancelar button {
  background-color: #ff5f57;
  color: white;
  border: none;
  border-radius: 5px;
  padding: 10px 15px;
  cursor: pointer;
  transition: background-color 0.3s ease;
}

.reserva-cancelar button:hover {
  background-color: #e0483e;
}

.reserva-factura a {
  color: #007bff;
  text-decoration: none;
}
</style>
