<template>
    <div>
        <v-layout class="mb-3">
            <v-flex xs10>
                <page-title
                        titulo="Processo"
                        rota-voltar="processos"
                        subtitulo="Detalhes do processo"/>
            </v-flex>
        </v-layout>
        <processo-form v-model="processo" :editando="false"/>
        <processo-detalhe-parecer
                class="mt-5"
                :pareceres="pareceres"
                v-if="ehUsuarioTriador"/>
        <processo-detalhe-novo-parecer
                class="mt-3"
                @salvar="salvarParecer"
                v-if="ehUsuarioFinalizador"/>
    </div>
</template>

<script>
    import {mapGetters} from 'vuex'
    import {actionTypes} from '@/commons/constants'
    import PageTitle from '@/views/components/PageTitle'
    import ProcessoForm from './ProcessoForm'
    import ProcessoDetalheParecer from './ProcessoDetalheParecer'
    import ProcessoDetalheNovoParecer from './ProcessoDetalheNovoParecer'

    export default {
        name: 'ProcessoDetalhe',
        components: {ProcessoDetalheNovoParecer, ProcessoDetalheParecer, ProcessoForm, PageTitle},
        data() {
            return {
                processo: {},
                pareceres: []
            }
        },
        computed: {
            ...mapGetters(['ehUsuarioTriador', 'ehUsuarioFinalizador'])
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
            },
            async salvarParecer(textoParecer) {
                try {
                    await this.$store.dispatch(actionTypes.REALIZAR_PARECER, {
                        processoId: this.processo.id,
                        usuarioId: this.$store.state.usuario.id,
                        textoParecer
                    })
                    this.mostrarAlertSucessoDefault()
                    this.$router.push({name: 'processosPendentes'})
                } catch(e) {
                    this.mostrarAlertExcecao(e)
                }
            }
        }
    }
</script>
