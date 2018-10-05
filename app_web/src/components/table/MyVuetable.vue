<template>
  <div class="container">
    <filter-bar
      :ADD_ROUTER_LINK_TO="ADD_ROUTER_LINK_TO"
    >
    </filter-bar>
    <vuetable ref="vuetable"
      :api-url="API_URL"
      :fields="fields"
      :css="css"
      pagination-path=""
      :per-page="10"
      :multi-sort="true"
      multi-sort-key="ctrl"
      :append-params="moreParams"
      @vuetable:pagination-data="onPaginationData"
    >
      <template slot="actions" scope="props">
        <button class="btn btn-default btn-sm"
          @click="onAction('EDIT', props.rowData, props.rowIndex)">
          <span class="glyphicon glyphicon-pencil"></span>
        </button>
        <button class="btn btn-default btn-sm"
          @click="onAction('DELETE', props.rowData, props.rowIndex)">
          <span class="glyphicon glyphicon-remove"></span>
        </button>
      </template>
    </vuetable>
    <div>
      <vuetable-pagination-info ref="paginationInfo"
        :css="css.pagination"
        info-class="pull-left"
        info-template="Exibindo {from} à {to} de {total} itens"
      ></vuetable-pagination-info>
      <vuetable-pagination ref="pagination"
        :css="css.pagination"
        @vuetable-pagination:change-page="onChangePage"
      ></vuetable-pagination>
    </div>
  </div>
</template>

<script>
import Vue from 'vue'
import VueEvents from 'vue-events'
import Vuetable from 'vuetable-2/src/components/Vuetable'
import VuetablePagination from 'vuetable-2/src/components/VuetablePagination'
import VuetablePaginationInfo from 'vuetable-2/src/components/VuetablePaginationInfo'
import FilterBar from './FilterBar'

Vue.use(VueEvents)

export default {
  components: {
    Vuetable,
    VuetablePagination,
    VuetablePaginationInfo,
    FilterBar
  },
  props: ['ADD_ROUTER_LINK_TO', 'API_URL', 'fields', 'onAction'],
  data () {
    return {
      css: {
        tableClass: 'table table-striped table-bordered',
        loadingClass: 'loading',
        ascendingIcon: 'glyphicon glyphicon-chevron-up',
        descendingIcon: 'glyphicon glyphicon-chevron-down',
        handleIcon: 'glyphicon glyphicon-menu-hamburger',
        pagination: {
          infoClass: 'pull-left',
          wrapperClass: 'vuetable-pagination pull-right',
          activeClass: 'btn-primary',
          disabledClass: 'disabled',
          pageClass: 'btn btn-border',
          linkClass: 'btn btn-border',
          icons: {
            first: '',
            prev: '',
            next: '',
            last: '',
          },
        },
      },
      moreParams: {}
  	}
  },
  mounted () {
    this.$events.listen('filter-set', filterText => this.onFilterSet(filterText))
    this.$events.listen('filter-reset', () => this.onFilterReset())
  },
  methods: {
    onPaginationData (paginationData) {
      this.$refs.pagination.setPaginationData(paginationData)
      this.$refs.paginationInfo.setPaginationData(paginationData)
    },
    onChangePage (page) {
      this.$refs.vuetable.changePage(page)
    },
    onFilterSet (filterText) {
      this.moreParams = {
        'filter': filterText
      }
      Vue.nextTick(() => this.$refs.vuetable.refresh())
    },
    onFilterReset () {
      this.moreParams = {}
      this.$refs.vuetable.refresh()
      Vue.nextTick(() => this.$refs.vuetable.refresh())
    }
  },
}
</script>
<style>
.pagination {
  margin-top: 0;
}
.btn.btn-border {
  border: 1px solid;
  margin-right: 2px;
}
.vuetable-pagination-info {
  margin-top: 8px !important;
}
span.sort-icon {
  float: right;
  color: #ff9100;
}
</style>
