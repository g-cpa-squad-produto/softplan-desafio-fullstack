<template>
    <div>
        <v-layout class="mb-3">
            <v-flex xs10>
                <page-title titulo="Novo Processo" rota-voltar="processos"
                            subtitulo="Informe os dados abaixo para criar um novo processo"/>
            </v-flex>
        </v-layout>
        <processo-form v-model="novoProcesso" @salvar="salvarProcesso"/>
    </div>
</template>

<script>
    import {actionTypes} from '@/commons/constants'
    import PageTitle from '@/views/components/PageTitle'
    import ProcessoForm from './ProcessoForm'

    export default {
        name: 'ProcessoNovo',
        components: {ProcessoForm, PageTitle},
        data() {
            return {
                novoProcesso: {}
            }
        },
        methods: {
            async salvarProcesso() {
                try {
                    const processoInserido = await this.inserirProcesso()
                    await this.solicitarParecerUsuarios(processoInserido)
                    this.$router.push({name: 'processos'})
                    this.mostrarAlertSucessoDefault()
                } catch (e) {
                    this.mostrarAlertExcecao(e)
                }
            },
            async inserirProcesso() {
                this.novoProcesso.usuarioCriacao = this.$store.state.usuario
                return await this.$store.dispatch(actionTypes.INSERIR_PROCESSO, this.novoProcesso)
            },
            async solicitarParecerUsuarios(processo) {
                await this.$store.dispatch(actionTypes.SOLICITAR_PARECER, {
                    processoId: processo.id,
                    usuariosParecer: this.novoProcesso.usuariosParecer
                })
            }
        }
    }
</script>