<template>

    <div v-if="props.data === 'basic'" class="q-pa-md">
        <q-btn color="purple" @click="showNotif" label="{{ props.data }}" />
    </div>

</template>

<script setup>

    const props = defineProps({
        type: '',
        data: ''
    });

</script>