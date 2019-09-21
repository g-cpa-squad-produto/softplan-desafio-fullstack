<template>
    <v-content>
        <v-container fluid fill-height>
            <v-layout align-center justify-center>
                <v-flex xs12 sm8 md4>
                    <slot></slot>
                </v-flex>
            </v-layout>
        </v-container>
    </v-content>
</template>

<script>
    export default {
        name: "Container"
    }
</script>

<style scoped>

</style>