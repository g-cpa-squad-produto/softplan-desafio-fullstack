<template>
    <div>
        <v-layout class="mb-3">
            <v-flex xs10>
                <page-title titulo="Processos" subtitulo="Listagem dos processos" rota-voltar="login"/>
            </v-flex>
            <v-flex xs2 class="text-xs-right">
                <v-btn color="green" class="white--text" @click="irParaNovoProcesso">
                    Novo
                </v-btn>
            </v-flex>
        </v-layout>
        <processo-busca-tabela :processos="processos" v-if="existeProcessos" @ver-detalhes="verDetalhes"/>
        <empty-search-results v-else/>
    </div>
</template>

<script>
    import {actionTypes} from '@/commons/constants'
    import PageTitle from '@/views/components/PageTitle'
    import ProcessoBuscaTabela from './ProcessoBuscaTabela'
    import EmptySearchResults from '@/views/components/EmptySearchResults'

    export default {
        name: 'ProcessoBusca',
        components: {PageTitle, EmptySearchResults, ProcessoBuscaTabela},
        data() {
            return {
                processos: []
            }
        },
        computed: {
            existeProcessos() {
                return this.processos.length
            }
        },
        async mounted() {
            await this.buscarProcessos()
        },
        methods: {
            async buscarProcessos() {
                this.processos = await this.$store.dispatch(actionTypes.BUSCAR_PROCESSOS)
            },
            irParaNovoProcesso() {
                this.$router.push({name: 'processoNovo'})
            },
            verDetalhes(processoId) {
                this.$router.push({name: 'processoDetalhe', params: {processoId}})
            }
        }
    }
</script>