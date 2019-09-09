<template>
    <div class="container">
        <b-container fluid>
            <NavBar>
                <b-nav-item href="/usuarios/add" to="/usuarios/add" v-if="auth.roles['Usuario.Criar']" >Novo Usuário</b-nav-item>
            </NavBar>

            <b-table class="container" striped hover :items="usuarios" :fields="columns" responsive >

                <template v-slot:cell(actions)="row">
                    <a :href="'#/usuarios/edit/' + row.item.id" class="btn btn-info btn-sm" style="margin-right: 4px" v-if="auth.roles['Usuario.Editar']" >Editar</a>
                    <b-button @click="excluirUsuario(row.item)" class="btn btn-danger btn-sm" >Excluir</b-button>
                </template>

            </b-table>

            <!-- Modal Parecer -->
            <b-modal ref="confirmarExclusao" hide-footer title="Confirmar Exclusão">

                <p>Deseja realmente excluir o usuário?</p>
                <b-button  class="btn btn-info btn-sm" @click="confirmarExclusao()" >Confirmar</b-button>

            </b-modal>

        </b-container>
    </div>
</template>

<script>

    import {mapActions, mapState} from 'vuex'
    import * as types from '../../store/mutation-types'
    import NavBar from "../layout/NavBar";

    export default {
        name: "UsuarioList",
        components: {NavBar},
        data() {
            return {
                columns: [
                    {
                        key: 'id',
                        label: 'Código'
                    },
                    {
                        key: 'nome'
                    },
                    {
                        key: 'email'
                    },
                    {
                        key: 'papel.descricao',
                        label: 'Papel'

                    },
                    {
                        key: 'actions',
                        label: 'Ações'
                    }
                ],
                usuarioExcluir:null
            }
        },
        computed: {
            ...mapState(['usuarios', 'auth'])
        },
        mounted: async function () {
            await this.BUSCAR_USUARIOS()
        },
        methods: {
            ...mapActions([types.BUSCAR_USUARIOS, types.EXCLUIR_USUARIO]),
            excluirUsuario(usuario){
                this.usuarioExcluir = usuario;
                this.$refs.confirmarExclusao.show()
            },
            confirmarExclusao: async function () {
                await this.EXCLUIR_USUARIO(this.usuarioExcluir.id)
                await this.BUSCAR_USUARIOS()
                this.$refs.confirmarExclusao.hide()
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

    .nav-link {
        color: #FFFFFF;
    }

</style>