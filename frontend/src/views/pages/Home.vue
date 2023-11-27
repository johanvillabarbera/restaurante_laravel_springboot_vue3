<template>
    <div class="welcome">
        <h1 class="text-brown-10">Bienvenido a VibeRest</h1>
        <section class="content_uno_home">
            <img src="https://picsum.photos/id/1060/536/354?blur=2" alt="img uno">
            <p class="text-dark">Iníciate en un viaje culinario donde cada plato cuenta una historia y cada sabor te transporta a un rincón diferente del mundo. 
                En VibeRest, creemos que la alta cocina es más que comida: es una experiencia. Nuestro restaurante es un refugio para los entendidos 
                y aquellos que aprecian el arte de la gastronomía.
            </p>
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
    /* .welcome h1 {
        font-size: 3em;
        font-weight: 600;
        text-align: center;
        background: -webkit-linear-gradient(left, #ff4500, #ff6a4d, #ff8c00);
        -webkit-background-clip: text;
        color: transparent;
        -webkit-text-fill-color: transparent; 
    }

    .content_uno_home {
        display: flex;
        flex-direction: row;
        justify-content: space-between;
        align-items: center;
        width: 80%;
        height: 40vh;
        margin: 0 auto; 
    }

    .content_uno_home img, .content_uno_home p {
        animation-duration: 1s;
        animation-timing-function: ease;
        animation-fill-mode: forwards;
    }

    .content_uno_home img {
        animation-name: deslizarImagenDesdeArriba;
        width: 40%;
        height: 80%;
        border-radius: 10px;
    }

    .content_uno_home p {
        animation-name: deslizarParrafoDesdeDerecha;
        margin-left: 10%;
        font-size: 1.2em;
        font-weight: 600;
        text-align: justify;
        width: auto;
    }

    @keyframes deslizarImagenDesdeArriba {
        from {
            transform: translateY(-100%); 
        }
        to {
            transform: translateY(0); 
        }
    }

    @keyframes deslizarParrafoDesdeDerecha {
        from {
            transform: translateX(100%); 
            opacity: 0; 
        }
        to {
            transform: translateX(0); 
            opacity: 1; 
        }
    } */
</style>