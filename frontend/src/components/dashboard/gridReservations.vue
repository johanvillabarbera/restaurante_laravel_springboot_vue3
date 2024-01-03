<template>
    <div class="q-pa-md">
      <q-table 
        title="Reservations" 
        :rows="rows" 
        :columns="columns" 
        row-key="name" 
      >
        <template v-slot:body-cell-action="props">
          <q-td :props="props">
            <q-btn
              color="negative"
              icon="check"
              flat
              @click="confirmReservation(props.row)"
            ></q-btn>
          </q-td>
        </template>
      </q-table>
    </div>
  </template>
  
  <script setup>
  
  import { useRouter } from 'vue-router';
  import { useStore } from 'vuex';
  import adminConstant from '../../store/modules/admin/adminConstant';
  import { ref } from 'vue';
  import { useQuasar } from 'quasar'

  const store = useStore();

  const columns = [
    { name: 'reservationID', align: 'center', label: 'bookingID', field: 'bookingID', sortable: true },
    { name: 'clientID', label: 'clientID', field: 'clientID', sortable: true },
    { name: 'tableID', label: 'tableID', field: 'tableID', sortable: true },
    { name: 'turnID', label: 'turnID', field: 'turnID', sortable: true },
    { name: 'Booking day', label: 'booking_day', field: 'booking_day', align: 'center' },
    { name: 'Diners number', label: 'diners_number', field: 'diners_number', sortable: true },
    { name: 'Status', label: 'status', field: 'status', sortable: true },
    { name: 'menuID', label: 'menuID', field: 'menuID', sortable: true },
    { name: 'action', label: 'Actions', field: 'action', align: 'center' },
  ]
  
  const props = defineProps({
    data: Object
  });
  
  const rows = ref(props.data);
  
  const router = useRouter();
  
  const q = useQuasar();

  const confirmReservation = (row) => {
    const bookingID = row.bookingID;
    row.status = 1;
    store.dispatch(`reservations/${adminConstant.UPDATE_RESERVATION}`, row);
    const NUM_ELEMENTOS_A_BORRAR = 1;
    const reservation_borrado_pos = rows.value.findIndex(item => item.bookingID == bookingID);
    rows.value.splice(reservation_borrado_pos, NUM_ELEMENTOS_A_BORRAR);
    q.notify({
          message: 'Reserva confirmada',
          color: 'green'
    })
  }
  
  </script>