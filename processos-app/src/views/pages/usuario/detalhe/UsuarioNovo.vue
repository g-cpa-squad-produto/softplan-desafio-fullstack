<template>
    <div>
        <v-layout class="mb-3">
            <v-flex xs10>
                <page-title titulo="Novo Usuário" rota-voltar="usuarios"
                            subtitulo="Informe os dados abaixo para criar um novo usuário"/>
            </v-flex>
        </v-layout>
        <usuario-form v-model="novoUsuario" @salvar="salvarUsuario"/>
    </div>
</template>

<script>
    import {actionTypes} from '@/commons/constants'
    import PageTitle from '@/views/components/PageTitle'
    import UsuarioForm from './UsuarioForm'

    export default {
        name: 'UsuarioNovo',
        components: {UsuarioForm, PageTitle},
        data() {
            return {
                novoUsuario: {}
            }
        },
        methods: {
            async salvarUsuario() {
                try {
                    this.novoUsuario.situacao = 'ATIVO'
                    await this.$store.dispatch(actionTypes.INSERIR_USUARIO, this.novoUsuario)
                    this.mostrarAlertSucessoDefault()
                    this.$router.push({name: 'usuarios'})
                } catch (e) {
                    this.mostrarAlertExcecao(e.response.data)
                }
            }
        }
    }
</script>