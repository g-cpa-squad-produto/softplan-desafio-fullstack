import Vue from 'vue'
import Vuex from 'vuex'
import actions from './actions'
import mutations from './mutations'

Vue.use(Vuex)

const state = {
  auth: {
    token: null,
    username: null,
    roles:{}
  },
  papeis:[],
  usuarios:[],
  usuario:{papel:{}},
  processos:[],
  processo:{},
  pareceres:[],
  parecer:{processo:{}}
}

const options = {
  state,
  mutations,
  actions
}

const store = new Vuex.Store(options)

export default store
