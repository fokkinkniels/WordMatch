<template>
    <div> 
        <User/>
      <gauth/>
  </div>
</template>


<script>
import User from './components/User.vue'
import gauth from "./components/gauth.vue"

export default {
  name: 'App',
  components: {
    gauth,
    User
  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
