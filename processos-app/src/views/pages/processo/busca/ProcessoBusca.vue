<template>
    <div>
        <v-layout class="mb-3">
            <v-flex xs10>
                <page-title titulo="Processos" subtitulo="Listagem dos processos"/>
            </v-flex>
            <v-flex xs2 class="text-xs-right">
                <v-btn color="green" class="white--text" @click="irParaNovoProcesso">
                    Novo
                </v-btn>
            </v-flex>
        </v-layout>
        <processo-busca-tabela :processos="processos" v-if="existeProcessos"/>
        <processo-busca-vazia v-else/>
    </div>
</template>

<script>
    import {actionTypes} from '@/commons/constants'
    import PageTitle from '@/views/components/PageTitle'
    import ProcessoBuscaTabela from './ProcessoBuscaTabela'
    import ProcessoBuscaVazia from './ProcessoBuscaVazia'

    export default {
        name: 'ProcessoBusca',
        components: {PageTitle, ProcessoBuscaVazia, ProcessoBuscaTabela},
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
            }
        }
    }
</script>