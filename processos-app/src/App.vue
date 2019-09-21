<template>
    <v-app id="app" data-app>
        <container>
            <router-view/>
        </container>
    </v-app>
</template>

<script>
    import Container from './views/components/Container'
    export default {
        name: 'app',
        components: {Container}
    }
</script>
