<template>
  <v-app id="inspire">
    <v-content>
      <v-container fluid fill-height>
        <v-layout align-center justify-center>
          <v-flex xs12 sm8 md4>
            <v-card class="elevation-12">
              <v-toolbar dark color="primary">
                <v-toolbar-title>Gerenciador de Processos</v-toolbar-title>
                <v-spacer></v-spacer>
              </v-toolbar>
              <v-card-text>
                <v-form>
                  <v-text-field prepend-icon="person" v-model="login" name="login" label="Login" type="text"></v-text-field>
                  <v-text-field id="password" prepend-icon="lock" v-model="password" name="password" label="Senha" type="password"></v-text-field>
                </v-form>
              </v-card-text>
              <v-alert
                v-model="alert"
                dismissible
                :type="typeMessage"
            >
                {{message}}
            </v-alert>
              <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn color="primary" @click="logon">Login</v-btn>
              </v-card-actions>
            </v-card>
          </v-flex>
        </v-layout>
      </v-container>
    </v-content>
  </v-app>
</template>

<script>
import { AUTHENTICATE } from '@/store/actions.type'
import CustomerService from '@/api/customer-service'

export default {
  data: () => ({
    drawer: null,
    login: '',
    password: '',
    alert: false,
    message: '',
    typeMessage: 'success'
  }),
  props: {
    source: String
  },
  methods: {
    logon () {
      let user = {
        login: this.login,
        password: this.password
      }
      this.$store.dispatch(AUTHENTICATE, user).then(action => {
        if (action != null && action !== '') {
          CustomerService.saveCustomer(JSON.stringify(action))
          this.$router.go(this.$router.replace({ name: 'Workspace' }))
        } else {
          this.alert = true
          this.message = 'Usuário/Senha inválidos'
          this.typeMessage = 'error'
        }
      }).catch((error) => {
        this.showAlertMessage(error.toString(), 'error')
        throw new Error(`[RWV] apiAuthService ${error}`)
      })
    },
    showAlertMessage (message, typeMessage) {
      this.alert = true
      this.message = message
      this.typeMessage = typeMessage
    }
  }
}
</script>
