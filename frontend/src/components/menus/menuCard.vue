<template>

    <div class="menu" v-for="item in props.data">
        <p>{{ item.name }}</p>
        <p>{{ item.description }}</p>
        <p>{{ item.price }} €</p>
    </div>

</template>

<script setup>
    const props = defineProps({
        data: Object
    });
</script>

<style lang="css">
    .menu {
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

    .menu:hover {
        transform: translateY(-5px);
        box-shadow: 0 6px 12px rgba(0, 0, 0, 0.15);
    }
    
    .menu p:first-child {
        font-weight: bold;
    }
</style>