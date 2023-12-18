<template>

    <div class="table" v-for="item in props.data">
        <p>{{ item.tableID }}</p>
        <p>{{ item.capacity }}</p>
        <p>{{ item.location }} </p>
    </div>

</template>

<script setup>
    const props = defineProps({
        data: Object
    });
</script>

<style lang="css" scoped>
    .table {
        color: #333;
        width: calc(100% / 4 - 2%); 
        margin: 1%;
        padding: 20px;
        background-color: #f9f9f9;
        border: 1px solid #ddd;
        border-radius: 15px;
        box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1); 
        display: flex;
        flex-direction: column;
        justify-content: space-between;
        align-items: flex-start;
        font-weight: normal;
        transition: transform 0.3s ease, box-shadow 0.3s ease;
    }

    .table:hover {
        transform: translateY(-5px);
        box-shadow: 0 6px 12px rgba(0, 0, 0, 0.15);
    }
    
    .table p:first-child {
        font-weight: bold;
    }
</style>