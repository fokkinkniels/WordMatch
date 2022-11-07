<template>
    <div id="app"> 
      <select-username
        v-if="!usernameAlreadySelected"
        @input="onUsernameSelection"
      />
    <chat v-else />
  </div>
</template>


<script>

import SelectUsername from "./components/SelectUsername";
import Chat from "./components/Chat";
import socket from "./socket";

export default {
    name: "App",
    components: {
        Chat,
        SelectUsername,
    },
    data() {
      return {
        usernameAlreadySelected: false,
      };
  },
  methods: {
      onUsernameSelection(username) {
        //implement own login system and own usernames
        this.usernameAlreadySelected = true;
        socket.auth = { username };
        socket.connect();
      },
  },
  created() {
    const sessionID = localStorage.getItem("sessionID");

    if (sessionID) {
      this.usernameAlreadySelected = true;
      socket.auth = { sessionID };
      socket.connect();
    }

    socket.on("session", ({ sessionID, userID }) => {
      // attach the session ID to the next reconnection attempts
      socket.auth = { sessionID };
      // store it in the localStorage
      localStorage.setItem("sessionID", sessionID);
      // save the ID of the user
      socket.userID = userID;
    });

    socket.on("connect_error", (err) => {
      if (err.message === "invalid username") {
        this.usernameAlreadySelected = false;
      }
    });
  },
  unmounted() {
    socket.off("connect_error");
  },
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
