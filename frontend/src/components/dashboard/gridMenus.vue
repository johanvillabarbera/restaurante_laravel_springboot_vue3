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
            @click="updateMenu(props.row.menuID)"
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
  { name: 'menuID', align: 'center', label: 'menuID', field: 'menuID', sortable: true },
  { name: 'Name', label: 'name', field: 'name', sortable: true },
  { name: 'Description', label: 'description', field: 'description', sortable: true },
  { name: 'Price', label: 'price', field: 'price', sortable: true },
  { name: 'action', label: 'Actions', field: 'action', align: 'center' },
]

const props = defineProps({
  data: Object
});

const rows = ref(props.data);

const router = useRouter();

const redirects = {
  create: () => router.push({ name: 'menusCreate' })
};

const updateMenu = (menuID) => {
  // console.log(row);
  // const menuID2 = row.menuID;
  // console.log(menuID);
  router.replace({ name: 'menusUpdate', params: { id: menuID } });
}

const deleteMenu = (row) => {
  const menuID = row.menuID;
  store.dispatch(`menus/${adminConstant.DELETE_MENU}`, menuID);
  const NUM_ELEMENTOS_A_BORRAR = 1;
  const menu_borrado_pos = rows.value.findIndex(item => item.menuID == menuID);
  rows.value.splice(menu_borrado_pos, NUM_ELEMENTOS_A_BORRAR);
}

</script>