<template>
  <div>
    <h1 v-if="hasprocessData">Edição: {{processData.name}}</h1>
    <h1 v-else>Cadastro de Processo</h1>
    <form id="processForm" @submit.prevent="submit">
      <input type="hidden" id="id" :value="processData.id">
      <div class="form-group">
        <label for="code">Código: </label>
        <input type="code" class="form-control" id="code" aria-describedby="codeHelp" placeholder="Código..." v-model="processData.code">
      </div>
       <div class="form-group">
        <label for="name">Nome: </label>
        <input type="text" class="form-control" id="name" aria-describedby="nameHelp" placeholder="Nome..." v-model="processData.name">
      </div>
      <div class="form-group">
        <label for="seem">Parecer: </label>
        <textarea type="seem" class="form-control" id="seem" placeholder="Parecer..." v-model="processData.seem"/>
      </div>
       <div class="form-group">
        <label for="exampleFormControlRole1">Usuário para Parecer: </label>
        <v-select id="users" multiple v-model="selectedUsers" :options="users"></v-select>
      </div>
      <button type="submit" class="btn btn-primary">Salvar</button>
      <button @click="onCancel()" class="btn btn-primary">Cancelar</button>
    </form>
  </div>
</template>

<script>
  import {AUTH_REQUEST} from 'actions/auth'
  import config from '../../config'
  import axios from 'axios'

  const URL = process.env.NODE_ENV === 'production' ? config.build.url : config.dev.url
  
  const getUsers = function(users) {

    const data = [];
    if (users) {
      users.forEach(element => {
        data.push({
          label: element.name,
          value: element.id
        })
      });
    }

    return data;
  };

  export default {
    name: 'process',
    data () {
      return {
        selectedUsers: null,
        users: [],
        processData: {},
        hasprocessData: false
      }
    },
    methods: {
      async getUsers() {
        await axios.get(`${URL}api/users`)
          .then(res => {

            this.users = getUsers(res.data);
            
            const processId = this.$route.params.id;
            if ($.isNumeric(processId)) {
              this.getprocessData();
            }

          })
          .catch(err => {
            console.log(err)
          })
      },
      async getprocessData() {
        const processId = this.$route.params.id;
        if ($.isNumeric(processId)) {
          await axios.get(`${URL}api/process/${processId}`)
          .then(res => {
            this.processData = res.data.process;
            this.selectedUsers = getUsers(res.data.users);

            if (res.data) {
              this.hasprocessData = true;
            }
            
          })
          .catch(err => {
            console.log(err)
          })
        }
      },
      async submit() {
        
        var process = {
          id: document.getElementById('id').value ? Number(document.getElementById('id').value) : null,
          code: document.getElementById('code').value,
          name: document.getElementById('name').value,
          seem: document.getElementById('seem').value,
        }

        const users = [];
        if (this.selectedUsers) {

          for (let i = 0; i < this.selectedUsers.length; i++) {
            users.push({
              id: this.selectedUsers[i].value
            });
          }

        }

        const processDTO = {
          process: process,
          users: users
        };

        await axios.post(`${URL}api/process`, processDTO)
          .then(res => {
            this.$router.push({name: 'ProcessList'});
          })
          .catch(err => {
            console.log(err);
          })
        
      },
      onCancel() {
        this.$router.push({name: 'ProcessList'});
      }
    },
    async created() {
      await this.getUsers();
    }
  }
</script>

<style>
  label {
    float: left;
  }
  #users {
    display: inline-block;
    width: 100%;
  }
</style>
