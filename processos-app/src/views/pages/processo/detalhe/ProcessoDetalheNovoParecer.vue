<template>
    <div>
        <v-flex xs12 class="text-xs-center">
            <v-btn color="green" class="white--text" @click="abrirModalParecer">
                Adicionar Parecer
            </v-btn>
        </v-flex>

        <v-dialog v-model="elaborandoParecer" persistent scrollable width="800">
            <v-card>
                <v-card-title>
                    <v-flex xs10>
                        <span class="title">Elaboração do Parecer</span>
                    </v-flex>
                    <v-flex xs2 class="text-xs-right">
                        <v-btn flat icon>
                            <v-icon @click="fecharModalParecer">close</v-icon>
                        </v-btn>
                    </v-flex>
                </v-card-title>
                <v-card-text>
                    <v-flex xs12>
                        <v-textarea
                                label="Parecer"
                                name="parecer"
                                :counter="4000"
                                v-model="parecer"
                                v-validate="'required'"
                                :error-messages="errors.collect('parecer')"
                        />
                    </v-flex>
                </v-card-text>
                <v-card-actions>
                    <v-layout row wrap>
                        <v-flex xs12 class="mb-3">
                            <v-divider/>
                        </v-flex>
                        <v-flex xs12 class="text-xs-center">
                            <v-btn color="green" class="white--text" @click="salvarParecer">
                                Salvar
                            </v-btn>
                        </v-flex>
                    </v-layout>
                </v-card-actions>
            </v-card>
        </v-dialog>
    </div>
</template>

<script>
    export default {
        name: 'ProcessoDetalheNovoParecer',
        data() {
            return {
                elaborandoParecer: false,
                parecer: '',
            }
        },
        methods: {
            abrirModalParecer() {
                this.elaborandoParecer = true
            },
            fecharModalParecer() {
                this.elaborandoParecer = false
            },
            async salvarParecer() {
                const formValido = await this.$validator.validate()
                if (!formValido) {
                    return
                }
                this.$emit('salvar', this.parecer)
                this.fecharModalParecer()
            }
        }
    }
</script>