<template>
    <div>
        <v-layout class="mb-3">
            <v-flex xs10>
                <page-title titulo="Processo" rota-voltar="processos"
                            subtitulo="Detalhes do processo"/>
            </v-flex>
        </v-layout>
        <processo-form v-model="processo" :editando="false"/>
        <processo-detalhe-parecer :pareceres="pareceres" class="mt-5" v-if="ehUsuarioTriador"/>
    </div>
</template>

<script>
    import {mapGetters} from 'vuex'
    import {actionTypes} from '@/commons/constants'
    import PageTitle from '@/views/components/PageTitle'
    import ProcessoForm from './ProcessoForm'
    import ProcessoDetalheParecer from './ProcessoDetalheParecer'

    export default {
        name: 'ProcessoDetalhe',
        components: {ProcessoDetalheParecer, ProcessoForm, PageTitle},
        data() {
            return {
                processo: {},
                pareceres: []
            }
        },
        computed: {
            ...mapGetters(['ehUsuarioTriador'])
        },
        async mounted() {
            await this.buscarProcesso(this.$route.params.processoId)
            await this.buscarPareceresProcesso(this.$route.params.processoId)
        },
        methods: {
            async buscarProcesso(processoId) {
                this.processo = await this.$store.dispatch(actionTypes.BUSCAR_PROCESSO, processoId)
            },
            async buscarPareceresProcesso(processoId) {
                this.pareceres = await this.$store.dispatch(actionTypes.BUSCAR_PARECERES_PROCESSO, processoId)
            }
        }
    }
</script>
