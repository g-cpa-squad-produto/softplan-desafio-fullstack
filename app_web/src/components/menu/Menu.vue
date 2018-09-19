<template>
  <div id="menu">
    <Slide>
      <a id="home" href="http://localhost:8080/home"> 
        <span>Home</span>  
      </a>
      <a id="home" href="http://localhost:8080/process"> 
        <span>Processos</span>  
      </a>
      <a id="home" href="http://localhost:8080/user"> 
        <span>Usuários</span>  
      </a>
    </Slide>
  </div>
</template>

<script>
import { Slide } from 'vue-burger-menu';

export default {
  name: 'Menu',
  components: {
    Slide,
  },
  data() {
    return {};
  },
};
</script>