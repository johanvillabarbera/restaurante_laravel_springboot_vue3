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
              <h2 class="text-h2 text-uppercase q-my-none text-weight-regular">Login</h2>
            </div>
          </div>
        </q-card-section>
        <q-card-section>
          <q-form class="q-gutter-md" @submit.prevent="submitForm">
            <q-input label="Username" v-model="state.username">
            </q-input>
            <q-input label="Password" type="password" v-model="state.password">
            </q-input>
            <div>
              <q-btn @click="login" class="full-width" color="primary" label="Login" type="button" rounded></q-btn>
              <div class="text-center q-mt-sm q-gutter-lg">
                <router-link class="text-black" to="/login">Esqueceu a senha?</router-link>
                <router-link class="text-black" to="/login">Criar conta</router-link>
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
  password: ''
})

const submitForm = async () => {
  // Add comprobations to check the password length, uppercase, lowercase, numbers and special characters

  if (!state.username || !login.password) {
    $q.notify({
      type: 'negative',
      message: 'Os dados informados são inválidos.'
    })
  } else if (state.password.length < 8 || !/[A-Z]/.test(state.password) || !/[a-z]/.test(state.password) || !/[0-9]/.test(state.password) || !/[!@#$%^&*]/.test(state.password)) {
    $q.notify({
      type: 'negative',
      message: 'A senha deve ter pelo menos 8 caracteres, uma letra maiúscula, uma letra minúscula, um número e um caractere especial.'
    })
  }

}

const login = () => {
    const data = {
        username: state.username,
        password: state.password
    };
    emit('send', data);
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