/* eslint-disable promise/param-names */
import { AUTH_REQUEST, AUTH_ERROR, AUTH_SUCCESS, AUTH_LOGOUT } from '../actions/auth'
import { USER_REQUEST } from '../actions/user'
import config from '../../../config'
import axios from 'axios'
import uuidv4 from 'uuid/v4'

const state = {
  token: localStorage.getItem('user-token') || '',
  status: '',
  hasLoadedOnce: false
}

const getters = {
  isAuthenticated: state => !!state.token,
  authStatus: state => state.status,
}

const URL = process.env.NODE_ENV === 'production' ? config.build.url : config.dev.url

const actions = {
  [AUTH_REQUEST]: ({commit, dispatch}, loginData) => {
    return new Promise((resolve, reject) => {
      commit(AUTH_REQUEST)
      axios.post(`${URL}api/users/oauth`, loginData)
        .then(resp => {
          if (resp.data) {
            const userToken = uuidv4()
            resp.data['token'] = userToken

            localStorage.setItem('user-token', userToken)
            commit(AUTH_SUCCESS, resp.data)
            dispatch(USER_REQUEST, resp.data)
            resolve(resp)
          } else {
            const err = 'User and/or password is invalid!'
            commit(AUTH_ERROR, err)
            localStorage.removeItem('user-token')
            reject(err)
          }
        })
        .catch(err => {
          commit(AUTH_ERROR, err)
          localStorage.removeItem('user-token')
          reject(err)
        })
    })
  },
  [AUTH_LOGOUT]: ({commit, dispatch}) => {
    return new Promise((resolve, reject) => {
      commit(AUTH_LOGOUT)
      localStorage.removeItem('user-token')
      resolve()
    })
  }
}

const mutations = {
  [AUTH_REQUEST]: (state) => {
    state.status = 'loading'
  },
  [AUTH_SUCCESS]: (state, resp) => {
    state.status = 'success'
    state.token = resp.token
    state.hasLoadedOnce = true
  },
  [AUTH_ERROR]: (state) => {
    state.status = 'error'
    state.hasLoadedOnce = true
  },
  [AUTH_LOGOUT]: (state) => {
    state.token = ''
  }
}

export default {
  state,
  getters,
  actions,
  mutations,
}
