<template>
    <div class="account-verified">
        <div class="message-container">
            <img src="../../assets/avatar.svg" alt="Account Verified" class="verified-icon" />
            <h1>Tu cuenta ha sido verificada</h1>
            <p>¡Felicidades! Tu cuenta ha sido verificada con éxito. Por favor, accede al <router-link
                    to="/login">login</router-link> para entrar.</p>
        </div>
    </div>
</template>
  
<script setup>

</script>
  
<style scoped>
.account-verified {
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100vh;
    background-color: #f0f0f0;
    text-align: center;
}

.message-container {
    max-width: 400px;
}

.verified-icon {
    width: 100px;
    height: auto;
    margin-bottom: 20px;
}
</style>
  