<template>
  <div id="app">
    <router-view @authenticated="setAuthenticated"/>
  </div>
</template>

<script>
import CustomerService from '@/api/customer-service'

export default {
  name: 'App',
  data () {
    return {
      authenticated: false
    }
  },
  mounted () {
    if (!this.authenticated && CustomerService.getCustomer() == null) {
      this.$router.replace({ name: 'Login' })
    }
  },
  methods: {
    setAuthenticated (status) {
      this.authenticated = status
    },
    logout () {
      this.authenticated = false
    }
  }
}
</script>
