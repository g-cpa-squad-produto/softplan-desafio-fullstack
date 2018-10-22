<template>

<div class="card">
  <div class="card-header">
    <h6 class="c-grey-900" v-if="usuario.id">Altearação de Usuario</h6>
    <h6 class="c-grey-900" v-else >Cadastro de Usuario</h6>
  </div>
  <div class="card-body">
    <!-- Alertas -->
    <div class="alert alert-success" role="alert" v-show="msg">{{msg}}</div>
    <div class="alert alert-danger" role="alert"  v-show="msgErr">{{msgErr}}</div>

    <form @submit.prevent="submit()">
          
      <div class="form-group">
        <label for="nomeInput">Nome</label>
        <input type="text" class="form-control" id="nomeInput" name="nomeInput" @focus="cleanMsg();"
          required placeholder="" autocomplete="false"  v-model="usuario.nome" maxlength="100">
      </div>

      <div class="form-group">
        <label for="loginInput">Login</label>
        <input type="text" class="form-control" id="loginInput" name="loginInput" @focus="cleanMsg();"
          required placeholder="" autocomplete="false"  v-model="usuario.login" maxlength="20">
      </div>

      <div class="form-group">
        <label for="senhaInput">Senha</label>
        <input type="password" class="form-control" id="senhaInput" name="senhaInput" @focus="cleanMsg();"
          required placeholder="" autocomplete="false"  v-model="usuario.senha" maxlength="12">
      </div>

       <div class="form-group">
        <label for="tipoUsuarioInput">Tipo Usuário</label>
        <select class="form-control" id="tipoUsuarioInput" name="tipoUsuarioInput" @focus="cleanMsg();" required  v-model="usuario.tipoUsuario">
          <option value="">Selecione</option>
          <option value="ADMIN">Administrador</option>
          <option value="TRIADOR">Triador</option>
          <option value="FINALIZADOR">Finalizador</option>
        </select>
      </div>
      <button type="submit" class="btn btn-primary">Salvar</button>
    </form>

  </div>
</div>     
</template>

<script>
import Usuario from "@/domains/usuario/Usuario";
import UsuarioService from "@/domains/usuario/UsuarioService";
export default {
  data() {
    return {
      msg: "",
      msgErr: "",
      usuarioId: this.$route.params.usuarioId,
      usuario: new Usuario()
    };
  },

  methods: {
    submit() {
      this.service.salvar(this.usuario).then(
        () => {
          if (this.usuario.id) {
           this.$router.push({name:'UsuarioList', params: {msg: "Alterado com sucesso!"}})
          } else {
            this.msg = "Adicionado com sucesso!";
          }
          this.usuario = new Usuario();
          this.usuario.tipoUsuario = "";
        },
        err => {
          console.log(err);
          this.msgErr = err.body.message;
        }
      );
    },

    cleanMsg() {
      this.msg = "";
      this.msgErr = "";
    }
  },

  created() {
    this.service = new UsuarioService(this.$resource);
    this.usuario.tipoUsuario = "";

    if(this.usuarioId){
      this.service.trazer(this.usuarioId).then(usuario => {
        this.usuario = usuario;
      });
    }
  
  }
};
</script>

<style>
</style>
