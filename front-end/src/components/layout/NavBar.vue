<template>
    <div id="navbar">
        <b-navbar toggleable="md" type="dark" variant="info">

            <b-nav-toggle target="nav_collapse"></b-nav-toggle>

            <img src="../../assets/logo.png">

            <b-navbar-brand href="#/home">Home</b-navbar-brand>

            <b-collapse is-nav id="nav_collapse">

                <!-- Right aligned nav items -->
                <b-nav is-nav-bar class="ml-auto">
                    <b-nav is-nav-bar class="nav-bar-soft">
                        <slot/>
                        <b-nav-item-dropdown right>
                            <template v-slot:button-content>
                                <em>{{auth.username}}</em>
                            </template>
                            <b-dropdown-item @click="LOGOUT()">Sair</b-dropdown-item>
                        </b-nav-item-dropdown>
                    </b-nav>
                </b-nav>

            </b-collapse>
        </b-navbar>
    </div>
</template>

<script>
    import {mapActions, mapState} from 'vuex'
    import * as types from '../../store/mutation-types'

    export default {
        name: "NavBar",
        computed: {
            ...mapState(['auth'])
        },
        methods: {
            ...mapActions([types.LOGOUT])
        }
    }
</script>

<style scoped>
    img {
        height: 50px;
    }
    .nav-bar-soft li a {
        color: #FFFFFF;
    }
</style>