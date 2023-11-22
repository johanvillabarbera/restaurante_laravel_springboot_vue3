<template>
    <div class="content">
        <Sidebar/>
        <div class="table_list">
            <h2>Tables</h2>
            <div class="load_table">
                <ul>
                    <tr>
                        <td> Table ID </td>
                        <td> Capacity </td>
                        <td> Location </td>
                        <td> Availability </td>
                    </tr>
                    <tr v-for="item in state.tables">
                        <td>{{ item.tableID }}</td>
                        <td> {{ item.capacity }}</td>
                        <td> {{ item.location  }} </td>
                        <td> {{ item.availability }} </td>
                    </tr>
                </ul>
            </div>
        </div>
    </div>
    
</template>

<script setup>

import Sidebar from '../../components/dashboard/Sidebar.vue';
import { useStore } from 'vuex';
import adminConstant from '../../store/modules/admin/adminConstant'
import { computed, reactive } from 'vue';
    const store = useStore();

    store.dispatch(`tables/${adminConstant.INITIALIZE_TABLES}`);

    const state = reactive({
        tables: computed(() => store.getters['tables/GetTables'])
    });

    console.log(state);
</script>

<style lang="css">

    .content{
        margin: 0px;
    }

    .table_list {
        margin-left: 10vw;
        padding: 2vw;
        width: 80vw;
    }
    
    .load_table {
        background-color: black;
    }

    .load_table ul {
        display: flex;
        flex-direction: column;
    }

    .load_table tr {
        display: flex;
        justify-content: space-around;
    }

    .load_table td {
        text-align: center;
    }

</style>