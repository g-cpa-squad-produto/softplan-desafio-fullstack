import Vue from 'vue'
import App from './App.vue'
import router from './router';
import VueSweetalert2 from 'vue-sweetalert2';
import axios from 'axios'

Vue.config.productionTip = false

Vue.use(VueSweetalert2, axios)

new Vue({
  router,
  render: h => h(App)
}).$mount('#app')