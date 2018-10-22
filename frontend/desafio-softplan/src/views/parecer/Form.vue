<template>

<div class="card">
  <div class="card-header">
    <h6 class="c-grey-900" v-if="processo.id">Processo</h6>
  </div>
  <div class="card-body">
      <!-- processo -->
      <div class="form-group">
        <label>Número</label>
        <input class="form-control" v-model="processo.numero" disabled/>
      </div>

      <div class="form-group">
        <label>Descrição</label>
        <textarea class="form-control" disabled v-model="processo.descricao"></textarea>
      </div>
  </div>

  <div class="card-header border-top-parecer">
    <h6 class="c-grey-900" v-if="processo.id">Parecer</h6>
  </div>
  <div class="card-body">
    <!-- Alertas -->
    <div class="alert alert-success" role="alert" v-show="msg">{{msg}}</div>
    <div class="alert alert-danger" role="alert"  v-show="msgErr">{{msgErr}}</div>

    <form @submit.prevent="submit()">
      <!-- parecer -->
      <div class="form-group">
        <label for="tituloInput">Título</label>
        <input type="text" class="form-control" id="nomeInput" name="nomeInput" @focus="cleanMsg();"
          required placeholder="" autocomplete="false"  v-model="parecer.titulo" maxlength="200">
      </div>

      <div class="form-group">
        <label for="descricaoInput">Parecer</label>
        <textarea class="form-control" id="descricaoInput" name="descricaoInput" @focus="cleanMsg();"
          required v-model="parecer.descricao"></textarea>
      </div>

      <button type="submit" class="btn btn-primary">Salvar</button>
    </form>

  </div>
</div>     
</template>

<script>
import Processo from "@/domains/processo/Processo";
import Parecer from "@/domains/parecer/Parecer";
import ProcessoService from "@/domains/processo/ProcessoService";
import ParecerService from "@/domains/parecer/ParecerService";
export default {
  data() {
    return {
      msg: "",
      msgErr: "",
      processoId: this.$route.params.processoId,
      processo: new Processo(),
      parecer: new Parecer()
    };
  },

  methods: {
    submit() {
      this.parecer.usuario = { id: this.$auth.user().id };
      this.parecer.processo = { id: this.processo.id };
      this.service.salvarParecerProcesso(this.parecer).then(
        () => {
          this.$router.push({
            name: "ProcessoPendentesParecerUsuarioList",
            params: { msg: "Adicionado com sucesso!" }
          });
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
    this.service = new ParecerService(this.$resource);
    this.processoService = new ProcessoService(this.$resource);

    if (!this.processoId) {
      this.$router.push({ name: "ProcessoPendentesParecerUsuarioList" });
    }

    this.processoService.trazer(this.processoId).then(processo => {
      this.processo = processo;
    });
  }
};
</script>

<style>
div.border-top-parecer {
  border-top: 1px solid rgba(0, 0, 0, 0.125);
}
</style>
