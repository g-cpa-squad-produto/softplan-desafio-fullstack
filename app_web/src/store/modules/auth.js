/* eslint-disable promise/param-names */
import { AUTH_REQUEST, AUTH_ERROR, AUTH_SUCCESS, AUTH_LOGOUT } from '../actions/auth'
import { USER_REQUEST } from '../actions/user'
import config from '../../../config'
import axios from 'axios'
import uuidv4 from 'uuid/v4'
import Swal from 'sweetalert2'

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

          if (resp.data.status === 200) {

            if (!localStorage.getItem('userData')) {

              Swal({
                type: 'success',
                title: resp.data.message,
                showConfirmButton: false,
                timer: 1500
              })

            }

            const userToken = uuidv4()
            resp.data.user['token'] = userToken

            localStorage.setItem('user-token', userToken)
            commit(AUTH_SUCCESS, resp.data.user)
            dispatch(USER_REQUEST, resp.data.user)
            resolve(resp)

          } else if (resp.data.status === 500) {

            Swal({
              type: 'error',
              title: 'Oops...',
              text: resp.data.message
            })

            commit(AUTH_ERROR, resp.data.message)
            localStorage.removeItem('user-token')
            reject(resp.data.message)

          }
        })
        .catch(err => {
          Swal({
            type: 'error',
            title: 'Oops...',
            text: 'Algo deu errado. Tente novamente mais tarde!'
          })
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
