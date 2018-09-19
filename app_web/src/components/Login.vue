<template>
  <div id="login">
    <h1>Login</h1>
    <input type="text" name="username" v-model="input.username" placeholder="Username" />
    <input type="password" name="password" v-model="input.password" placeholder="Password" />
    <button type="button" v-on:click="login()">Login</button>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  name: 'Login',
  data() {
    return {
      input: {
        username: '',
        password: '',
      },
    };
  },
  methods: {
    login() {
      if (this.input.username != '' && this.input.password != '') {
        axios
          .post('http://localhost:9090/api/users/oauth', {
            email: this.input.username,
            password: this.input.password,
          })
          .then(isAuthenticated => {
            if (isAuthenticated.data) {
              localStorage.userData = isAuthenticated.data;
              this.$emit('authenticated', true);
              this.$router.replace({ name: 'home' });
            } else {
              this.$swal({
                type: 'error',
                text: 'Email e/ou senha errado(s). Por favor, tente novamente.',
              });
            }
          })
          .catch(error => {
            this.$swal({
              type: 'error',
              title: 'Opss',
              text: `Alguma coisa deu errada: ${error}`,
            });
          });
      } else {
        this.$swal({
          type: 'error',
          text: 'Os campos email e senha são obrigatórios.',
        });
      }
    },
    // login() {
    //   if (this.input.username != '' && this.input.password != '') {
    //     if (
    //       this.input.username == this.$parent.mockAccount.username &&
    //       this.input.password == this.$parent.mockAccount.password
    //     ) {
    //       this.$emit('authenticated', true);
    //       this.$router.replace({ name: 'home' });
    //     } else {
    //       console.log('The username and / or password is incorrect');
    //     }
    //   } else {
    //     console.log('A username and password must be present');
    //   }
    // },
  },
};
</script>

<style scoped>
#login {
  width: 500px;
  border: 1px solid #cccccc;
  background-color: #ffffff;
  margin: auto;
  margin-top: 200px;
  padding: 20px;
}
</style>