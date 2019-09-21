<template>
    <div>
        <span class="headline mr-2">
            <v-icon class="mr-2" v-if="rotaVoltar" @click="voltar">arrow_back</v-icon>
            {{titulo}}
        </span>
        <span class="text--secondary" v-if="subtitulo">{{subtitulo}}</span>
    </div>
</template>

<script>
    export default {
        name: 'PageTitle',
        props: {
            titulo: {
                type: String,
                required: true
            },
            subtitulo: {
                type: String,
                required: false
            },
            rotaVoltar: {
                type: String,
                default: null
            }
        },
        methods: {
            voltar() {
                this.$router.push({name: this.rotaVoltar})
            }
        }
    }
</script>