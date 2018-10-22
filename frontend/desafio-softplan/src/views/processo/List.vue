<template>

<div class="card">
  <div class="card-header">
    <h6>Lista de Processos</h6>
  </div>
  <div class="card-body">
    <!-- Alertas -->
    <div class="alert alert-success" role="alert" v-show="msg">{{msg}}</div>
    <div class="alert alert-danger" role="alert"  v-show="msgErr">{{msgErr}}</div>

    <table class="table">
      <thead>
        <tr>
          <th scope="col">#</th>
          <th scope="col">Número</th>
          <th scope="col">Descrição</th>
          <th scope="col">Status do Parecer</th>
          <th scope="col"></th>
        </tr>
      </thead>
      <tbody>
        <tr  v-for="(processo, index) of list" :key="processo.id">
          <th scope="row">{{index + 1}}</th>
          <td>{{processo.numero}}</td>
          <td>{{processo.descricao.substring(0,70) + (processo.descricao.length > 70 ? '...':'')}}</td>
          <td>{{getParecerStatus(processo.statusParecer)}}</td>
          <td align="right">
            <router-link :to="{name : 'ProcessoForm', params: {processoId: processo.id}}" title="Editar">
              <font-awesome-icon icon="edit" style="margin-right:5px; color:black;"/>
            </router-link>
            <font-awesome-icon icon="trash" @click="remove(processo)" title="Remover"/>
          </td>
        </tr>
      </tbody>
    </table>
    <router-link :to="{name : 'ProcessoForm'}">
      <button type="submit" class="btn btn-primary">Novo</button>
    </router-link>
  </div>
</div>     
</template>

<script>
import ProcessoService from "@/domains/processo/ProcessoService";
export default {
  data() {
    return {
      msg: "",
      msgErr: "",
      list: []
    };
  },

  methods: {
    
    remove(processo) {
      if (confirm(`Deseja remover: ${processo.numero}?`)) {
        this.service.remover(processo.id).then(
          () => {
            let index = this.list.indexOf(processo);
            this.list.splice(index, 1);
            this.msg = "Removido com sucesso!";
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
    },

    getParecerStatus(status){
      let tipo = "";
      if(status === 0){
        tipo = "Pendente";
      }else if(status === 1){
        tipo = "Concluido";
      }
      return tipo;
    }
  },

  created() {
    this.service = new ProcessoService(this.$resource);

    this.service
      .listar()
      .then(
        processoList => (this.list = processoList),
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
