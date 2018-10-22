<template>
  <div>

</div>
</template>

<script>
export default {
  created(){
    console.log(this.$auth.check('ADMIN'))
    if(this.$auth.check('ADMIN')){
       this.$router.push({name:'UsuarioList'});
    }else if(this.$auth.check('TRIADOR')){
       this.$router.push({name:'ProcessoList'});
    }else if(this.$auth.check('FINALIZADOR')){
       this.$router.push({name:'ProcessoPendentesParecerUsuarioList'});
    }else{
      this.$auth.logout();
    }
   

  }
}
</script>

<style lang="scss">

</style>

