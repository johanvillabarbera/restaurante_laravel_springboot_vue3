<template>
  <div class="q-pa-md">
    <q-table 
      title="Menus" 
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
            @click="deleteMenu(props.row)"
          ></q-btn>
          <!-- Botón de Editar -->
          <q-btn
            color="primary"
            icon="edit"
            flat
            @click="updateMenu(props.row)"
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
  { name: 'menuID', align: 'center', label: 'menuID', field: 'menuID', sortable: true },
  { name: 'Name', label: 'name', field: 'name', sortable: true },
  { name: 'Description', label: 'description', field: 'description', sortable: true },
  { name: 'Price', label: 'price', field: 'price', sortable: true },
  { name: 'action', label: 'Actions', field: 'action', align: 'center' },
]

const props = defineProps({
  data: Object
});

const rows = props.data;

const router = useRouter();

const redirects = {
  create: () => router.push({ name: 'menusCreate' })
};

const updateMenu = (row) => {
  const menuID = row.menuID;
  router.push({ name: 'menusUpdate', params: { menuID } });
}

const deleteMenu = (row) => {
  const menuID = row.menuID;
  console.log(menuID);
}

</script>