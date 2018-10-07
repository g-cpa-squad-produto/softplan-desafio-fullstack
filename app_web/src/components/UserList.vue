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
  import Swal from 'sweetalert2'

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
          .then(resp => {
            
            if (resp.data.status == "200") {
              
              Swal({
                type: 'success',
                title: resp.data.message,
                showConfirmButton: false,
                timer: 1500
              })
              
              document.getElementById('searchButton').click();

            } else if (resp.data.status == "500") {
              
              Swal({
                type: 'error',
                title: 'Oops...',
                text: resp.data.message
              })

            }
            
          })
          .catch(err => {
            Swal({
                type: 'error',
                title: 'Oops...',
                text: 'Algo deu errado. Tente novamente mais tarde!'
              })
          })
        }
      },
    },
    computed: mapState({profile: state => state.user.profile})
  }
</script>
