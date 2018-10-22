<template>

<div class="card">
  <div class="card-header">
    <h6>Lista de Usuario</h6>
  </div>
  <div class="card-body">
    <!-- Alertas -->
    <div class="alert alert-success" role="alert" v-show="msg">{{msg}}</div>
    <div class="alert alert-danger" role="alert"  v-show="msgErr">{{msgErr}}</div>

    <table class="table">
      <thead>
        <tr>
          <th scope="col">#</th>
          <th scope="col">Nome</th>
          <th scope="col">Login</th>
          <th scope="col">Tipo Usuário</th>
          <th scope="col"></th>
        </tr>
      </thead>
      <tbody>
        <tr  v-for="(usuario, index) of list" :key="usuario.id">
          <th scope="row">{{index + 1}}</th>
          <td>{{usuario.nome}}</td>
          <td>{{usuario.login}}</td>
          <td>{{usuario.tipoUsuario}}</td>
          <td align="right">
            <router-link :to="{name : 'UsuarioForm', params: {usuarioId: usuario.id}}">
              <font-awesome-icon icon="edit" style="margin-right:5px; color:black;" title="Editar"/>
            </router-link>
            <font-awesome-icon icon="trash" @click="remove(usuario)" title="Remover"/>
          </td>
        </tr>
      </tbody>
    </table>
    <router-link :to="{name : 'UsuarioForm'}">
      <button type="submit" class="btn btn-primary">Novo</button>
    </router-link>
  </div>
</div>     
</template>

<script>
import UsuarioService from "@/domains/usuario/UsuarioService";
export default {
  data() {
    return {
      msg: "",
      msgErr: "",
      list: []
    };
  },

  methods: {

    remove(usuario) {
      if (confirm(`Deseja remover o usuario ${usuario.nome}?`)) {
        this.service.remover(usuario.id).then(
          () => {
            let index = this.list.indexOf(usuario);
            this.list.splice(index, 1);
            this.msg = "Usuario removido com sucesso!";
          },
          err => {
            this.msgErr = err.message;
          }
        );
      }
    },

    cleanMsg() {
      this.msg = "";
      this.msgErr = "";
    }
  },

  created() {
    this.service = new UsuarioService(this.$resource);

    this.service
      .listar()
      .then(
        usuarioList => (this.list = usuarioList),
        err => (this.msgErr = err.message)
      );

    if (this.$route.params.msg) {
      this.msg = this.$route.params.msg;
    }
  
  }
};
</script>

<style>
</style>
