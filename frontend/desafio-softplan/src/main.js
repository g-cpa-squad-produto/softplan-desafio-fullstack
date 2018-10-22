import Vue from 'vue'
import App from './App.vue'
import router from './router'
import VueResource from 'vue-resource'

import 'bootstrap/scss/bootstrap.scss';
import 'bootstrap/dist/js/bootstrap.min.js';

import { library } from '@fortawesome/fontawesome-svg-core'
import { faEdit, faTrash } from '@fortawesome/free-solid-svg-icons'
import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome'

library.add(faEdit)
library.add(faTrash)

Vue.component('font-awesome-icon', FontAwesomeIcon)

Vue.use(VueResource)
Vue.http.options.root = 'http://localhost:8081/desafiosoftplan/'

Vue.config.productionTip = false

Vue.router= router;

Vue.use(require('@websanova/vue-auth'), {
  auth: require('@websanova/vue-auth/drivers/auth/bearer.js'),
  http: require('@websanova/vue-auth/drivers/http/vue-resource.1.x.js'),
  router: require('@websanova/vue-auth/drivers/router/vue-router.2.x.js'),
  rolesVar: 'tipoUsuario',
  loginData: {url: 'login', method: 'POST', redirect: '/', fetchUser: true},
  fetchData: {url: 'login/usuario', method: 'GET'},
  refreshData: {url: 'login/refresh', method: 'GET', atInit: false},
  parseUserData: function (data) {
    return data;
  }
})

new Vue({
  router,
  render: h => h(App)
}).$mount('#app')




