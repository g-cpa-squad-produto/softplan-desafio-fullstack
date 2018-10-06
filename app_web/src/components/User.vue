<template>
  <div class="jumbotron">
    <div class="container">
        <div class="row">
          <h1 v-if="hasUserData">Edição: {{userData.name}}</h1>
          <h1 v-else>Cadastro de Usuário</h1>
          <form id="userForm" @submit.prevent="submit">
            <input type="hidden" id="id" v-model="userData.id">
            <div class="form-group">
              <label for="name">Nome: </label>
              <input v-validate="'required'" type="text" class="form-control" name="name" aria-describedby="nameHelp" placeholder="Nome..." v-model="userData.name">
              <div v-if="submitted && errors.has('name')" class="invalid-feedback">{{ errors.first('name') }}</div>
            </div>
            <div class="form-group">
              <label for="email">Email: </label>
              <input type="text" v-validate="'required'" class="form-control" name="email" aria-describedby="emailHelp" placeholder="Email..." v-model="userData.email">
              <div v-if="submitted && errors.has('email')" class="invalid-feedback">{{ errors.first('email') }}</div>
            </div>
            <div class="form-group">
              <label for="password">Senha: </label>
              <input type="password" v-validate="'required'" class="form-control" name="password" placeholder="Senha..." v-model="userData.password">
              <div v-if="submitted && errors.has('password')" class="invalid-feedback">{{ errors.first('password') }}</div>
            </div>
            <div class="form-group">
              <label for="selectedRole">Cargo: </label>
              <v-select v-validate="'required'" id="roles" name="role" v-model="selectedRole" :options="roles"></v-select>
              <div v-if="submitted && errors.has('role')" class="invalid-feedback">{{ errors.first('role') }}</div>
            </div>
            <button type="submit" class="btn btn-primary">Salvar</button>
            <button @click="onCancel()" class="btn btn-primary">Cancelar</button>
          </form>
        </div>
    </div>
  </div>
</template>

<script>
  import config from '../../config'
  import axios from 'axios'

  const URL = process.env.NODE_ENV === 'production' ? config.build.url : config.dev.url

  const getRolesData = (roles) => {
    const data = []
    if (roles) {
      roles.forEach(element => {
        data.push({
          label: element.name,
          value: element.id
        })
      })
    }
    return data;
  };

  export default {
    name: 'user',
    data () {
      return {
        roles: [],
        selectedRole: null,
        userData: {},
        hasUserData: false,
        isRequired: true,
        submitted: false
      }
    },
    methods: {
      async getRoles () {
        await axios.get(`${URL}api/roles`)
          .then(res => {
            this.roles = getRolesData(res.data)
            const userId = this.$route.params.id
            if ($.isNumeric(userId)) {
              this.getUserData()
            }
          })
          .catch(err => {
            console.log(err)
          })
      },
      async getUserData () {
        
        const userId = this.$route.params.id
        if ($.isNumeric(userId)) {
          
          await axios.get(`${URL}api/users/${userId}`)
          .then(res => {
            this.userData = res.data
            this.selectedRole = getRolesData([res.data.role])
            if (res.data) {
              this.hasUserData = true
            }
          })
          .catch(err => {
            console.log(err)
          })
        }
      },
      async submit () {
        this.submitted = true;
        this.$validator.validate().then(async valid => {
          if (valid) {

            const user = this.userData;

            if (this.selectedRole) {
              for (let i = 0; i < this.selectedRole.length; i++) {
                user['role'] = {
                  id: this.selectedRole[i].value
                }
              }
            }
            
            await axios.post(`${URL}api/users`, user)
            .then(res => {
              this.$router.push({name: 'UserList'})
            })
            .catch(err => {
              console.log(err)
            })

          }
        });
      },
      onCancel () {
        this.$router.push({name: 'UserList'})
      }
    },
    async created () {
      await this.getRoles()
    }
  }
</script>

<style>
  label {
    float: left;
  }
  #roles {
    display: inline-block;
    width: 100%;
    background: white;
  }
</style>
