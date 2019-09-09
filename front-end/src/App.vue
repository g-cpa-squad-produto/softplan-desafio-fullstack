<template>
    <div id="app">
        <router-view></router-view>
    </div>
</template>

<script>

    import * as types from './store/mutation-types'
    import {mapActions} from 'vuex'

    export default {
        name: 'app',
        mounted: async function () {
            await this.LOAD_TOKEN()
        },
        methods: {
            ...mapActions([types.LOAD_TOKEN])
        }
    }
</script>

<style>
    #app {
        font-family: 'Avenir', Helvetica, Arial, sans-serif;
        -webkit-font-smoothing: antialiased;
        -moz-osx-font-smoothing: grayscale;
        text-align: center;
        color: #2c3e50;
        #margin-top: 60px;
    }
</style>