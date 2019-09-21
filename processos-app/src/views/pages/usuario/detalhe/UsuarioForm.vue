<template>
    <v-card>
        <v-card-text>
            <v-form @submit.prevent.stop="salvar">
                <v-layout row wrap>
                    <v-flex xs12 sm8>
                        <v-text-field
                                label="Nome"
                                name="nome"
                                placeholder="Ex. José da Silva"
                                class="mr-3"
                                :disabled="!editando"
                                v-model="value.nome"
                                v-validate="'required'"
                                :error-messages="errors.collect('nome')"
                        />
                    </v-flex>
                    <v-flex xs12 sm4>
                        <v-select
                                clearable
                                label="Papel"
                                name="papel"
                                :disabled="!editando"
                                :items="tiposUsuarioArray"
                                v-model="value.tipo"
                                v-validate="'required'"
                                :error-messages="errors.collect('papel')"
                        />
                    </v-flex>
                    <v-flex xs12>
                        <v-text-field
                                label="E-mail"
                                name="e-mail"
                                placeholder="Ex. josesilva@..."
                                :counter="250"
                                :disabled="!editando"
                                v-model="value.email"
                                v-validate="'required|email'"
                                :error-messages="errors.collect('e-mail')"
                        />
                    </v-flex>
                </v-layout>
            </v-form>
        </v-card-text>
        <v-card-actions v-if="editando">
            <v-layout row wrap>
                <v-flex xs12 class="mb-3">
                    <v-divider/>
                </v-flex>
                <v-flex xs12 class="text-xs-right">
                    <v-btn flat color="red" class="white--text" @click="cancelar">
                        Cancelar
                    </v-btn>
                    <v-btn color="green" class="white--text" @click="salvar">
                        Salvar
                    </v-btn>
                </v-flex>
            </v-layout>
        </v-card-actions>
    </v-card>
</template>

<script>
    import {tiposUsuario} from '@/commons/constants'

    export default {
        name: 'UsuarioForm',
        props: {
            editando: {
                type: Boolean,
                default: true
            },
            value: {
                required: true
            }
        },
        computed: {
            tiposUsuarioArray() {
                const tipos = []
                for (let tipo of Object.keys(tiposUsuario)) {
                    tipos.push(tipo)
                }
                return tipos
            }
        },
        methods: {
            cancelar() {
                this.$router.push({name: 'usuarios'})
            },
            async salvar() {
                const formValido = await this.$validator.validate()
                if (!formValido) {
                    return
                }
                this.$emit('salvar')
            }
        }
    }
</script>