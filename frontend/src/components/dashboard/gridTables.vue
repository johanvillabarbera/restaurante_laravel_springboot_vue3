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
  import { useStore } from 'vuex';
import adminConstant from '../../store/modules/admin/adminConstant';
import { ref } from 'vue';

  const store = useStore();

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

    const rows = ref(props.data); 

    const updateTable = (row) => {
      const tableID = row.tableID
      console.log(tableID);
      router.push({ name: 'tablesUpdate', params: { id: tableID } })
    };

    const deleteTable = async (row) => {
      const tableID = row.tableID;
      await store.dispatch(`tables/${adminConstant.DELETE_TABLE}`, tableID);
      const NUM_ELEMENTOS_A_BORRAR = 1;
      const menu_borrado_pos = rows.value.findIndex(item => item.tableID == tableID);
      rows.value.splice(menu_borrado_pos, NUM_ELEMENTOS_A_BORRAR);
    };
</script>