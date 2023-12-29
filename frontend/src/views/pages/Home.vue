<template>
    <div class="welcome">
        <!-- <h1 class="text-brown-10">Bienvenido a VibeRest</h1> -->
        <!-- <img src="../../../public/Quasar_Logo.png" alt=""> -->
        <section class="content_uno_home">
            <q-carousel
                v-model="slide"
                animated
                arrows
                infinite
                navigation
                style="height: 400px; width: 90%; margin: 20px auto; background-color: black; color: white; border-radius: 10px;">
                
                <q-carousel-slide
                    v-for="(image, index) in images"
                    :key="index"
                    :name="index"
                    :img-src="image.src">
                    <div class="custom-caption">
                        <p>{{ image.caption }}</p>
                    </div>
                </q-carousel-slide>

            </q-carousel>
        </section>
    </div>
    <MenuList @emitAction="redirectReservation"></MenuList>
</template>

<script setup>
import MenuList from '../menus/menusList.vue'
import { useRouter } from 'vue-router';
import { ref } from 'vue';

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

    const images = ref([
    { src: '../public/slid1.jpg', caption: 'Iníciate en un viaje culinario...' },
    { src: '../public/slid2.jpg', caption: 'Iníciate en un viaje culinario...' },

])

const slide = ref(0)

</script>

<style lang="css" scoped>
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
        background: linear-gradient(to bottom, #333333, #1a1a1a);
    }

    .custom-caption {
    position: absolute;
    bottom: 20px;
    left: 20px;
    color: white;
    background-color: rgba(0, 0, 0, 0.5);
    padding: 10px;
    border-radius: 5px;
}

</style>