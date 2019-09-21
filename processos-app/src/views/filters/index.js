import Vue from 'vue'

import date from './date'
import titleCase from './title-case'

Vue.filter('date', date)
Vue.filter('titleCase', titleCase)

export {
    date,
    titleCase
}