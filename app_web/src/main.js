import Vue from 'vue'
import App from './App'
import router from './router'
import store from './store'
import Loading from 'components/lib/loading'
import CenterContainer from 'components/lib/center-container'
import axios from 'axios'
import VueAxios from 'vue-axios'
import vSelect from 'vue-select'
import VeeValidate from 'vee-validate'

Vue.config.productionTip = false

Vue.use(VueAxios, axios)
Vue.use(VeeValidate, {fieldsBagName: 'formFields'})

Vue.component('v-select', vSelect)
Vue.component('loading', Loading)
Vue.component('center-container', CenterContainer)
/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  store,
  template: '<App/>',
  components: { App },
})
