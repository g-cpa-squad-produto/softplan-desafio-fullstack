<template>
    <div class="container">
        <b-container fluid>
            <NavBar>
                <b-nav-item href="#/processos/add" v-if="auth.roles['Processo.Criar']" >Novo Processo</b-nav-item>
            </NavBar>

            <b-table class="container" striped hover :items="processos" :fields="columnsProcesso" responsive >

                <template v-slot:cell(actions)="row">
                    <b-button @click="visualizarPareceres(row.item)" v-if="auth.roles['Processo.DelegarParecer']" >Pareceres</b-button>
                    <b-button @click="incluirParecer(row.item)" v-if="auth.roles['Processo.IncluirParecer']" >Incluir Parecer</b-button>
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

                <b-alert variant="danger" show v-if="showError" >{{msgError}}</b-alert>

                <b-form-input id="input-numero" placeholder="Busque por nome..." v-model="filtroAtual.nome" v-on:keyup="buscarUsuarios()" ></b-form-input>

                <br/>

                <b-list-group>
                    <b-list-group-item v-for="usuario in usuarios" v-bind:key="usuario.email" v-on:click="selecionarUsario(usuario)" >{{usuario.nome}}</b-list-group-item>
                </b-list-group>

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
                filtroAtual:{
                    nome:''
                },
                showError:false,
                msgError:''
            }
        },
        computed: {
            ...mapState(['processos', 'pareceres', 'usuarios', 'auth'])
        },
        mounted: async function () {
            await this.BUSCAR_PROCESSOS()
        },
        methods: {
            ...mapActions([types.BUSCAR_PROCESSOS, types.BUSCAR_PARECERES_PROCESSO, types.BUSCAR_USUARIOS, types.ADICIONAR_USUARIO_PARECER]),
            visualizarPareceres(processo) {

                this.showPareceres = true
                this.showAdicionarUsuario = false

                this.$refs.parecerModal.show()
                this.processoParecer = processo
                this.BUSCAR_PARECERES_PROCESSO(processo.id)
            },
            iniciarAdicionarUsuario(){

                this.showPareceres = false
                this.showAdicionarUsuario = true
                this.showError = false
                this.msgError = ''
                this.filtroAtual = {
                    nome:'',
                    finalizadores:true
                }
                this.BUSCAR_USUARIOS(this.filtroAtual)
            },
            buscarUsuarios(){

                this.BUSCAR_USUARIOS(this.filtroAtual)
            },
            selecionarUsario(usuario){

                this.showError = false;
                this.msgError = ''

                let dataSend = {
                    processo: JSON.parse(JSON.stringify(this.processoParecer)),
                    usuario: JSON.parse(JSON.stringify(usuario))
                }

                this.ADICIONAR_USUARIO_PARECER(dataSend)
                    .then(() => {
                        this.visualizarPareceres(this.processoParecer)
                    })
                    .catch((err) => {
                        console.log(err)
                        this.showError = true;
                        this.msgError = err.response && err.response.data.message ? err.response.data.message : 'Erro ao realizar ação';
                    })
            },
            incluirParecer(processo){

                this.$router.push('/processos/'+ processo.id +'/pareceres/atual')
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

    .list-group .list-group-item:hover {
        background-color: #3CB371;
        cursor: pointer;
    }

</style>