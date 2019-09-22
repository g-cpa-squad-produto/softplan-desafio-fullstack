import {mutationTypes, exceptions} from '@/commons/constants'

export default {
    methods: {
        mostrarAlertSucessoDefault() {
            this.$store.commit(mutationTypes.SET_ALERT, {
                message: 'Operação realizada com sucesso.',
                type: 'success'
            })
        },
        mostrarAlertExcecao(e) {
            let message = e.response && e.response.data ? exceptions[e.response.data.message] : null
            if (!message) {
                message = exceptions.default
            }
            this.$store.commit(mutationTypes.SET_ALERT, {
                message,
                type: 'error'
            })
        }
    }
}