<template>
    <container>
        <v-list two-line>
            <v-subheader>
                Selecione o Usuário
            </v-subheader>
            <v-divider/>
            <template v-for="usuario in usuarios">
                <v-list-tile
                        avatar
                        :key="usuario.id"
                        @click="selecionarUsuario(usuario)">

                    <v-list-tile-avatar>
                        <v-icon>account_circle</v-icon>
                    </v-list-tile-avatar>

                    <v-list-tile-content>
                        <v-list-tile-title v-html="usuario.nome"></v-list-tile-title>
                        <v-list-tile-sub-title class="text-capitalize">
                            {{usuario.tipo | titleCase}}
                        </v-list-tile-sub-title>
                    </v-list-tile-content>

                </v-list-tile>
            </template>
        </v-list>
    </container>
</template>

<script>
    import {mapState} from 'vuex'
    import {actionTypes, mutationTypes, tiposUsuario} from '@/commons/constants'
    import Container from '@/views/components/Container'

    export default {
        name: 'Login',
        components: {Container},
        computed: {
            ...mapState(['usuarios'])
        },
        async mounted() {
            await this.buscarUsuarios()
        },
        methods: {
            async buscarUsuarios() {
                await this.$store.dispatch(actionTypes.BUSCAR_USUARIOS)
            },
            selecionarUsuario(usuario) {
                this.$store.commit(mutationTypes.SET_USUARIO, usuario)
                const rota = this.definirRotaParaUsuarioSelecionado(usuario)
                this.$router.push(rota)
            },
            definirRotaParaUsuarioSelecionado(usuario) {
                if (tiposUsuario.ADMINISTRADOR === usuario.tipo) {
                    return {name: 'usuarios'}
                } else if (tiposUsuario.TRIADOR === usuario.tipo) {
                    return {name: 'processos'}
                } else if (tiposUsuario.FINALIZADOR === usuario.tipo) {
                    return {name: 'processosPendentes'}
                }
            }
        }
    }
</script>