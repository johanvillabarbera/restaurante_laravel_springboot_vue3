<template>
    <div class="container_charts">
        <div class="chart">
            <chart_login :data="stats.login_predictions" v-if="stats.login_predictions" :title="'Predicción Logins'"/>
        </div>    
        <div class="chart" >
            <chart_login :data="stats.logout_predictions" v-if="stats.logout_predictions" :title="'Predicción Logouts'" />
        </div>    
        <!-- <div class="chart">
            <chart_login />
        </div>  
        <div class="chart">
            <chart_login />
        </div>     -->
    </div>
</template>

<script setup>
    import { ref } from 'vue';
    import { useStats } from '../../composables/stats/useStats'
    import chart_login from './chart_login.vue';
    const drawer = ref(false);


    // use a composable function

    const { stats, loadStatsPredict } = useStats();

    // load the stats
    loadStatsPredict().then(() => {
        console.log(stats.value.logout_predictions);
    }) 


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