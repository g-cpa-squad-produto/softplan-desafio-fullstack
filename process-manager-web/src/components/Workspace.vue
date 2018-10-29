<template>
  <div id="app">
  <v-app id="inspire">
    <v-navigation-drawer
      clipped
      fixed
      disable-resize-watcher
      stateless
      v-model="drawer"
      app
    >
      <v-list dense>
        <v-list-tile @click="changeComponent(userComponent)" ripple v-if="checkRole(['ADMIN'])">
          <v-list-tile-action>
            <v-icon>person</v-icon>
          </v-list-tile-action>
          <v-list-tile-content>
            <v-list-tile-title>{{$i18n.t('messages.menu.userManager')}}</v-list-tile-title>
          </v-list-tile-content>
        </v-list-tile>
        <v-list-tile @click="changeComponent(processComponent)" ripple v-if="checkRole(['ADMIN','USER_MANAGER'])">
          <v-list-tile-action>
            <v-icon>library_books</v-icon>
          </v-list-tile-action>
          <v-list-tile-content>
            <v-list-tile-title>{{$i18n.t('messages.menu.processManager')}}</v-list-tile-title>
          </v-list-tile-content>
        </v-list-tile>
        <v-list-tile @click="changeComponent(processActionComponent)" ripple v-if="checkRole(['ADMIN','USER_ACTION'])">
          <v-list-tile-action>
            <v-icon>archive</v-icon>
          </v-list-tile-action>
          <v-list-tile-content>
            <v-list-tile-title>{{$i18n.t('messages.menu.processFinalize')}}</v-list-tile-title>
          </v-list-tile-content>
        </v-list-tile>
      </v-list>
    </v-navigation-drawer>
    <v-toolbar color="#1A405F" dark fixed app clipped-left>
      <v-toolbar-side-icon @click.stop="drawer = !drawer"></v-toolbar-side-icon>
      <v-toolbar-title>{{$i18n.t('messages.appName')}}</v-toolbar-title>
       <v-spacer></v-spacer>
      <v-btn icon @click="logout">
        <v-icon>logout</v-icon>
      </v-btn>
    </v-toolbar>
     <main>
    </main>
    <v-content>
      <v-container id="containerFrame" fluid fill-height>
        <v-layout align-start>
         <v-flex xs12 d-flex fill-height>
            <component :is="component"></component>
         </v-flex>
        </v-layout>
      </v-container>
    </v-content>
    <v-footer color="#1A405F" app fixed>
      <span class="white--text">&copy;</span>
    </v-footer>
  </v-app>
</div>
</template>

<style>
main {
  padding-top: 10px !important;
}
</style>

<script>
import CustomerService from '@/api/customer-service'
import UserManager from '@/components/UserManager'
import ProcessManager from '@/components/ProcessManager'

export default {
  data: () => ({
    drawer: true,
    loadDefault: true,
    userComponent: UserManager,
    processComponent: ProcessManager,
    processActionComponent: null,
    component: null
  }),
  components: {
    UserManager
  },
  methods: {
    changeComponent (component) {
      this.component = component
    },
    checkRole (roles) {
      if (CustomerService.getCustomer() == null) return false
      if (roles.includes(JSON.parse(CustomerService.getCustomer()).role)) {
        return true
      } else {
        return false
      }
    },
    logout () {
      CustomerService.destroyCustomer()
      this.$router.go(this.$router.replace({name: 'Login'}))
    }
  },
  props: {
    source: String
  },
  destroyed () {
    CustomerService.destroyCustomer()
  }
}
</script>
