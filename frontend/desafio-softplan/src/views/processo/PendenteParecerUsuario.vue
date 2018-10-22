<template>

<div class="card">
  <div class="card-header">
    <h6>Lista de Processos Pendentes de Parecer</h6>
  </div>
  <div class="card-body">
    <!-- Alertas -->
    <div class="alert alert-success" role="alert" v-show="msg">{{msg}}</div>
    <div class="alert alert-danger" role="alert"  v-show="msgErr">{{msgErr}}</div>

    <table class="table" @click="cleanMsg()">
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
            <router-link :to="{name : 'ParecerForm', params: {processoId: processo.id}}">
              <button type="button" class="btn btn-warning" title="Escrever Parecer">Parecer</button>
            </router-link>  
          </td>
        </tr>
      </tbody>
    </table>
    <div v-show="list.length == 0" class="alert alert-warning" role="alert">
      Não existe nenhum processo com parecer pendente!
    </div>
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
      .listarPendentesParecerUsuario()
      .then(
        processoPendentesList => (this.list = processoPendentesList),
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
