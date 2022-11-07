<template>
    <div id="app">
        <div v-if="state == 0">
            <form @submit.prevent="setUsername">
                <input type="text" placeholder="Username..." v-model="username">
                <input type="submit" value="Join">
            </form>
        </div>

        <div v-if="state == 1">
            <ul id="chatBox">
                <li v-for="(message, i) in messages" :key="i">
                    <b>{{ message.user }}:</b> {{ message.message }}
                </li>
            </ul>

            <form @submit.prevent="sendMessage">
                <input type="text" placeholder="Message" v-model="newMessage">
                <input type="submit" value="Send">
            </form>

            <button v-on:click="leave()">Leave</button>
        </div>

    </div>
</template>

<script>
    import io from "socket.io-client"; 
    export default {
        name: 'BlockGame',
        data () {
            return {
                username: '',
                newMessage: "",
                messages: [],
                socket: {},
                state: 0,
            }
        },
        created() {
            this.socket = io("http://localhost:3000"); 
        },
        mounted () {
            this.socket.on("message", data => {
                this.messages.push(data)
            })
        },
        methods: {
            join(){
                this.socket.emit("join", this.username)
                this.state = 1;
            },
            leave(){
                this.socket.emit("leave", this.username)
                this.state = 0;
            },
            setUsername() {
                if(this.username != ''){
                    this.socket.emit("setUsername", this.username)
                }
                this.join()
            },
            sendMessage(){
                this.socket.emit("message", this.newMessage)
                this.newMessage = "";
            }
        },
    }
</script>

<style scoped>
</style>