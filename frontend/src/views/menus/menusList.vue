<template>
    <h3>que pasaaaaa</h3>

    <li v-for="item in state.menus">
        <p>{{ item.menuID }} || {{ item.name }} || {{ item.description }} || {{ item.price }}</p>
    </li>
    <MenuCard></MenuCard>
</template>

<script setup>
import MenuCard from '../../components/menus/menuCard.vue'
import { useStore } from 'vuex';
import clientConstant from '../../store/modules/client/clientConstant'
import { computed, reactive } from 'vue';
    const store = useStore();

    store.dispatch(`menu/${clientConstant.INITIALIZE_MENU}`);

    const state = reactive({
        menus: computed(() => store.getters['menu/GetMenus'])
    });

</script>