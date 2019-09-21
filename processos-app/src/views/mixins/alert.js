import {mutationTypes} from '@/commons/constants'

export default {
    methods: {
        mostrarAlertSucesso(mensagem) {

        },
        mostrarAlertSucessoDefault() {
            this.$store.commit(mutationTypes.SET_ALERT, {
                message: 'Operação realizada com sucesso.',
                type: 'success'
            })
        },
        mostrarAlertErro(mensagem) {

        },
        mostrarAlertErroDefault() {

        }
    }
}