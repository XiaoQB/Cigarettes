<template>
  <div id="app">
    <router-view></router-view>
  </div>
</template>
<script src="main.js"></script>
<script>


export default {
  name: 'app',
  components: {
    // eslint-disable-next-line vue/no-unused-components
  }
}
</script>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
