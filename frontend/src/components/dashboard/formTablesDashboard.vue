<template>
    <div class="login-box">
        <h2>Mesa</h2>

        <q-form
      @submit="emitAction(state.tableLocal)"
      class="q-gutter-md"
    >
        <q-input
            filled
            v-model="state.tableLocal.location"
            label="Location"
        />

        <q-input
            filled
            v-model="state.tableLocal.capacity"
            label="Capacity"
        />

        <q-input
            filled
            v-model="state.tableLocal.availability"
            label="Availability"
        />

        <!-- <q-select
            filled
            v-model="multiple"
            multiple
            :options="options"
            label="Multiple"
            style="width: 250px"
        /> -->

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

    const table_ = props.data ? props.data : { 'name': '' };
    const state = reactive({
            tableLocal: { ...table_ }
    });

    console.log(props.data);

    const emitAction = (item) => {
        emit('emitAction', item)
    }
</script>