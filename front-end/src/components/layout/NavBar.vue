<template>
    <div id="navbar">
        <b-navbar toggleable="md" type="dark" variant="info">

            <b-nav-toggle target="nav_collapse"></b-nav-toggle>

            <img src="../../assets/logo.png">

            <b-navbar-brand href="/">Home</b-navbar-brand>

            <b-collapse is-nav id="nav_collapse">

                <!-- Right aligned nav items -->
                <b-nav is-nav-bar class="ml-auto">
                    <b-nav is-nav-bar>
                        <slot/>
                    </b-nav>
                </b-nav>

            </b-collapse>
        </b-navbar>
    </div>
</template>

<script>
    export default {
        name: "NavBar"
    }
</script>

<style scoped>
    img {
        height: 50px;
    }
</style>