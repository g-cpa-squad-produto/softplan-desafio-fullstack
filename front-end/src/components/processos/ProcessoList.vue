<template>
    <div class="container">
        <b-container fluid>
            <NavBar>
                <b-nav-item href="#/usuarios/add">Novo Usuário</b-nav-item>
            </NavBar>

            <b-table class="container" striped hover :items="usuarios" :fields="columns" responsive >

                <template v-slot:cell(actions)="row">
                    <a :href="'#/usuarios/edit/' + row.item.id" class="btn btn-info btn-sm" style="margin-right: 4px" >Editar</a>
                    <a href="#link" class="btn btn-danger btn-sm" >Excluir</a>
                </template>

            </b-table>

        </b-container>
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
                columns: [
                    {
                        key: 'id',
                        label: 'Código'
                    },
                    {
                        key: 'numero',
                        label: 'Número'
                    },
                    {
                        key: 'descricao',
                        label: 'Descrição'
                    },
                    {
                        key: 'papel.descricao',
                        label: 'Papel'

                    },
                    {
                        key: 'actions',
                        label: 'Ações'
                    }
                ]
            }
        },
        computed: {
            ...mapState(['usuarios'])
        },
        mounted: async function () {
            await this.BUSCAR_USUARIOS()
        },
        methods: {
            ...mapActions([types.BUSCAR_USUARIOS])
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