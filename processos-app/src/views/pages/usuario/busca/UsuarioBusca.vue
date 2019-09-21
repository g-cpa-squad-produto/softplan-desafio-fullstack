<template>
    <div>
        <v-layout class="mb-3">
            <v-flex xs10>
                <page-title titulo="Usuários" subtitulo="Listagem dos usuários" rota-voltar="login"/>
            </v-flex>
            <v-flex xs2 class="text-xs-right">
                <v-btn color="green" class="white--text" @click="irParaNovoUsuario">
                    Novo
                </v-btn>
            </v-flex>
        </v-layout>
        <usuario-busca-tabela :usuarios="usuarios" @remover="confirmarRemocao" v-if="existeUsuarios"/>
        <empty-search-results v-else/>
    </div>
</template>

<script>
    import {mapState} from 'vuex'
    import PageTitle from '@/views/components/PageTitle'
    import EmptySearchResults from '@/views/components/EmptySearchResults'
    import UsuarioBuscaTabela from './UsuarioBuscaTabela'

    export default {
        name: 'UsuarioBusca',
        components: {PageTitle, EmptySearchResults, UsuarioBuscaTabela},
        data() {
            return {
                confirmandoRemocao: false,
                usuarioRemocao: null
            }
        },
        computed: {
            existeUsuarios() {
                return this.usuarios.length > 0
            },
            ...mapState(['usuarios'])
        },
        methods: {
            confirmarRemocao(usuario) {
                this.usuarioRemocao = usuario
                this.confirmandoRemocao = true
            },
            irParaNovoUsuario() {
                // TODO
            },
            remover() {
                // TODO
            }
        }
    }
</script>
