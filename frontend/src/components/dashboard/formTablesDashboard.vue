<template>
    <div class="login-box">
        <h2>Mesa</h2>

        <q-form
      @submit="emitAction(table)"
      class="q-gutter-md"
    >
        <q-input
            filled
            v-model="table.location"
            label="Location"
        />

        <q-input
            filled
            v-model="table.capacity"
            label="Capacity"
        />

        <q-input
            filled
            v-model="table.availability"
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
import { computed, reactive, getCurrentInstance } from 'vue';
import { useRouter } from 'vue-router';
    const { emit } = getCurrentInstance();
    const props = defineProps({
        data: Object
    });

    const dataTable = props.data ? props.data[0] ? props.data[0] : {} : {};

    const table = reactive({
        location: '',
        capacity: '',
        availability: '',
        ...dataTable
    })

    const emitAction = (item) => {
        emit('emitAction', item)
    }
</script>