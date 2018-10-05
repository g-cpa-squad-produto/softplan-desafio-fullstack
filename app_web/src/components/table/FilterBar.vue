<template>
  <div>
    <div class="filter-bar pull-left">
      <div class="form-inline">
        <div class="form-group">
          <label>Buscar por:</label>
          <input type="text" v-model="filterText" class="form-control" @keyup.enter="doFilter" placeholder="nome, email ou cargo">
          <button class="btn btn-primary" @click="doFilter">Buscar</button>
          <button class="btn btn-default" @click="resetFilter">Limpar</button>
        </div>
      </div>
    </div>
    <div class="filter-bar pull-right">
      <div class="form-inline">
        <div class="form-group">
          <router-link :to="ADD_ROUTER_LINK_TO" class="btn btn-primary" tag="button">Adicionar</router-link>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  props: ['ADD_ROUTER_LINK_TO'],
  data () {
    return {
      filterText: ''
    }
  },
  methods: {
    doFilter () {
      this.$events.fire('filter-set', this.filterText)
    },
    resetFilter () {
      this.filterText = ''
      this.$events.fire('filter-reset')
    }
  }
}
</script>
<style>
.filter-bar {
  margin-bottom: 8px;
}

</style>