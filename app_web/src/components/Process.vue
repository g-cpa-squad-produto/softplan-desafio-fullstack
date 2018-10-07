<template>
  <div class="jumbotron">
    <div class="container">
        <div class="row">
            <h1 v-if="hasprocessData">Edição: {{processData.name}}</h1>
            <h1 v-else>Cadastro de Processo</h1>
            <form id="processForm" @submit.prevent="submit">
              <input type="hidden" id="id" :value="processData.id">
              <div class="form-group">
                <label for="code">Código: </label>
                <input :disabled="!isTriador" v-validate="'required'" type="text" name="code" class="form-control" placeholder="Código..." v-model="processData.code">
                <div v-if="submitted && errors.has('code')" class="invalid-feedback">{{ errors.first('code') }}</div>
              </div>
              <div class="form-group">
                <label for="name">Nome: </label>
                <input :disabled="!isTriador" v-validate="'required'" type="text" class="form-control" placeholder="Nome..." name="name" v-model="processData.name">
                <div v-if="submitted && errors.has('name')" class="invalid-feedback">{{ errors.first('name') }}</div>
              </div>
              <div class="form-group">
                <label for="seem">Parecer: </label>
                <textarea :disabled="!isFinalizador" class="form-control" name="seem" placeholder="Parecer..." v-model="processData.seem"/>
                <div v-if="submitted && errors.has('seem')" class="invalid-feedback">{{ errors.first('seem') }}</div>
              </div>
              <div class="form-group">
                <label for="selectedUsers">Usuário para Parecer: </label>
                <v-select 
                  v-model="selectedUsers" 
                  v-validate="'required'" 
                  :disabled="!isTriador" 
                  id="users" 
                  multiple 
                  :options="users" 
                  name="selectedUsers"
                >
                </v-select>
                <div v-if="submitted && errors.has('selectedUsers')" class="invalid-feedback">{{ errors.first('selectedUsers') }}</div>
              </div>
              <button type="submit" class="btn btn-primary">Salvar</button>
              <button type="button" @click="onCancel()" class="btn btn-primary">Cancelar</button>
            </form>
        </div>
    </div>
  </div>
</template>

<script>
  import { mapGetters } from 'vuex'
  import config from '../../config'
  import axios from 'axios'
  import Swal from 'sweetalert2'

  const URL = process.env.NODE_ENV === 'production' ? config.build.url : config.dev.url
  
  const getUsersData = (users) => {
    const data = []
    if (users) {
      users.forEach(element => {
        data.push({
          label: element.name,
          value: element.id
        })
      })
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
        hasprocessData: false,
        submitted: false
      }
    },
    methods: {
      async getUsers () {
        await axios.get(`${URL}api/users?roleCode=finalizador`)
          .then(res => {
            this.users = getUsersData(res.data)
            const processId = this.$route.params.id;
            if ($.isNumeric(processId)) {
              this.getprocessData()
            }
          })
          .catch(err => {
            console.log(err)
          })
      },
      async getprocessData () {
        
        const processId = this.$route.params.id
        if ($.isNumeric(processId)) {

          await axios.get(`${URL}api/process/${processId}`)
          .then(res => {
            this.processData = res.data.process
            this.selectedUsers = getUsersData(res.data.users)
            if (res.data) {
              this.hasprocessData = true
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
            
            const users = [];
            if (this.selectedUsers) {
              for (let i = 0; i < this.selectedUsers.length; i++) {
                users.push({
                  id: this.selectedUsers[i].value
                })
              }
            }
            
            const processDTO = {
              process: this.processData,
              users: users
            }

            await axios.post(`${URL}api/process`, processDTO)
              .then(resp => {

                if (resp.data.status === 200) {

                  Swal({
                    type: 'success',
                    title: resp.data.message,
                    showConfirmButton: false,
                    timer: 1500
                  })

                  this.$router.push({name: 'ProcessList'})

                } else if (resp.data.status === 500) {

                  Swal({
                    type: 'error',
                    title: 'Oops...',
                    text: resp.data.message
                  })

                }

              })
              .catch(err => {
                 Swal({
                  type: 'error',
                  title: 'Oops...',
                  text: 'Algo deu errado. Tente novamente mais tarde!'
                })
              })
          }
        });
      },
      onCancel () {
        this.$router.push({name: 'ProcessList'})
      }
    },
    computed: {
      ...mapGetters(['isFinalizador', 'isTriador']),
    },
    async created () {
      await this.getUsers()
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
    background: white;
  }
</style>
