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
    import Socket from "./socket"
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
        },
        mounted () {
            Socket.on("message", data => {
                this.messages.push(data)
            });

            Socket.on("private message", ({ content, from }) => {
                if (this.id === from) {
                    this.messages.push({
                        content,
                        fromSelf: false,
                });
            }});
        },
        methods: {
            join(){
                Socket.emit("join", this.username)
            },
            leave(){
                Socket.emit("leave", this.username)
                this.state = 0;
            },
            setUsername() {
                if(this.username != ''){
                    Socket.emit("setUser", {'username': this.username, 'id': this.id})
                }
                this.join()
            },
            sendMessage(){
                Socket.emit("message", this.newMessage)
                this.newMessage = "";
            },
            onMessage(content) {
                if (this.selectedUser) {
                    Socket.emit("private message", {
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