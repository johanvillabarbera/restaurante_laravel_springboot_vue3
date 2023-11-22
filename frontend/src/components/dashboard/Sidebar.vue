<template>
    <div class="sidebar_menu">
        <h2>Dashboard</h2>
        <ul>
            <router-link to="/dashboard" :class="{ active: isDashboard }">
                <li>Dashboard</li>
            </router-link>
            <router-link to="/dashboard/tables" :class="{ active: isDashboardTables }">
                <li>Mesas</li>
            </router-link>
            <li>Menus</li>
            <li>Item 4</li>
        </ul>
    </div>
</template>

<script setup>

</script>

<style lang="css">
    .header {
        display: none;
    }
    
    .sidebar_menu {
        position: fixed;
        background-color: #000000;
        width: 10vw;
        height: 100vh;
    }

    .sidebar_menu h2 {
        color: #ff764d;
    }

    .sidebar_menu ul {
        list-style: none;
        margin-top: 2vh;
        text-align: left;
        
    }

    .sidebar_menu ul li {
        margin: 0px;
        /* border: #ff764d solid 1px;
        border-radius: 5px; */
        padding-left: 2vw;
        margin-top: 1vh;
    }

    .sidebar_menu ul li:hover {
        background-color: #ff764d;
        color: #000000;
        cursor: pointer;
    }

    .sidebar_menu ul router-link {
        text-decoration: none;
    }



</style>