<template>
    <div> 
      <User/>
      <game/>
  </div>
</template>


<script>
import User from './components/User.vue'
import game from "./components/game.vue"

export default {
  name: 'App',
  components: {
    User,
    game
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
