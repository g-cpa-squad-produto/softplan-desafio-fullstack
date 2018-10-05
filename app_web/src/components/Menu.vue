<template>
  <div class="menu">
    <ul>
      <li v-if="isProfileLoaded">
        <router-link to="/userList">Usuários</router-link>
      </li>
      <li v-if="isProfileLoaded">
        <router-link to="/processList">Processos</router-link>
      </li>
      <li v-if="isAuthenticated" @click="logout">
        <span class="logout">Logout</span>
      </li>
      <li v-if="!isAuthenticated && !authLoading">
        <router-link to="/login">Login</router-link>
      </li>
    </ul>
  </div>
</template>

<script>
  import { mapGetters, mapState } from 'vuex'
  import { AUTH_LOGOUT } from 'actions/auth'

  export default {
    name: 'menu',
    methods: {
      logout: function () {
        this.$store.dispatch(AUTH_LOGOUT).then(() => this.$router.push('/login'))
      }
    },
    computed: {
      ...mapGetters(['getProfile', 'isAuthenticated', 'isProfileLoaded']),
      ...mapState({
        authLoading: state => state.auth.status === 'loading',
        name: state => `${state.user.profile.title} ${state.user.profile.name}`,
      })
    },
  }
</script>

<style lang="scss" scoped>
  a {
    color: white;
    text-decoration: none;
  }
  .menu {
    display: flex;
    color: white;
    align-items: center;
    background-color: #ffa035;
    padding: 5px;
    margin-bottom: 30px;

    ul {
      display: flex;
      &:first-child {
        flex-grow: 1;
      }
      li {
        padding-right: 1em;
      }
    }
  }
  .logout {
    &:hover {
      cursor: pointer;
    }
  }

</style>
