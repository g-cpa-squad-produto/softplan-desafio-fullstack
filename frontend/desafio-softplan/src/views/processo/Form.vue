<template>

<div class="card">
  <div class="card-header">
    <h6 class="c-grey-900" v-if="processo.id">Altearação de Processo</h6>
    <h6 class="c-grey-900" v-else >Cadastro de Processo</h6>
  </div>
  <div class="card-body">
    <!-- Alertas -->
    <div class="alert alert-success" role="alert" v-show="msg">{{msg}}</div>
    <div class="alert alert-danger" role="alert"  v-show="msgErr">{{msgErr}}</div>

    <form @submit.prevent="submit()">
          
      <div class="form-group">
        <label for="numeroInput">Número</label>
        <input type="text" class="form-control" id="numeroInput" name="numeroInput" @focus="cleanMsg();"
          required placeholder="" autocomplete="false"  v-model="processo.numero" maxlength="10">
      </div>

      <div class="form-group">
        <label for="descricaoInput">Descrição</label>
        <textarea class="form-control" id="descricaoInput" name="descricaoInput" @focus="cleanMsg();"
          required v-model="processo.descricao"></textarea>
      </div>

      <div class="form-group">
        <label for="statusParecerInput">Status do parecer</label>
        <select class="form-control" id="statusParecerInput" name="statusParecerInput" @focus="cleanMsg();"
          required autocomplete="false"  v-model="processo.statusParecer">
          <option value="">Selecione</option>
          <option value="0">Pendente</option>
          <option value="1">Concluido</option>
        </select>
      </div>

      <div class="form-row">
        <div class="form-group col-md-10">
          <label for="statusParecerInput">Usúarios Parecer</label>
          <vue-single-select 
            v-model="usuarioParecerSelected" 
            :options="usuarioFinalizadores"
            option-label="nome"
            placeholder="Pesquisar">
          </vue-single-select>
        </div>
        <div class="form-group col-md-2">
          <label>&nbsp;</label>
          <button type="button" class="btn btn-secondary form-control" @focus="cleanMsg()" @click="adicionarUsuarioParecer(usuarioParecerSelected)">Adicionar</button>
        </div>
        <div class="form-group col-md-12">
          <ul class="list-group" v-if="processo.usuariosPararecer">
            
            <li v-for="usuario in processo.usuariosPararecer" :key="usuario.id" class="list-group-item">
              {{usuario.nome}}
              <font-awesome-icon icon="trash" @click="remove(usuario)" title="Remover" style="float:right"/> 
            </li>
          </ul>
        </div>
      </div>

      <button type="submit" class="btn btn-primary">Salvar</button>
    </form>

  </div>
</div>     
</template>

<script>
import Processo from "@/domains/processo/Processo";
import ProcessoService from "@/domains/processo/ProcessoService";
import UsuarioService from "@/domains/usuario/UsuarioService";
import VueSingleSelect from "vue-single-select";
export default {
  components: {
    VueSingleSelect
  },
  data() {
    return {
      msg: "",
      msgErr: "",
      processoId: this.$route.params.processoId,
      processo: new Processo(),
      usuarioFinalizadores: [],
      usuarioParecerSelected: null
    };
  },

  methods: {
    submit() {
      this.service.salvar(this.processo).then(
        () => {
          if (this.processo.id) {
            this.$router.push({
              name: "ProcessoList",
              params: { msg: "Alterado com sucesso!" }
            });
          } else {
            this.msg = "Adicionado com sucesso!";
          }
          this.processo = new Processo();
          this.processo.statusParecer = "";
          this.usuarioParecerSelected = null;
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
    },

    adicionarUsuarioParecer(usuarioParecer) {
      if (this.processo.usuariosPararecer == null) {
        this.processo.usuariosPararecer = [];
      }
      let index = -1;
      this.processo.usuariosPararecer.forEach((item, i) => {
        if (usuarioParecer.id === item.id) {
          index = i;
        }
      });
      if (index === -1) {
        this.processo.usuariosPararecer.push(usuarioParecer);
      } else {
        this.msgErr = "Usúario já adicionado a lista!";
      }
      this.usuarioParecerSelected = null;
    },

    remove(usuario) {
      this.processo.usuariosPararecer.forEach((item, i) => {
        if (usuario.id === item.id) {
          this.processo.usuariosPararecer.splice(i, 1);
        }
      });
    }
  },

  created() {
    this.service = new ProcessoService(this.$resource);
    this.serviceUsuario = new UsuarioService(this.$resource);

    this.processo.statusParecer = "";

    this.serviceUsuario.listarFinalizadores().then(usuarioFinalizadores => {
      this.usuarioFinalizadores = usuarioFinalizadores;
    });

    if (this.processoId) {
      this.service.trazer(this.processoId).then(processo => {
        this.processo = processo;
      });
    }
  }
};
</script>

<style>
</style>
