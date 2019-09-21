<template>
    <div>
        <span class="title font-weight-regular">Lista de Pareceres</span>
        <v-expansion-panel class="mt-2">
            <v-expansion-panel-content
                    v-for="parecer in pareceres"
                    :key="parecer.id">
                <template v-slot:header>
                    <div>
                    <span :class="{'green--text': estaRealizado(parecer), 'red--text': !estaRealizado(parecer)}">
                        {{parecer.situacao}}
                    </span>
                        <span class="ml-2">{{parecer.usuario.nome}}</span>
                    </div>
                </template>
                <v-card>
                    <v-card-text>{{parecer.texto}}</v-card-text>
                </v-card>
            </v-expansion-panel-content>
        </v-expansion-panel>
    </div>
</template>

<script>
    export default {
        name: 'ProcessoDetalheParecer',
        props: ['pareceres'],
        methods: {
            estaRealizado(parecer) {
                return parecer.situacao === 'REALIZADO'
            }
        }
    }
</script>