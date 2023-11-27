<template>
    <q-select filled v-model="model" :options="state.menu" label="Filled" />
    <TableCard :data="state.tables"/>
</template>

<script setup>

    import TableCard from '../../components/tables/tableCard.vue';
    import { useStore } from 'vuex';
    import adminConstant from '../../store/modules/admin/adminConstant'
    import clientConstant from '../../store/modules/client/clientConstant'
    import { computed, reactive } from 'vue';
    import { useRouter, useRoute } from 'vue-router';

    const store = useStore();

    store.dispatch(`tables/${adminConstant.INITIALIZE_TABLES}`);

    store.dispatch(`menu/${clientConstant.INITIALIZE_MENU}`);

    const state = reactive({
        tables: computed(() => store.getters['tables/GetTables']),
        menu: computed(() => store.getters['menu/GetMenus']?.map(item => item.name))
    });

    const route = useRoute();

    try {
        if (route.params.filters !== '') {
            console.log(JSON.parse(atob(route.params.filters)));
            // filters_URL = JSON.parse(atob(route.params.filters));
        }
    } catch (error) {
    }
</script>

<style lang="css">

</style>