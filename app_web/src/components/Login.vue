<template>
  <div>
    <form class="login" @submit.prevent="login">
      <h1>Entrar</h1>
      <label>E-mail</label>
      <input required v-model="email" type="text" placeholder="Snoopy"/>
      <label>Senha</label>
      <input required v-model="password" type="password" placeholder="Password"/>
      <hr/>
      <button type="submit">Login</button>
    </form>
  </div>
</template>

<script>
  import {AUTH_REQUEST} from 'actions/auth'

  export default {
    name: 'login',
    data () {
      return {
        email: 'admin@softplan.com',
        password: '123',
      }
    },
    methods: {
      login: function () {
        const { email, password } = this
        this.$store.dispatch(AUTH_REQUEST, { email, password }).then(() => {
          this.$router.push('/')
        })
      }
    },
  }
</script>

<style>
  .login {
    display: flex;
    flex-direction: column;
    width: 300px;
    padding: 10px;
  }
</style>
