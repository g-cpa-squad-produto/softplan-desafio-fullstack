import Vue from 'vue'
import axios from 'axios'
import VueAxios from 'vue-axios'

const apiService = {
  init () {
    Vue.use(VueAxios, axios)
    Vue.axios.defaults.baseURL = process.env.URL_API
  },

  setHeader () {
    // Vue.axios.defaults.headers.common['Authorization'] = `Token ${TokenService.getToken()}`
    Vue.axios.defaults.headers.common['Content-Type'] = 'application/json'
    Vue.axios.defaults.headers.common['Access-Control-Allow-Origin'] = '*'
  },

  query (resource, params) {
    return Vue.axios
      .get(resource, params)
      .catch((error) => {
        throw new Error(`[RWV] apiService ${error}`)
      })
  },

  get (resource) {
    return Vue.axios
      .get(`${resource}`, this.setHeader())
      .catch((error) => {
        throw new Error(`[RWV] apiService ${error}`)
      })
  },

  post (resource, params) {
    return Vue.axios.post(`${resource}`, params, {
      headers: {
        'Content-Type': 'application/json',
        'Access-Control-Allow-Origin': '*'
      }
    })
  },

  update (resource, slug, params) {
    return Vue.axios.put(`${resource}/${slug}`, params)
  },

  put (resource, params) {
    return Vue.axios
      .put(`${resource}`, params)
  },

  delete (resource) {
    return Vue.axios
      .delete(resource)
      .catch((error) => {
        throw new Error(`[RWV] apiService ${error}`)
      })
  }
}

export default apiService

export const userService = {
  listAll () {
    return apiService.get('/user/findAll')
  },
  findByRole (role) {
    return apiService.get(`/user/findByRole/${role}`)
  },
  save (user) {
    return apiService.post('/user/save', user)
  },
  authenticate (user) {
    return apiService.post('/user/authenticate', user)
  },
  remove (user) {
    return apiService.delete(`/user/delete/${user.id}`)
  }
}

export const processService = {
  listAll () {
    return apiService.get('/judicialProcess/findAll')
  },
  save (process) {
    return apiService.post('/judicialProcess/save', process)
  },
  remove (process) {
    return apiService.delete(`/judicialProcess/delete/${process.id}`)
  }
}
