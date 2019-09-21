<template>
    <v-snackbar v-model="show" :color="color" right top>
        {{ text }}
        <v-btn dark flat @click="show = false">
            <v-icon small>close</v-icon>
        </v-btn>
    </v-snackbar>
</template>

<script>
    export default {
        name: 'Alert',
        data() {
            return {
                text: '',
                color: '',
                show: false
            }
        },
        created: function () {
            this.$store.watch(state => state.alert, () => {
                console.log(alert)
                const alert = this.$store.state.alert
                if (alert.message !== '') {
                    this.show = true
                    this.text = alert.message
                    this.color = alert.type
                }
            })
        }
    }
</script>