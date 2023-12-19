<template>
    <div class="user_details">
        <div class="user_image">
            <img src="../../assets/user-image.webp" alt="User Image" />
        </div>
        <div class="user_info">
            <span class="user_name">Miguel</span>
            <span class="user_role">Administrator</span>
        </div>
    </div>
    <div class="content_profile">
        <ul class="menu_profile">
            <li><span>Estadísticas</span></li>
            <li><span>Reservas</span></li>
            <li><span>Configuración</span></li>
        </ul>

        <div class="contenedor_estadisticas">
            <div class="estadisticas">
                <p>Estadísticas</p>
                <div class="estadistica">
                    <div class="numero">10</div>
                    <div class="texto">Reservas</div>
                </div>
                <div class="estadistica">
                    <div class="numero">10</div>
                    <div class="texto">Reservas</div>
                </div>
                <div class="estadistica">
                    <div class="numero">10</div>
                    <div class="texto">Reservas</div>
                </div>
            </div>
        </div>

    </div>
</template>

<script setup>

</script>

<style lang="css" scoped>
    .user_details {
        /* background image: ../../assets/background-profile.jpg */
        background-image: url("../../assets/background-profile.png");
        background-size: cover;
        background-position: center;
        padding: 2vh;
        text-align: right;
        display: flex;
        flex-direction: row;
        justify-content: space-between;
        background-color: #000000;
        min-height: 14vh;
    }

    .user_details .user_image {
        width: 10vh;
        height: 10vh;
        border-radius: 50%;
        overflow: hidden;
        border: 2px solid white;
        margin-right: 2vh;
        margin-left: 10vw;
    }

    .user_details .user_image img {
        width: 100%;
        height: 100%;
        object-fit: cover;
    }

    .user_details .user_info {
        display: flex;
        flex-direction: column;
        justify-content: center;
        align-items: flex-start;
        font-weight: bold;
        color: white;
    }

    .menu_profile {
        display: flex;
        list-style: none;
        padding-left: 10vw;
        /* justify-content: space-around; */
        width: 100%;
        background-color: white;
    }

    .menu_profile li {
        display: inline-block;
        margin-right: 30px;
        cursor: pointer;
        padding: 6px;
        color: black;
        font-weight: bold;
        font-size: 16px;
    }

    .menu_profile li:hover {
        color: #ff764d;
    }   

    .menu_profile li:last-child {
        margin-right: 0;
    }

    .contenedor_estadisticas {
        display: flex;
        justify-content: center;
        align-items: center;
        margin-top: 5vh;
    }

    .estadisticas {
        display: flex;
        flex-direction: column;
        justify-content: center;
        align-items: center;
        width: 40%;
        background-color: #ff764d;
        border-radius: 10px;
        padding: 2vh;
    }

    .estadisticas p {
        font-size: 1.5em;
        font-weight: bold;
        color: white;
    }

    .estadistica {
        display: flex;
        justify-content: center;
        align-items: center;
        margin-top: 2vh;
        cursor: pointer;
    }

    .estadistica .numero {
        font-size: 2em;
        font-weight: bold;
        color: white;
        margin-right: 2vw;
    }

    .estadistica .texto {
        font-size: 1.5em;
        font-weight: bold;
        color: white;
    }

    .estadistica .numero::after {
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

    .estadistica .numero:hover::after {
        width: 100%;
    }

    .estadistica .texto::after {
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

    .estadistica .texto:hover::after {
        width: 100%;
    }
    

</style>
