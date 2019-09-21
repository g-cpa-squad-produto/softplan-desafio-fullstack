import Vue from 'vue'
import store from '@/commons/store'
import router from '@/views/router'
import App from './App'

import '@/commons/vendors/vuetify'

Vue.config.productionTip = false

export default class AppBuilder {

    build() {
        new Vue({
            router,
            store,
            render: h => h(App)
        }).$mount('#app')
    }

}