<template>
  <div class="container">
    <b-container fluid>

      <NavBar>
        <b-nav-item href="#/usuarios" v-if="auth.roles['Usuario.Visualizar']">Usuários</b-nav-item>
        <b-nav-item href="#/processos" v-if="auth.roles['Processo.Visualizar']" >Processos</b-nav-item>
      </NavBar>

      <h1>Softplan Desafio</h1>

    </b-container>
  </div>
</template>


<script>

  import {mapState} from 'vuex'
  import NavBar from "./layout/NavBar";

export default {
  name: 'Home',
  components: {NavBar},
  computed: {
    ...mapState(['auth'])
  },
}
</script>

<style scoped>
  .container {
    width: 100%;
  }
</style>
