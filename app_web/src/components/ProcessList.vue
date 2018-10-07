<template>
  <div>
    <h1> Processos</h1>
    <my-vuetable 
      :ADD_ROUTER_LINK_TO="ADD_ROUTER_LINK_TO"
      :API_URL="API_URL" 
      :fields="fields"
      :onAction="onAction"
      :canDelete="canDelete"
    > 
    </my-vuetable>
  </div>
</template>

<script>

  import { mapGetters, mapState } from 'vuex'
  import config from '../../config'
  import MyVuetable from './table/MyVuetable'

  const URL = process.env.NODE_ENV === 'production' ? config.build.url : config.dev.url

  export default {
    name: 'processList',
    components: {
      MyVuetable
    },
    data: () => {
      
      let API_URL = `${URL}api/process/pagination`;
      if (localStorage.getItem('userData')) {
        
        const userData = JSON.parse(localStorage.getItem('userData'));
        if (userData && userData.role && userData.role.code === 'finalizador') {
          API_URL = `${API_URL}?userId=${userData.id}`
        }
      }

      return {
        ADD_ROUTER_LINK_TO: '/process/null',
        API_URL: API_URL,
        canDelete: false,
        fields: [
          {
            name: 'name',
            title: 'Nome',
            titleClass: 'text-center'
          },
          {
            name: 'code',
            title: 'Código',
            titleClass: 'text-center'
          },
          {
            name: 'seem',
            title: 'Parecer',
            titleClass: 'text-center'
          },
          {
            name: '__slot:actions',
            title: 'Ações',
            titleClass: 'text-center',
            dataClass: 'text-center'
          }
        ]
      }
    },
    methods: {
      onAction (action, data, index) {

        if (action === 'EDIT') {
          this.$router.push({ name: 'Process', params: { id: data.id }});
        } else if (action === 'DELETE') {
          console.log('custom-actions: ' + action, data.name, index)
        }

      },
    },
    computed: {
      ...mapGetters(['getProfile', 'isFinalizador']),
      ...mapState({profile: state => state.user.profile})
    }
  }
</script>
