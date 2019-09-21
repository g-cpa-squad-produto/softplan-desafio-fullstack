<template>
    <v-dialog persistent value="true" width="400px">
        <v-card>
            <v-card-text>
                {{message}}
            </v-card-text>
            <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn color="red" flat @click="$emit('declined')">
                    {{declineLabel}}
                </v-btn>
                <v-btn color="green" class="white--text" flat @click="$emit('confirmed')">
                    {{confirmLabel}}
                </v-btn>
            </v-card-actions>
        </v-card>
    </v-dialog>
</template>

<script>
    export default {
        name: 'ConfirmDialog',
        props: {
            message: {
                type: String,
                required: true
            },
            confirmLabel: {
                type: String,
                default: 'Confirmar'
            },
            declineLabel: {
                type: String,
                default: 'Cancelar'
            }
        }
    }
</script>