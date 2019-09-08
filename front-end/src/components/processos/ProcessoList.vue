<template>
    <div class="container">
        <b-container fluid>
            <NavBar>
                <b-nav-item href="#/processos/add">Novo Processo</b-nav-item>
            </NavBar>

            <b-table class="container" striped hover :items="processos" :fields="columnsProcesso" responsive >

                <template v-slot:cell(actions)="row">
                    <b-button @click="visualizarPareceres(row.item)" >Ver Pareceres</b-button>
<!--                    <b-button >Incluir Parecer</b-button>-->
                </template>

            </b-table>

        </b-container>

        <!-- Modal Parecer -->
        <b-modal ref="parecerModal" hide-footer title="Pareceres">

            <div v-if="showPareceres">
                <b-button  @click="iniciarAdicionarUsuario()" >Novo Usuário</b-button>

                <br/>

                <b-table class="container" striped hover :items="pareceres" :fields="columnsParecer" responsive te>
                </b-table>
            </div>

            <div v-if="showAdicionarUsuario">
                Adicionar Usuário
            </div>

        </b-modal>

    </div>
</template>

<script>

    import {mapActions, mapState} from 'vuex'
    import * as types from '../../store/mutation-types'
    import NavBar from "../layout/NavBar";

    export default {
        name: "ProcessoList",
        components: {NavBar},
        data() {
            return {
                processoParecer:{},
                columnsProcesso: [
                    {
                        key: 'numero',
                        label: 'Número'
                    },
                    {
                        key: 'descricao',
                        label: 'Descrição'
                    },
                    {
                        key: 'actions',
                        label: 'Ações'
                    }
                ],
                columnsParecer:[
                    {
                        key: 'usuario.nome',
                        label: 'Usuário'
                    },
                    {
                        key: 'parecer'
                    }
                ],
                showPareceres:false,
                showAdicionarUsuario:false,
            }
        },
        computed: {
            ...mapState(['processos', 'pareceres'])
        },
        mounted: async function () {
            await this.BUSCAR_PROCESSOS()
        },
        methods: {
            ...mapActions([types.BUSCAR_PROCESSOS, types.BUSCAR_PARECERES_PROCESSO, types.BUS]),
            visualizarPareceres(processo) {

                this.showPareceres = true
                this.showAdicionarUsuario = false

                this.$refs.parecerModal.show();
                this.processoParecer = processo;
                this.BUSCAR_PARECERES_PROCESSO(processo.id)
            },
            iniciarAdicionarUsuario(){

                this.showPareceres = false
                this.showAdicionarUsuario = true
            }
        }
    }

</script>

<style>
    .container {
        width: 100%;
    }

    th {
        text-align: center;
    }

</style>