<template>

<ul class="navbar-nav mr-auto">
          
  <li class="nav-item dropdown" v-if="$auth.check('ADMIN')">
    <a class="nav-link dropdown-toggle" href="/usuario/list" id="dropdown01" 
    data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Usuarios</a>
    <div class="dropdown-menu" aria-labelledby="dropdown01">
      <router-link class="dropdown-item" to="/usuario/form">Cadastro de Usuario</router-link>
      <router-link class="dropdown-item" to="/usuario/list">Lista de Usuario</router-link>
    </div>
  </li>
  
  <li class="nav-item dropdown" v-if="$auth.check('TRIADOR') || $auth.check('FINALIZADOR')">
    <a class="nav-link dropdown-toggle" href="/usuario/list" id="dropdown01" 
    data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Processo</a>
    <div class="dropdown-menu" aria-labelledby="dropdown01">
      <router-link v-if="$auth.check('TRIADOR')" class="dropdown-item" to="/processo/form">Cadastro de Processo</router-link>
      <router-link v-if="$auth.check('TRIADOR')" class="dropdown-item" to="/processo/list">Lista de Processos</router-link>
      <router-link v-if="$auth.check('FINALIZADOR')" class="dropdown-item" to="/processo/pendente/usuario/">Processos Pendestes de Parecer</router-link>
    </div>
  </li>
  
</ul>
</template>

<script>
export default {

}
</script>


<style scoped lang="scss">
</style>
