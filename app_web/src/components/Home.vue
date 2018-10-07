<template>
  <div>
    <loading v-if="loading"/>
    <div class="jumbotron">
      <div class="container">
        <div class="row">
          <h2>Gerenciador de Processos</h2>
          <h3>Bem vindo, {{getProfile.name}}!</h3>
          <p> Você possui o perfil de {{getProfile.role.name}} e sua visão é: </p>
          <p v-if="isAdmin"> Incluir, excluir, atualizar e visualizar usuários;</p>
          <p v-if="isTriador"> Incluir e visualizar processos;</p>
          <p v-if="isTriador"> Atribuir um ou mais usuários a realizar um parecer sobre um processo;</p>
          <p v-if="isFinalizador"> Visualizar processos pendentes de parecer;</p>
          <p v-if="isFinalizador"> Incluir o parecer sobre o processo;</p>
        </div>
      </div>
    </div>
    <div v-if="!isAuthenticated && authStatus !== 'loading'">
      <h1>Bem vindo ao Gerenciador de Processos</h1>
      <p>Por favor, faça seu login!</p>
      <login/>
    </div>
  </div>
</template>

<script>
  import { mapGetters } from 'vuex'
  import Login from 'components/Login'

  export default {
    components: {
      Login,
    },
    name: 'home',
    computed: {
      ...mapGetters(['getProfile', 'isAuthenticated', 'authStatus', 'isAdmin', 'isTriador', 'isFinalizador']),
      loading: function () {
        return this.authStatus === 'loading' && !this.isAuthenticated
      }
    },
  }
</script>

<style>
  .home {
    display: flex;
    align-items: center;
    flex-direction: column;
  }
</style>