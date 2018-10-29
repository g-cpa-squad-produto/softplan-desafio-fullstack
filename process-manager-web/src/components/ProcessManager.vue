<template>
  <v-container fluid fill-height>
    <v-layout row>
      <v-flex xs12>
          <v-layout v-bind="layoutAttributes">
              <v-form ref="form" v-model="valid" lazy-validation>
                <v-text-field
                  v-model="process.processNumber"
                  :rules="nameRules"
                  :counter="50"
                  label="Número"
                  required
                ></v-text-field>
                <v-select
                  v-model="process.user"
                  :items="items"
                  :rules="[v => !!v || 'É necessário selecionar o usuário que irá realizar o parecer']"
                  label="Usuário"
                  required
                ></v-select>
                <v-btn
                  small
                  :disabled="!valid"
                  @click="submit"
                >Salvar
                </v-btn>
                <v-btn small @click="clear">Limpar</v-btn>
                <v-alert
                  v-model="alert"
                  dismissible
                  :type="typeMessage"
                >
                  {{message}}
                </v-alert>
              </v-form>
            </v-layout>
          <br>
          <br>
          <br>
          <div class="table-responsive">
          <table class="table table-striped table-bordered" style="width:100%">
            <thead width="400px">
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">Número<i class="fas fa-sort-alpha-down float-right"></i></th>
                    <th scope="col">Usuário<i class="fas fa-sort-alpha-down float-right"></i></th>
                    <th scope="col">Parecer</th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="(process, index) in listUsers" :key="index">
                  <td>{{index + 1}}</td>
                  <td>{{process.processNumber}}</td>
                  <td>{{process.user.name}}</td>
                  <td>{{process.description}}</td>
                  <td>
                    <v-btn flat icon small
                        @click="edit(process)">
                        <v-icon>edit</v-icon>
                    </v-btn>
                    <v-btn flat icon small
                        @click="remove(process)">
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
import { SAVE_PROCESS, LIST_PROCESS, REMOVE_PROCESS, LIST_USER_ROLE } from '@/store/actions.type'

export default {
  data () {
    return {
      alert: false,
      process: {
        id: null,
        processNumber: '',
        user: null
      },
      valid: true,
      nameRules: [
        v => !!v || 'Numero is required',
        v => (v && v.length <= 50) || 'A quantidade de caracteres máximos para o número é de 50'
      ],
      items: [],
      message: '',
      showAlert: false,
      typeMessage: 'success',
      listUsers: []
    }
  },
  computed: {
    layoutAttributes () {
      return {
        [this.alignment]: 'align-start',
        [this.justify]: false,
        [this.flexDirection]: 'row',
        'fill-height': false
      }
    }
  },
  methods: {
    submit () {
      if (this.$refs.form.validate()) {
        let load = this.$loading.show()
        this.$store.dispatch(SAVE_PROCESS, this.process).then(action => {
          this.process.id = action.id
          this.showAlertMessage('Processo salvo com sucesso!', 'success')
          load.hide()
          this.listGrid()
        }).catch((error) => {
          load.hide()
          this.showAlertMessage(error.toString(), 'error')
          throw new Error(`[RWV] processService ${error}`)
        })
      }
    },
    edit (process) {
      this.process = process
    },
    remove (process) {
      this.$store.dispatch(REMOVE_PROCESS, process).then(action => {
        this.showAlertMessage('Processo removido com sucesso!', 'success')
        this.listGrid()
      }).catch((error) => {
        this.showAlertMessage(error.toString(), 'error')
        throw new Error(`[RWV] processService ${error}`)
      })
    },
    clear () {
      this.$refs.form.reset()
      this.process.id = null
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
      this.$store.dispatch(LIST_PROCESS).then(action => {
        this.listUsers = action
        load.hide()
      }).catch((error) => {
        load.hide()
        this.showAlertMessage(error.toString(), 'error')
        throw new Error(`[RWV] processService ${error}`)
      })
    }
  },
  mounted () {
    this.listGrid()
    var itemsTmp = []
    this.$store.dispatch(LIST_USER_ROLE, 'USER_ACTION').then(action => {
      action.filter(function (value, key) {
        var item = {
          text: value.name,
          value: value
        }
        itemsTmp.push(item)
      })
      this.items = itemsTmp
    }).catch((error) => {
      this.showAlertMessage(error.toString(), 'error')
      throw new Error(`[RWV] apiAuthService ${error}`)
    })
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
