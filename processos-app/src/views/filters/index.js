import Vue from 'vue'
import titleCase from './title-case'

Vue.filter('titleCase', titleCase)

export {
    titleCase
}