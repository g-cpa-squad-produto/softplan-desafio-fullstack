import Vue from 'vue'
import VeeValidate, {Validator} from 'vee-validate'
import ptBR from 'vee-validate/dist/locale/pt_BR'

Vue.use(VeeValidate)
Validator.localize('pt_BR', ptBR)
