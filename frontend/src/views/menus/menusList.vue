<template>
    <section class="menu_list">
        <h2 class="text-deep-orange-6">Menus</h2>
        <div class="load_menus">
            <MenuCard :data="state.menus" @emitAction="emitAction"/>
        </div>
    </section>
    <!-- <li v-for="item in state.menus">
        <p>{{ item.menuID }} || {{ item.name }} || {{ item.description }} || {{ item.price }}</p>
    </li> -->
</template>

<script setup>
import MenuCard from '../../components/menus/menuCard.vue'
import { useStore } from 'vuex';
import clientConstant from '../../store/modules/client/clientConstant'
import { computed, getCurrentInstance, reactive } from 'vue';
    const store = useStore();

    store.dispatch(`menu/${clientConstant.INITIALIZE_MENU}`);

    const state = reactive({
        menus: computed(() => store.getters['menu/GetMenus'])
    });

    const { emit } = getCurrentInstance();
    const emitAction = (item) => {
        emit('emitAction', item)
    }

</script>

<style lang="css">

    .menu_list h2 {
        font-size: 3em;
        font-weight: 600;
        text-align: center;
        color: #ffffff;
    }
    .load_menus {
        width: 100%;
        height: auto;
        display: flex;
        flex-direction: row;
        flex-wrap: wrap;
        justify-content: space-evenly;
    }

</style>