<template>
    <div class="reserva-container">
      <div class="reserva-card" v-for="reserva in reservas" :key="reserva.id">
        <div class="reserva-header">
          {{ reserva.nombre }}
        </div>
        <div class="reserva-body">
          <div class="reserva-info"><strong>Día:</strong> {{ reserva.dia }}</div>
          <div class="reserva-info"><strong>Menú:</strong> {{ reserva.menu }}</div>
          <div class="reserva-info"><strong>Precio:</strong> {{ reserva.precio }}</div>
          <div class="reserva-info"><strong>Comensales:</strong> {{ reserva.comensales }}</div>
        </div>
      </div>
    </div>
  </template>
  

<script setup>

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
</style>
