<template>
    <div>
        <h1>Nao Autorizado</h1>
        <v-btn flat @click="voltar" class="ml-0 pl-0">
            <v-icon class="mr-2">arrow_back</v-icon>
            Voltar
        </v-btn>
    </div>
</template>

<script>
    export default {
        name: 'AcessoNaoAutorizado',
        methods: {
            voltar() {
                this.$router.back()
                this.$router.back()
            }
        }
    }
</script>