<template>
  <img src="../../assets/wave.png" class="wave" alt="login-wave">
  <div class="row" style="height: 90vh">
    <div class="col-0 col-md-6 flex justify-center content-center">
      <img src="../../assets/login.svg" class="responsive" alt="login-image">
    </div>
    <div v-bind:class="{'justify-center': $q.screen.md || $q.screen.sm ||$q.screen.xs}"
         class="col-12 col-md-6 flex content-center">
      <q-card v-bind:style="$q.screen.lt.sm ? {'width': '80%'} : {'width': '50%'}">
        <q-card-section>
          <q-avatar size="103px" class="absolute-center shadow-10">
            <img src="../../assets/avatar.svg" alt="avatar">
          </q-avatar>
        </q-card-section>
        <q-card-section>
          <div class="q-pt-lg">
            <div class="col text-h6 ellipsis flex justify-center">
              <h2 class="text-h2 text-uppercase q-my-none text-weight-regular" v-if="isLogin">Login</h2>
              <h2 class="text-h2 text-uppercase q-my-none text-weight-regular" v-else>Register</h2>
            </div>
          </div>
        </q-card-section>
        <q-card-section>
          <q-form class="q-gutter-md" @submit.prevent="submitForm">
            <q-input label="Username" v-model="state.username">
            </q-input>
            <q-input label="Password" type="password" v-if="isLogin" v-model="state.password">
            </q-input>
            <q-input label="name" type="text" v-if="!isLogin" v-model="state.name">
            </q-input>
            <q-input label="surname" type="text" v-if="!isLogin" v-model="state.surname">
            </q-input>
            <q-input label="email" type="email" v-if="!isLogin" v-model="state.email">
            </q-input>
            <q-input label="password" type="password" v-if="!isLogin" v-model="state.password">
            </q-input>
            <q-input label="confirm password" type="password" v-if="!isLogin" v-model="state.confirmPassword">
            </q-input>
            <q-input label="phone" type="number" v-if="!isLogin" v-model="state.phone">
            </q-input>
            <q-input label="address" type="text" v-if="!isLogin" v-model="state.address">
            </q-input>
            <q-input label="birth date" type="date" v-if="!isLogin" v-model="state.birth_date">
            </q-input>
            <div>
              <q-btn @click="login" class="full-width" color="primary" label="Login" v-if="isLogin" type="button" rounded></q-btn>
              <q-btn @click="register" class="full-width" color="primary" label="Register" v-else type="button" rounded></q-btn>
              <div class="text-center q-mt-sm q-gutter-lg">
                <router-link class="text-black" to="/login">Has olvidado tu contraseña?</router-link>
                <router-link class="text-black" to="/register" v-if="isLogin">Crear cuenta</router-link>
                <router-link class="text-black" to="/login" v-else>Ya tienes cuenta?</router-link>
              </div>
            </div>
          </q-form>
        </q-card-section>
      </q-card>
    </div>
  </div>
</template>

<script setup>
import { useQuasar } from 'quasar';
import { mapActions } from 'vuex';
import { reactive, getCurrentInstance } from 'vue';

let $q = useQuasar()

const props = defineProps({
  isLogin: Boolean
});

const { emit } = getCurrentInstance();

const state = reactive({
  username: '',
  password: '',
  confirmPassword: '',
  name: '',
  surname: '',
  email: '',
  phone: '',
  address: '',
  birth_date: ''
})

const submitForm = async () => {
  // Add comprobations to check the password length, uppercase, lowercase, numbers and special characters
  
  if (!state.username || !state.password) {
    $q.notify({
      type: 'negative',
      message: 'Rellenga todos los campos'
    });
    return false;
  } else if (state.password.length < 8 || !/[A-Z]/.test(state.password) || !/[a-z]/.test(state.password) || !/[0-9]/.test(state.password) || !/[!@#$%^&*]/.test(state.password)) {
    // $q.notify({
    //   type: 'negative',
    //   message: 'La contraseña debe tener como mínimo 8 carácteres, una letra mayuscula, una letra minúscula, un número y un caracter especial.'
    // })
    return true;
  }

  return true;

}

const login = () => {
    const data = {
        username: state.username,
        password: state.password
    };

    submitForm() 
    ? emit('send', data)
    : undefined;

    // emit('send', data);
}

const checkRegister = () => {
  // Comprueba que esten todos los campos y que las contraseñas sean iguales

  if (!state.username, !state.password, !state.name, !state.email, !state.phone, !state.address, !state.birth_date) {
    $q.notify({
      type: 'negative',
      message: 'Rellenga todos los campos'
    });
    return false;
  } else if (state.password !== state.confirmPassword) {
    $q.notify({
      type: 'negative',
      message: 'Las contraseñas no coinciden'
    });
    return false;
  }

  return true;
}

const register = () => {
    const data = {
        clientID: 0,
        username: state.username,
        password: state.password,
        name: state.name,
        surname: state.surname,
        email: state.email,
        tlf: state.phone,
        address: state.address,
        birth_date: state.birth_date
    };

    checkRegister()
    ? emit('send', data)
    : undefined;
    // emit('send', data);
}

const { doLogin } = mapActions('auth', ['doLogin'])
</script>

<style scoped>

.wave {
  position: fixed;
  height: 100%;
  left: 0;
  bottom: 0;
  z-index: -1;
}
</style>