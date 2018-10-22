<template>
  <div id="app">

    <nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
      <a class="navbar-brand-custom" href="/">Desafio Softplan - Thiago Bernardo</a>
      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExampleDefault" aria-controls="navbarsExampleDefault" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>

      <div class="collapse navbar-collapse" id="navbarsExampleDefault">
        
        <Menu></Menu>
         
        <div class="form-inline my-2 my-lg-0" style="padding-right:10px; color:#fff">
          {{ $auth.user().login }} - {{ $auth.user().nome }} - {{ $auth.user().tipoUsuario }}
        </div>

        <form class="form-inline my-2 my-lg-0" v-if="$auth.check()">
          <button class="btn btn-outline-danger my-2 my-sm-0" type="button" @click="$auth.logout()">logout</button>
        </form>

      </div>
    </nav>

    <main role="main">
      <div class="container app-home">
        <router-view></router-view>
      </div> <!-- /container -->
    </main>

  </div>
</template>

<script>
import Menu from "@/components/Menu";
export default {
  components: {
    Menu
  }
};
</script>


<style lang="scss">
div.container.app-home {
  padding-top: 5rem;
}
.navbar-brand-custom {
  display: inline-block;
  padding-top: 0.3125rem;
  padding-bottom: 0.3125rem;
  margin-right: 1rem;
  font-size: 1.25rem;
  line-height: inherit;
  white-space: nowrap;
  min-height: 1.25rem;
  color: #fff;
}
</style>
