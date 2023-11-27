<template>
    <div class="q-pa-md">
      <q-table
        title="Tables"
        :rows="rows"
        :columns="columns"
        row-key="name"
      >
      <template v-slot:body-cell-action="props">
        <q-td :props="props">
          <q-btn
            color="negative"
            icon="delete"
            flat
            @click="deleteTable(props.row)"
          ></q-btn>
          <!-- Botón de Editar -->
          <q-btn
            color="primary"
            icon="edit"
            flat
            @click="updateTable(props.row)"
          ></q-btn>
        </q-td>
      </template>
    </q-table>
    </div>
    <button @click="redirects.create">Crear</button>
</template>

<script setup>
  import { useRouter } from 'vue-router';

    const columns = [
    { name: 'tableID', align: 'center', label: 'tableID', field: 'tableID', sortable: true },
    { name: 'Capacitty', label: 'Capacity', field: 'capacity', sortable: true },
    { name: 'Location', label: 'Location', field: 'location', sortable: true},
    { name: 'Availability', label: 'Availability', field: 'availability', sortable: true},
    { name: 'action', label: 'Actions', field: 'action', align: 'center' },
    ]

    const router = useRouter();

    const redirects = {
      create: () => router.push({ name: 'tablesCreate' }),
    };

    const props = defineProps({
        data: Object
    });

    const updateTable = (row) => {
      const tableID = row.tableID
      router.push({ name: 'tablesUpdate', params: { tableID } })
    };

    const deleteTable = (row) => {
      console.log('Eliminar fila:', row);
      // Aquí lógica para eliminar la fila
    };
    
    const rows = props.data; 

</script>