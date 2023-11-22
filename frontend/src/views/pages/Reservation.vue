<template>
    <TableCard :data="state.tables"/>
</template>

<script setup>

    import TableCard from '../../components/tables/tableCard.vue';
    import { useStore } from 'vuex';
    import adminConstant from '../../store/modules/admin/adminConstant'
    import { computed, reactive } from 'vue';

    const store = useStore();

    store.dispatch(`tables/${adminConstant.INITIALIZE_TABLES}`);

    const state = reactive({
        tables: computed(() => store.getters['tables/GetTables'])
    });
</script>

<style lang="css">

</style>