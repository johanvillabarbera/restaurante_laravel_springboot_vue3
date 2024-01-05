<template>
    <div class="tables-container">
        <div class="table-container" v-for="n in 6" :key="n">
            <div class="table skeleton">
                <div class="chair chair-top"></div>
                <div class="chair chair-right"></div>
                <div class="chair chair-bottom"></div>
                <div class="chair chair-left"></div>
                <div class="table-status">Aplica filtros</div>
            </div>
        </div>
    </div>
</template>
  
<script setup>

</script>
  
<style scoped>
.tables-container {
    display: flex;
    flex-wrap: wrap;
    justify-content: center;
    align-items: center;
    height: 100%;
}

.table-container {
    padding: 60px;
}

.table.skeleton {
    position: relative;
    width: 30%;
    min-width: 200px;
    height: 100px;
    margin: 10px;
    background-color: #e0e0e0;
    /* Color de esqueleto */
    border-radius: 10px;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
}

/* Resto de los estilos .chair y .chair-[position] */

.table-status {
    position: absolute;
    top: 0;
    left: 0;
    right: 0;
    bottom: 0;
    display: flex;
    align-items: center;
    justify-content: center;
    color: #a0a0a0;
    /* Color de texto de esqueleto */
    font-weight: bold;
    font-size: 1.5em;
    border-radius: 10px;
    background-color: rgba(0, 0, 0, 0.1);
    /* Color de fondo de esqueleto */
}

/* Puedes agregar estilos hover y media queries si son necesarios */</style>
  