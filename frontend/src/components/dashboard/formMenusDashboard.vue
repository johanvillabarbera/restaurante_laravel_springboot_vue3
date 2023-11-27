<template>
    <div class="login-box">
        <h2>Menu</h2>

        <q-form
      @submit="emitAction(state.menuLocal)"
      class="q-gutter-md"
    >
        <q-input
            filled
            v-model="state.menuLocal.name"
            label="Location"
        />

        <q-input
            filled
            v-model="state.menuLocal.price"
            label="Capacity"
        />

        <q-input
            filled
            v-model="state.menuLocal.description"
            label="Availability"
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

    const menu_ = props.data ? props.data : { 'name': '' };
    const state = reactive({
            menuLocal: { ...menu_ }
    });

    const emitAction = (item) => {
        emit('emitAction', item);
    }
</script>