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
        <confirm-dialog
                message="Tem certeza que deseja remover o usuário?"
                @declined="fecharConfirmacaoRemocao"
                @confirmed="remover"
                v-if="confirmandoRemocao"
        />
    </div>
</template>

<script>
    import {mapState} from 'vuex'
    import {actionTypes} from '@/commons/constants'
    import PageTitle from '@/views/components/PageTitle'
    import EmptySearchResults from '@/views/components/EmptySearchResults'
    import ConfirmDialog from '@/views/components/ConfirmDialog'
    import UsuarioBuscaTabela from './UsuarioBuscaTabela'

    export default {
        name: 'UsuarioBusca',
        components: {ConfirmDialog, PageTitle, EmptySearchResults, UsuarioBuscaTabela},
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
        async mounted() {
            this.buscarUsuarios()
        },
        methods: {
            async buscarUsuarios() {
                await this.$store.dispatch(actionTypes.BUSCAR_USUARIOS)
            },
            confirmarRemocao(usuario) {
                this.usuarioRemocao = usuario
                this.confirmandoRemocao = true
            },
            fecharConfirmacaoRemocao() {
                this.usuarioRemocao = null
                this.confirmandoRemocao = false
            },
            irParaNovoUsuario() {
                this.$router.push({name: 'usuarioNovo'})
            },
            async remover() {
                try {
                    await this.$store.dispatch(actionTypes.REMOVER_USUARIO, this.usuarioRemocao.id)
                    this.mostrarAlertSucessoDefault()
                    await this.buscarUsuarios()
                    this.fecharConfirmacaoRemocao()
                } catch (e) {
                    this.fecharConfirmacaoRemocao()
                    this.mostrarAlertExcecao(e.response.data)
                }
            }
        }
    }
</script>
