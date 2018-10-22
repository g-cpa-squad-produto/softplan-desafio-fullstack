<template>

<div class="card">
  <div class="card-header">
    <h6>Login</h6>
  </div>
  <div class="card-body">

    <div class="alert alert-danger" role="alert"  v-show="msgErr">{{msgErr}}</div>
   
    <form @submit.prevent="submit()">
          
      <div class="form-group">
        <label for="loginInput">Login</label>
        <input type="text" class="form-control" id="loginInput" name="loginInput"
          required placeholder="" autocomplete="false"  v-model="usuario.login" maxlength="20">
      </div>

      <div class="form-group">
        <label for="senhaInput">Senha</label>
        <input type="password" class="form-control" id="senhaInput" name="senhaInput"
          required v-model="usuario.senha" maxlength="12">
      </div>

      <button type="submit" class="btn btn-primary">Enviar</button>
    </form>

  </div>
</div>     
</template>

<script>
import Usuario from "@/domains/usuario/Usuario";
export default {
  data() {
    return {
      usuario: new Usuario(),
      msgErr:""
    };
  },

  methods: {
    submit() {
      this.$auth.login({
        params: { login: this.usuario.login, senha: this.usuario.senha },
        fetchUser: true,
        success: function() {
          console.log("Usuário logado com sucesso.");
        },
        error: function() {
          this.msgErr = "Usuário e/ou senha inválidos.";
          console.log("Usuário e/ou senha inválidos.");
        },
        rememberMe: true,
        redirect: "/"
      });
    }
  }
};
</script>

<style>
</style>
