<template>
    <div id="app">
        <form @submit.prevent="setUsername">
            <input type="text" placeholder="Username..." v-model="username">
            <input type="submit" value="Join">
        </form>

        <ul id="chatBox">
            <li v-for="(message, i) in messages" :key="i">
                <b>{{ message.user }}:</b> {{ message.message }}
            </li>
        </ul>

        <form @submit.prevent="sendMessage">
            <input type="text" placeholder="Message" v-model="message">
            <input type="submit" value="Send">
        </form>

    </div>
</template>

<script>
    import io from "socket.io-client"; 
    export default {
        name: 'BlockGame',
        data () {
            return {
                username: '',
                message: "",
                messages: [],
                socket: {},
            }
        },
        created() {
            this.socket = io("http://localhost:3000"); 
        },
        mounted () {
        },
        methods: {
        },
    }
</script>

<style scoped>
</style>