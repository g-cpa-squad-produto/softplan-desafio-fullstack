<template>
  <div class="jumbotron">
    <div class="container">
      <div class="row">
        <h2>{{getProfile.name}}</h2>
          <p><strong>E-mail: </strong> {{getProfile.email}} </p>
          <p><strong>Cargo: </strong> {{getProfile.role.name}} </p>
      </div>
    </div>
  </div>
</template>

<script>
  import { mapGetters } from 'vuex'

  export default {
    name: 'profile',
    computed: {
      ...mapGetters(['getProfile']),
      loading: function () {
        return this.authStatus === 'loading' && !this.isAuthenticated
      }
    },
  }
</script>

<style>
  .home {
    display: flex;
    align-items: center;
    flex-direction: column;
  }
  
</style>