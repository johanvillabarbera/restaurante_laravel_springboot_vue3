<template>
    <div class="q-pa-md row items-start q-gutter-md justify-around">
        <q-card class="my-card" flat bordered v-for="item in props.data">
        <q-img src="https://cdn.quasar.dev/img/chicken-salad.jpg" />

        <q-card-section>
            <q-btn
            fab
            color="deep-orange-10"
            icon="today"
            class="absolute"
            style="top: 0; right: 12px; transform: translateY(-50%);"
            />

            <div class="row no-wrap items-center">
            <div class="col text-h6 ellipsis text-dark">
                {{ item.name }}
            </div>
            <div class="col-auto text-grey text-caption q-pt-md row no-wrap items-center">
                <q-icon name="place" />
                ----
            </div>
            </div>
        </q-card-section>

        <q-card-section class="q-pt-none">
            <div class="text-subtitle1">
                {{ item.price  }}
            </div>
            <div class="text-caption text-grey">
                {{ item.description }}
            </div>
        </q-card-section>

        <q-separator />

        <q-card-actions>
            <q-btn flat round icon="event" />
            <q-btn flat class="text-deep-orange-10" @click="emitAction(item)">
            Reservar
            </q-btn>
        </q-card-actions>
        </q-card>
    </div>
</template>

<script setup>
import { getCurrentInstance } from 'vue';

    const props = defineProps({
        data: Object
    });

    const { emit } = getCurrentInstance();
    const emitAction = (item) => {
        emit('emitAction', item)
    }
</script>

<style lang="sass" scoped>
.my-card
  width: 100%
  max-width: 300px
  margin-bottom: 1rem
</style>