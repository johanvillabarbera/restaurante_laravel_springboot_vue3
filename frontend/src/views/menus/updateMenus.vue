<template>
    <font-awesome-icon icon="fa-solid fa-arrow-left" class="fa-2x" style="cursor:pointer; margin-left:0.2em;"
        @click="redirects.return()" />
    <FormMenusDashboard :data="state.menu" :key="state.menu?.id" @emitAction="updateMenus" />
</template>

<script setup>
import { reactive, computed } from 'vue';
import { useStore } from 'vuex';
import adminConstant from '../../store/modules/admin/adminConstant';
import { useRouter } from 'vue-router';
import { useRoute } from 'vue-router';
import FormMenusDashboard from '../../components/dashboard/formMenusDashboard.vue';

    const store = useStore();
    const route = useRoute();
    const router = useRouter();

    store.dispatch(`menus/${adminConstant.INITIALIZE_MENU}`, route.params.id);

    const state = reactive({
        menu: computed(() => store.getters[`menus/GetMenu`])
    });
    console.log(state.menu)
    const updateMenus = (data) => {
        store.dispatch(`menus/${adminConstant.UPDATE_MENUS}`, data);
        redirects.return();
    };

    const redirects = {
        return: () => router.push({ name: 'dashboard' })
    }

</script>