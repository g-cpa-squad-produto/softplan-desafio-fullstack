<template>
    <v-content>
        <alert/>
        <v-container fluid fill-height>
            <v-layout align-center justify-center>
                <v-flex :class="classe">
                    <slot></slot>
                </v-flex>
            </v-layout>
        </v-container>
    </v-content>
</template>

<script>
    import Alert from './Alert'
    export default {
        name: 'Container',
        components: {Alert},
        props: {
            classe: {
                type: String,
                default: 'xs12 sm8 md8'
            }
        }
    }
</script>