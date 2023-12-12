<template>
    <div class="welcome">
        <!-- <h1 class="text-brown-10">Bienvenido a VibeRest</h1> -->
        <!-- <img src="../../../public/Quasar_Logo.png" alt=""> -->
        <section class="content_uno_home">
            <div class="content">
                <img src="https://picsum.photos/id/1060/536/354?blur=2" alt="img uno">
                <p class="text-dark">Iníciate en un viaje culinario donde cada plato cuenta una historia y cada sabor te transporta a un rincón diferente del mundo. 
                    En VibeRest, creemos que la alta cocina es más que comida: es una experiencia. Nuestro restaurante es un refugio para los entendidos 
                    y aquellos que aprecian el arte de la gastronomía.
                </p>
            </div>
        </section>
    </div>
    <MenuList @emitAction="redirectReservation"></MenuList>
</template>

<script setup>
import MenuList from '../menus/menusList.vue'
import { useRouter } from 'vue-router';
    const router = useRouter();

    const redirectReservation = (menu) => {
            const filters = {
                name: menu.name,
                diners_number: 2,
                booking_day: autoCalculateDate(),
                turnID: 1
            };
            const filters_ = btoa(JSON.stringify(filters));
            router.push({ name: "reservationFilters", params: { filters: filters_ } });
    }

    const autoCalculateDate = () => {
        const ONE_MORE_DAY = 1;
        const actualDate = new Date();
        actualDate.setDate(actualDate.getDate() + ONE_MORE_DAY)
        return actualDate
    }
</script>

<style lang="css">
/* img {
    transform: rotate(0);
    transition: transform .8s ease-in-out;
} */
    .content_uno_home {
        display: flex;
        justify-content: center;
    }

    .content_uno_home .content {
        width: 90%;
        margin-top: 20px;
        background-color: black;
        color: white;
        border-radius: 10px;
        padding: 50px;
        display: flex;
        align-items: center;
        justify-content: space-between;
    }

    .content_uno_home .content img {
        width: 50%;
        border-radius: 10px;
    }

    body {
        background: linear-gradient(to bottom, #f9f9f9, #e0e0e0);
    }

</style>