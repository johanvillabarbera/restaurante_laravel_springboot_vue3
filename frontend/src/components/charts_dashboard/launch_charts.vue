<template>
    <div class="container_charts">
        <div class="chart">
            <chart_login />
        </div>    
        <div class="chart">
            <chart_login />
        </div>    
        <div class="chart">
            <chart_login />
        </div>  
        <div class="chart">
            <chart_login />
        </div>    
    </div>
</template>

<script setup>
    import { ref } from 'vue';
    import chart_login from './chart_login.vue';
    const drawer = ref(false);
</script>

<style scoped>
    .container_charts{
        width: 100%;
        height: 100%;
        /* background-color: #fff; */
        display: flex;
        flex-direction: row;
        flex-wrap: wrap;
    }

    .chart {
        width: 40%;
        height: auto;
        margin-left: 5%;
    }
</style>