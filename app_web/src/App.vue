<template>
  <div id="app">
    <menu-navigation/>
    <div class="main-container">
      <center-container>
        <router-view/>
      </center-container>
    </div>
  </div>
</template>

<script>
import MenuNavigation from 'components/Menu'
import { USER_REQUEST } from 'actions/user'

export default {
  components: {
    MenuNavigation
  },
  name: 'app',
  created: function () {
    if (this.$store.getters.isAuthenticated) {
      this.$store.dispatch(USER_REQUEST)
    }
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
    /* margin-top: 60px; */
  }

  body {
    margin: 0;
    font-family: 'Roboto', sans-serif;
    color: #2e426b;
  }
  ul {
    margin: 0;
    padding: 0;
    list-style-type: none;
  }
</style>

<style scoped>
  .main-container {
    min-height: calc(100vh - 70px);
  }
</style>
