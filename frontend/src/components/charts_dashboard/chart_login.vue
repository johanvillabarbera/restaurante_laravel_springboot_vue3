<template>
    <div>
        <canvas ref="chartCanvas"></canvas>
    </div>
</template>
  
<script setup>
import { onMounted, ref } from 'vue';
import { Chart, registerables } from 'chart.js';
Chart.register(...registerables);

const chartCanvas = ref(null);

const props = defineProps({
   data: Array,
   title: String
});



let days, values = [];

if(props.data) {
    console.log(props.data);
    days = props.data.map(element => element.day);
    values = props.data.map(element => element.value);
    console.log(days);
}

// CONSULTA

// SELECT *
// SELECT 
//     DAYNAME(date_sub) AS day_of_week,
//     date_sub AS date,
//     COALESCE(COUNT(ue.event_timestamp), 0) AS login_count
// FROM (
//     SELECT DATE(NOW()) - INTERVAL c.number DAY AS date_sub
//     FROM 
//     (
//         SELECT 0 AS number
//         UNION SELECT 1
//         UNION SELECT 2
//         UNION SELECT 3
//         UNION SELECT 4
//         UNION SELECT 5
//         UNION SELECT 6
//     ) AS c
// ) AS date_sequence
// LEFT JOIN viberest.user_event_table ue
// ON DATE(ue.event_timestamp) = date_sequence.date_sub
// AND ue.event_type LIKE 'LOGIN'
// GROUP BY date_sequence.date_sub
// ORDER BY date_sequence.date_sub;

onMounted(() => {
    const ctx = chartCanvas.value.getContext('2d');
    new Chart(ctx, {
        type: 'line',
        data: {
            labels: days,
            datasets: [{
                label: props.title,
                backgroundColor: 'rgb(255, 99, 132)',
                borderColor: 'rgb(255, 99, 132)',
                data: values,
            }]
        },
        options: {
            responsive: true,
            plugins: {
                legend: {
                    position: 'top',
                },
                title: {
                    display: true,
                    text: props.title
                }
            }
        }
    });
});
</script>

  