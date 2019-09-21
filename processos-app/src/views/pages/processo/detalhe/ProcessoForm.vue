<template>
    <v-card>
        <v-card-text>
            <v-form @submit.prevent.stop="salvar">
                <v-flex xs12>
                    <v-text-field
                            label="Título"
                            name="título"
                            placeholder="Processo jurídico..."
                            :counter="250"
                            v-model="value.nome"
                            v-validate="'required'"
                            :error-messages="errors.collect('título')"
                    />
                </v-flex>
                <v-flex xs12>
                    <v-textarea
                            label="Texto"
                            name="texto"
                            :counter="4000"
                            v-model="value.texto"
                            v-validate="'required'"
                            :error-messages="errors.collect('texto')"
                    />
                </v-flex>
                <v-flex xs12>
                    <v-select
                            attach chips multiple
                            label="Quem realizará o parecer para o processo?"
                            name="usuários para parecer"
                            item-text="nome"
                            item-value="id"
                            :items="usuariosFinalizadores"
                            v-model="value.usuariosParecer"
                            v-validate="'required'"
                            :error-messages="errors.collect('usuários para parecer')"
                    ></v-select>
                </v-flex>
            </v-form>
        </v-card-text>
        <v-card-actions>
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
    import {mapState} from 'vuex'
    import {tiposUsuario} from '@/commons/constants'

    export default {
        name: 'ProcessoForm',
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
            usuariosFinalizadores() {
                return this.usuarios.filter(usuarioIterator => usuarioIterator.tipo === tiposUsuario.FINALIZADOR)
            },
            ...mapState(['usuarios'])
        },
        methods: {
            cancelar() {
                this.$router.push({name: 'processos'})
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