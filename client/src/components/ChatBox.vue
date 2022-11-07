<template>
    <div id="app" v-if="enabled == true">
        <div v-if="state == 0">
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
                enabled: false,
                username: "",
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
            });
        },
        methods: {
            join(){
                this.socket.emit("join", this.username)
            },
            leave(){
                this.socket.emit("leave", this.username)
                this.state = 0;
            },
            setUsername() {
                console.log(this.username + "test")
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