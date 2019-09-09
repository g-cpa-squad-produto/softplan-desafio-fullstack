<template>
    <div class="container">
        <b-container fluid>

            <NavBar>
                <b-nav-item href="#/processos">Voltar</b-nav-item>
            </NavBar>

            <b-alert variant="success" show v-if="showSucess" >Salvo com sucesso.</b-alert>
            <b-alert variant="danger" show v-if="showError" >{{msgError}}</b-alert>

            <b-form @submit="onSubmit" @reset="onReset" >

                <b-form-group id="input-group-numero" label="Número:" label-for="input-numero">
                    <b-form-input id="input-numero" type="text" v-model="processo.numero" required placeholder="Número"></b-form-input>
                </b-form-group>

                <b-form-group id="input-group-descricao" label="Descrição:" label-for="input-descricao" >
                    <b-form-input id="input-descricao" type="text" v-model="processo.descricao" required placeholder="Descrição"></b-form-input>
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
        name: "ProcessoForm",
        components: {NavBar},
        data() {
            return {
                showSucess: false,
                showError: false,
                msgError:''
            }
        },
        computed: {
            ...mapState(['processo'])
        },
        created: async function()  {
            this.ATRIBUIR_PROCESSO_INICIAL()
        },
        methods: {
            ...mapActions([types.SALVAR_PROCESSO, types.ATRIBUIR_PROCESSO_INICIAL]),
            onSubmit(evt) {
                evt.preventDefault()

                this.showError = false
                this.showSucess = false
                this.msgError = ''

                this.SALVAR_PROCESSO(this.processo)
                    .then(()=>{
                        this.showSucess = true
                        this.$router.push('/processos')
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