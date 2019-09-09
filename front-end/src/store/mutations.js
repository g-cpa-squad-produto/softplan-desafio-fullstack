import * as types from './mutation-types'

const mutations = {
  [types.LOGIN] (state) {
    state.auth.pending = true
  },
  [types.LOGIN_SUCCESS] (state, data) {

    const token = data.token
    const username = data.username
    const roles = {}

    data.roles.forEach((role) => {
      roles[role] = true
    })

    state.auth.token = token
    state.auth.username = username
    state.auth.roles = roles
  },
  [types.LOGOUT] (state) {
    localStorage.removeItem('JWT')
    localStorage.removeItem('username')
    state.auth.isLoggedIn = false
  },

  [types.BUSCAR_PAPEIS] (state, papeis) {
    state.papeis = papeis
  }
  ,
  [types.BUSCAR_USUARIOS] (state, usuarios) {
    state.usuarios = usuarios
  },
  [types.SALVAR_USUARIO] (state, usuario) {
    state.usuario = usuario
  },
  [types.BUSCAR_USUARIO] (state, usuario) {
    state.usuario = usuario
  },
  [types.BUSCAR_PROCESSOS] (state, processos) {
    state.processos = processos
  },
  [types.SALVAR_PROCESSO] (state, processo) {
    state.processo = processo
  },
  [types.BUSCAR_PARECERES_PROCESSO] (state, pareceres) {
    state.pareceres = pareceres
  },
  [types.BUSCAR_PARECER_PROCESSO] (state, parecer) {
    state.parecer = parecer
  },
  [types.SALVAR_PARECERE_PROCESSO] (state, parecer) {
    state.parecer = parecer
  }
}

export default mutations

