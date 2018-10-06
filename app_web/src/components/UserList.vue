<template>
  <div>
    <h1> Usuários</h1>
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

  import { mapState } from 'vuex'
  import config from '../../config'
  import MyVuetable from './table/MyVuetable'
  import axios from 'axios'

  const URL = process.env.NODE_ENV === 'production' ? config.build.url : config.dev.url

  export default {
    name: 'userList',
    components: {
      MyVuetable
    },
    data: function() {
      return {
        ADD_ROUTER_LINK_TO: '/user/null',
        API_URL: `${URL}api/users/pagination`,
        canDelete: true,
        fields: [
          {
            name: 'name',
            title: '<span class="glyphicon glyphicon-user"></span> Nome',
            titleClass: 'text-center'
          },
          {
            name: 'email',
            title: '<span class="glyphicon glyphicon-envelope"></span> E-mail',
            titleClass: 'text-center'
          },
          {
            name: 'role.name',
            title: '<span class="glyphicon glyphicon-lock"></span> Cargo',
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
      async onAction (action, data, index) {

        if (action === 'EDIT') {
          this.$router.push({ name: 'User', params: { id: data.id }});
        } else if (action === 'DELETE') {
          
          await axios.delete(`${URL}api/users/${data.id}`)
          .then(res => {
            
            if (res.data.status == "200") {
              console.log(res.data.message)
            } else if (res.data.status == "500") {
              console.error(res.data.message)
            }
            
          })
          .catch(err => {
            console.log(err)
          })

        }

      },
    },
    computed: mapState({profile: state => state.user.profile})
  }
</script>
