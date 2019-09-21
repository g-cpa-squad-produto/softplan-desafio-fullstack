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
            let message = exceptions[e.message]
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