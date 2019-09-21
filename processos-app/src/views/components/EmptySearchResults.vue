<template>
    <v-card height="150px">
        <v-card-text class="text-xs-center mt-3 title">
            <v-img contain src="/img/search.svg" max-height="70px"/>
            <div class="mt-3">Ops, sua busca não retornou nenhum resultado.</div>
        </v-card-text>
    </v-card>
</template>

<script>
    export default {
        name: 'EmptySearchResults'
    }
</script>