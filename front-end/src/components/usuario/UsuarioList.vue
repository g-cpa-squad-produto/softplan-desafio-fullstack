<template>
    <div class="container">
        <b-container fluid>
            <NavBar>
                <b-nav-item href="#/add">Novo Usuário</b-nav-item>
            </NavBar>

            <b-table class="container" striped hover :items="usuarios">
                <template slot="Delete" scope="row">
                    <b-button size="sm" variant="danger" @click.stop="deleteItem(row.index)">Delete</b-button>
                    <b-button size="sm" variant="secondary" @click.stop="editItem(row.item, row.index)">Edit</b-button>
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
        name: "UsuarioList",
        components: {NavBar},
        data() {
            return {}
        },
        computed: {
            ...mapState(['usuarios'])
        },
        mounted: async function () {
            await this.BUSCAR_USUARIOS();
        },
        methods: {
            ...mapActions([types.BUSCAR_USUARIOS])
        }
    }

</script>

<style>
    .container {
        width: 80%;
    }

    th {
        text-align: center;
    }
</style>