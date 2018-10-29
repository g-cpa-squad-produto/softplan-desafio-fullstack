<template>
   <v-container fluid fill-height>
      <v-layout row wrap>
         <v-form ref="form" v-model="valid" lazy-validation>
            <v-layout row wrap>
               <v-flex xs6 wrap>
                  <v-text-field
                     v-model="user.login"
                     :rules="[v => !!v || 'O login do usuário é obrigatório']"
                     label="Login"
                     required />
               </v-flex>
               <v-flex xs6 style="padding-left: 15px;">
                  <v-text-field
                     v-model="user.password"
                     :rules="[v => !!v || 'O password do usuário é obrigatório']"
                     label="Senha"
                     required/>
               </v-flex>
               <v-flex xs6>
                  <v-text-field
                     v-model="user.name"
                     :rules="nameRules"
                     :counter="20"
                     label="Nome"
                     required
                     />
               </v-flex>
               <v-flex xs6 style="padding-left: 15px;">
                  <v-select
                     v-model="user.role"
                     :items="items"
                     :rules="[v => !!v || 'É necessário selecionar a função do usuário']"
                     label="Função"
                     required />
               </v-flex>
               <v-checkbox
                  v-model="user.status"
                  label="Ativo"
                  required
                  ></v-checkbox>
               <v-btn
                  small
                  :disabled="!valid"
                  @click="submit"
                  >Salvar
               </v-btn>
               <v-btn small @click="clear">Limpar</v-btn>
            </v-layout>
            <v-alert
                  v-model="alert"
                  dismissible
                  :type="typeMessage"
                  >
                  {{message}}
               </v-alert>
         </v-form>
         <v-flex xs12>
            <div class="table-responsive">
               <table class="table table-striped table-bordered" style="width:100%">
                  <thead width="400px">
                     <tr>
                        <th scope="col">#</th>
                        <th scope="col">Nome <i class="fas fa-sort-alpha-down float-right"></i></th>
                        <th scope="col">Função<i class="fas fa-sort-alpha-down float-right"></i></th>
                        <th scope="col">Status</th>
                        <th scope="col">Login</th>
                        <th scope="col">Senha</th>
                        <th scope="col">Ações</th>
                     </tr>
                  </thead>
                  <tbody>
                     <tr v-for="(user, index) in listUsers" :key="index">
                        <td>{{index + 1}}</td>
                        <td>{{user.name}}</td>
                        <td>{{user.role}}</td>
                        <td>{{user.status ? 'Ativo' : 'Inativo'}}</td>
                        <td>{{user.login}}</td>
                        <td>{{user.password}}</td>
                        <td>
                           <v-btn flat icon small
                              @click="edit(user)">
                              <v-icon>edit</v-icon>
                           </v-btn>
                           <v-btn flat icon small
                              @click="remove(user)">
                              <v-icon>delete</v-icon>
                           </v-btn>
                        </td>
                     </tr>
                  </tbody>
               </table>
            </div>
         </v-flex>
      </v-layout>
   </v-container>
</template>

<script>
import { SAVE_USER, LIST_USER, REMOVE_USER } from '@/store/actions.type'

export default {
  data () {
    return {
      alert: false,
      user: {
        id: null,
        name: '',
        status: true,
        role: null,
        login: '',
        password: ''
      },
      valid: true,
      nameRules: [
        v => !!v || 'Name is required',
        v => (v && v.length <= 20) || 'A quantidade de caracteres máximos para o nome é de 20'
      ],
      items: [
        'ADMIN',
        'USER_MANAGER',
        'USER_ACTION'
      ],
      message: '',
      showAlert: false,
      typeMessage: 'success',
      listUsers: []
    }
  },
  methods: {
    submit () {
      if (this.$refs.form.validate()) {
        let load = this.$loading.show()
        this.$store.dispatch(SAVE_USER, this.user).then(action => {
          this.user.id = action.id
          this.showAlertMessage('Usuário salvo com sucesso!', 'success')
          load.hide()
          this.listGrid()
        }).catch((error) => {
          load.hide()
          this.showAlertMessage(error.toString(), 'error')
          throw new Error(`[RWV] apiAuthService ${error}`)
        })
      }
    },
    edit (user) {
      this.user = user
    },
    remove (user) {
      this.$store.dispatch(REMOVE_USER, user).then(action => {
        this.showAlertMessage('Usuário removido com sucesso!', 'success')
        this.listGrid()
      }).catch((error) => {
        this.showAlertMessage(error.toString(), 'error')
        throw new Error(`[RWV] userService ${error}`)
      })
    },
    clear () {
      this.$refs.form.reset()
      this.user.id = null
    },
    showAlertMessage (message, typeMessage) {
      this.alert = true
      this.message = message
      this.typeMessage = typeMessage
    },
    hideAlert () {
      this.alert = false
      this.message = ''
      this.typeMessage = ''
    },
    listGrid () {
      let load = this.$loading.show()
      this.$store.dispatch(LIST_USER).then(action => {
        this.listUsers = action
        load.hide()
      }).catch((error) => {
        load.hide()
        this.showAlertMessage(error.toString(), 'error')
        throw new Error(`[RWV] userService ${error}`)
      })
    }
  },
  mounted () {
    this.listGrid()
  }
}
</script>

<style scoped>
  .resultContainer {
    height: 350px;
  }

  .item {
    min-height: 50px;
    min-width: 80px;
    margin: 10px;
  }

  td {
    text-align: center
  }
</style>

<!--alignmentsAvailable: ['align-center', 'align-end', 'align-space-around', 'align-space-between', 'align-start', ''],
alignmentsContentAvailable: ['align-content-center', 'align-content-end', 'align-content-space-around', 'align-content-space-between', 'align-content-start', ''],
justifyAvailable: ['justify-center', 'justify-end', 'justify-space-around', 'justify-space-between', 'justify-start', ''],
flexDirectionAvailable: ['row', 'column', ''],
-->
