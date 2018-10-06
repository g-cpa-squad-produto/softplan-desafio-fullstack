import { USER_REQUEST, USER_ERROR, USER_SUCCESS } from '../actions/user'
import Vue from 'vue'
import { AUTH_LOGOUT } from '../actions/auth'

const state = {
  status: '',
  profile: {},
  isAdmin: false,
  isTriador: false,
  isFinalizador: false,
}

const getters = {
  getProfile: state => state.profile,
  isProfileLoaded: state => !!state.profile.name,
  isAdmin: state => state.isAdmin,
  isTriador: state => state.isTriador,
  isFinalizador: state => state.isFinalizador,
}

const actions = {
  [USER_REQUEST]: ({commit, dispatch}, userData) => {
    if (userData) {
      localStorage.setItem('userData', JSON.stringify(userData))
      commit(USER_SUCCESS, userData)
    } else {
      localStorage.removeItem('userData')
      commit(USER_ERROR)
      dispatch(AUTH_LOGOUT)
    }
  }
}

const mutations = {
  [USER_REQUEST]: (state) => {
    state.status = 'loading'
  },
  [USER_SUCCESS]: (state, resp) => {
    state.status = 'success'
    state.isAdmin = resp.role.code === 'administrador'
    state.isTriador = resp.role.code === 'triador'
    state.isFinalizador = resp.role.code === 'finalizador'
    Vue.set(state, 'profile', resp)
  },
  [USER_ERROR]: (state) => {
    state.status = 'error'
  },
  [AUTH_LOGOUT]: (state) => {
    state.profile = {}
    localStorage.removeItem('userData')
  }
}

export default {
  state,
  getters,
  actions,
  mutations,
}
