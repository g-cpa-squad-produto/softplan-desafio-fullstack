// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import { routes } from '@/router/index'
import VueRouter from 'vue-router'
import apiService from '@/api/api-service'
import store from '@/store'
import Vuetify from 'vuetify'
import VueI18n from 'vue-i18n'
import { messages } from '@/locale/messages'
import VeeValidate from 'vee-validate'
import Loading from 'vue-loading-overlay'

Vue.config.productionTip = false
Vue.use(VueI18n)
Vue.use(Vuetify)
Vue.use(VeeValidate)
Vue.use(Loading)

const router = new VueRouter({
  routes,
  mode: 'history',
  history: true
})

const i18n = new VueI18n({
  locale: 'pt',
  messages
})

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  store,
  i18n,
  components: { App },
  template: '<App/>'
})

apiService.init()
