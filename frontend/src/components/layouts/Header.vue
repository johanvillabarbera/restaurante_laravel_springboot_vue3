<template>
    <nav class="header">
        <p class="title_header">VIBEREST</p>
        <ul>
            <router-link to="/home" class="link" :class="{ active: isHome }">
                <li><span>Home</span></li>
            </router-link>
            <router-link to="/dashboard" class="link" :class="{ active: isDashboard }">
                <li><span>Dashboard</span></li>
            </router-link>
            <router-link to="/reservation" class="link">
                <li><span>Reservas</span></li>
            </router-link>
            <router-link to="/login" class="link">
                <li v-if="!state.isLogged"><span>Login</span></li>
            </router-link>
            <router-link to="/develop" class="link">
                <li><span>Develop</span></li>
            </router-link>
            <li @click="logout()" v-if="state.isLogged" class="page link"><span>Log Out</span></li>
        </ul>
    </nav>
</template>

<script setup>

    import { useStore } from 'vuex';
    import { reactive, computed } from 'vue';
    import authConstant from '../../store/modules/auth/authConstant';

    const store = useStore();

    const state = reactive({
        isLogged: computed(() => store.getters['auth/GetIsAuth'])
    })
    
    const logout = () => {
        store.dispatch(`auth/${authConstant.LOGOUT}`);
    }

</script>

<style lang="css" scoped>
    .header {
        background-color: #000000;
        padding: 2vh;
        text-align: right;
        display: flex;
        flex-direction: row;
        justify-content: space-between;
    }

    .header ul li {
        display: inline-block;
        margin-right: 30px;
    }

    .header ul li:last-child {
        margin-right: 0;
    }

    .header ul li span {
        font-size: 18px;
        font-weight: 600;
        display: block;
        text-decoration: none;
        position: relative;
        color: #ffffff;
        margin-right: 1vw;
        margin-left: 1vh;
    }

    .header ul li span::after {
        content: "";
        display: block;
        position: absolute;
        left: 0;
        bottom: 10;
        width: 0%;
        background-color: #ff764d;
        height: 2px;
        transition: 300ms all;
    }

    .header ul li span:hover::after {
        width: 100%;

    }

    .header .title_header {
        color: #ff764d;
        font-size: 1.4em;
        font-weight: bold;
    }
</style>