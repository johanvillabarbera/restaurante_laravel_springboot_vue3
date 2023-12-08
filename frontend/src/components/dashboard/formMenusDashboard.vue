<template>
    <div class="login-box">
        <h2>Menu</h2>

        <q-form
      @submit="emitAction(menu)"
      class="q-gutter-md"
    >
        <q-input
            filled
            v-model="menu.name"
            label="Name"
        />

        <q-input
            filled
            v-model="menu.price"
            label="Price"
        />

        <q-input
            filled
            v-model="menu.description"
            label="Description"
        />

      <div>
        <q-btn label="Submit" type="submit" color="primary"/>
        <q-btn label="Reset" type="reset" color="primary" flat class="q-ml-sm" />
      </div>
    </q-form>
    </div>
</template>

<script setup>
import { reactive, getCurrentInstance } from 'vue';
import { useRouter } from 'vue-router';

    const { emit } = getCurrentInstance();
    const props = defineProps({
        data: Object
    });

    const dataMenu = props.data ? props.data[0] ? props.data[0] : {} : {}

    const menu = reactive({
        name: '',
        price: '',
        description: '',
        ...dataMenu
    })

    const emitAction = (item) => {
        emit('emitAction', item);
    }
</script>