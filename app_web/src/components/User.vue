<template>
  <div>
    <h1 v-if="hasUserData">Edição: {{userData.name}}</h1>
    <h1 v-else>Cadastro de Usuário</h1>
    <form id="userForm" @submit.prevent="submit">
      <input type="hidden" id="id" :value="userData.id">
      <div class="form-group">
        <label for="name">Nome: </label>
        <input type="text" class="form-control" id="name" aria-describedby="nameHelp" placeholder="Nome..." :value="userData.name">
      </div>
      <div class="form-group">
        <label for="email">Email: </label>
        <input type="email" class="form-control" id="email" aria-describedby="emailHelp" placeholder="Email..." :value="userData.email">
      </div>
      <div class="form-group">
        <label for="password">Senha: </label>
        <input type="password" class="form-control" id="password" placeholder="Senha..." :value="userData.password">
      </div>
       <div class="form-group">
        <label for="exampleFormControlRole1">Cargo: </label>
        <select id="role" class="form-control">
          <option value="" disabled selected>Escolha um cargo</option>
          <option v-for="role in roles" :key="role.id" :value="role.id">{{ role.name }}</option>
        </select>
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

  export default {
    name: 'user',
    data () {
      return {
        roles: [],
        userData: {},
        hasUserData: false
      }
    },
    methods: {
      async getRoles() {
        await axios.get(`${URL}api/roles`)
          .then(res => {
            this.roles = [...res.data]
            
            const userId = this.$route.params.id;
            if ($.isNumeric(userId)) {
              this.getUserData();
            }

          })
          .catch(err => {
            console.log(err)
          })
      },
      async getUserData() {
        const userId = this.$route.params.id;
        if ($.isNumeric(userId)) {
          await axios.get(`${URL}api/users/${userId}`)
          .then(res => {
            this.userData = res.data;

            if (res.data) {
              this.hasUserData = true;
              document.getElementById('role').value = res.data.role.id;
            }
            
          })
          .catch(err => {
            console.log(err)
          })
        }
      },
      async submit() {
        
        var user = {
          id: document.getElementById('id').value ? Number(document.getElementById('id').value) : null,
          name: document.getElementById('name').value,
          email: document.getElementById('email').value,
          password: document.getElementById('password').value,
          role: {
            id: Number(document.getElementById('role').value)
          }
        }

        await axios.post(`${URL}api/users`, user)
          .then(res => {
            this.$router.push({name: 'UserList'});
          })
          .catch(err => {
            console.log(err);
          })
        
      },
      onCancel() {
        this.$router.push({name: 'UserList'});
      }
    },
    async created() {
      await this.getRoles();
    }
  }
</script>

<style>
  label {
    float: left;
  }
</style>
