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
            var tempList = this.messages;
            this.socket.on('message', function(msg) {
                tempList.push(msg);
                this.messages = tempList;
            });
        },
        methods: {
            setUsername(){
                this.socket.emit('join', this.username);
                this.username = '';
            },
            sendMessage() { 
                this.socket.emit('message', this.message);
                this.message = '';
            },
        },
    }
</script>

<style scoped>

</style>
