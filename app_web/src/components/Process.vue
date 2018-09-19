<template>
  <div id="process">
    <Menu></Menu>
    <h1>Secure Area</h1>
    <p>
      This is a secure area
    </p>
    <p>
      This is a secure area
    </p>
    <p>
      This is a secure area
    </p>
    <p>
      This is a secure area
    </p>
    <p>
      This is a secure area
    </p>
    <p>
      This is a secure area
    </p>
    <p>
      This is a secure area
    </p>
  </div>
</template>

<script>
import Menu from './menu/Menu.vue';

export default {
  name: 'Process',
  components: {
    Menu,
  },
  data() {
    return {};
  },
};
</script>

<style scoped>
#process {
  background-color: #ffffff;
  border: 1px solid #cccccc;
  padding: 20px;
  margin-top: 10px;
}
</style>