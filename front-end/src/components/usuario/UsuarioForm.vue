<template>
    <div class="container">
        <b-container fluid>

            <NavBar>
                <b-nav-item href="/usuarios" to="/usuarios" >Voltar</b-nav-item>
            </NavBar>

            <b-alert variant="success" show v-if="showSucess" >Salvo com sucesso.</b-alert>
            <b-alert variant="danger" show v-if="showError" >{{msgError}}</b-alert>

            <b-form @submit="onSubmit" @reset="onReset" >

                <b-form-group id="input-group-nome" label="Nome:" label-for="input-nome">
                    <b-form-input id="input-nome" v-model="usuario.nome" required placeholder="Nome"></b-form-input>
                </b-form-group>

                <b-form-group id="input-group-email" label="E-mail:" label-for="input-email" >
                    <b-form-input id="input-email" v-model="usuario.email" type="email" required placeholder="Endereço de Email"></b-form-input>
                </b-form-group>

                <b-form-group id="input-group-papel" label="Papel:" label-for="input-papel">
                    <b-form-select id="input-papel" v-model="usuario.papel.id" :options="papeis" required text-field="descricao" value-field="id"></b-form-select>
                </b-form-group>

                <b-button type="submit" variant="primary">Salvar</b-button>
            </b-form>

        </b-container>
    </div>
</template>

<script>
    import {mapActions, mapState} from 'vuex'
    import * as types from '../../store/mutation-types'
    import NavBar from "../layout/NavBar";

    export default {
        name: "UsuarioForm",
        components: {NavBar},
        data() {
            return {
                showSucess: false,
                showError: false,
                msgError:''
            }
        },
        computed: {
            ...mapState(['papeis', 'usuario'])
        },
        created: async function()  {
            if(this.$route.params.id) {
                await this.BUSCAR_USUARIO(this.$route.params.id)
            }else{
                this.ATRIBUIR_USUARIO_INICIAL()
            }
        },
        mounted: async function () {
            await this.BUSCAR_PAPEIS()
        },
        methods: {
            ...mapActions([types.BUSCAR_PAPEIS, types.SALVAR_USUARIO, types.BUSCAR_USUARIO, types.ATRIBUIR_USUARIO_INICIAL]),
            onSubmit(evt) {
                evt.preventDefault()

                this.showError = false;
                this.showSucess = false;
                this.msgError = '';

                let isNovoUsuario = !this.usuario.id;

                this.SALVAR_USUARIO(this.usuario)
                    .then(()=>{
                        this.showSucess = true
                        if(isNovoUsuario){
                            this.$router.push('/usuarios/edit/' + this.usuario.id)
                        }
                    })
                    .catch((err) => {

                        this.showError = true;
                        this.msgError = err.response && err.response.data.message ? err.response.data.message : 'Erro ao realizar ação';
                    })
            },
            onReset(evt) {
                evt.preventDefault()
            }
        }
    }
</script>

<style scoped>
    .container {
        width: 100%;
    }

</style>