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
                id: "",
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

            this.socket.on("private message", ({ content, from }) => {
                if (this.id === from) {
                    this.messages.push({
                        content,
                        fromSelf: false,
                });
            }});
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
                if(this.username != ''){
                    this.socket.emit("setUser", {'username': this.username, 'id': this.id})
                }
                this.join()
            },
            sendMessage(){
                this.socket.emit("message", this.newMessage)
                this.newMessage = "";
            },
            onMessage(content) {
                if (this.selectedUser) {
                    this.socket.emit("private message", {
                    content,
                    to: this.id,
                });
                    this.selectedUser.messages.push({
                    content,
                    fromSelf: true,
                });
                }
            },
        },
    }
</script>

<style scoped>
</style>