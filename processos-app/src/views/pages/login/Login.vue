<template>
    <div>
        <h1>Login</h1>
        <div v-for="usuario in usuarios">
            {{usuario}}
        </div>
    </div>

</template>

<script>
    import {mapState} from 'vuex'
    import {actionTypes} from '@/commons/constants'

    export default {
        name: 'Login',
        computed: {
            ...mapState(['usuarios'])
        },
        async mounted() {
            await this.buscarUsuarios()
        },
        methods: {
            async buscarUsuarios() {
                await this.$store.dispatch(actionTypes.BUSCAR_USUARIOS)
            }
        }
    }
</script>