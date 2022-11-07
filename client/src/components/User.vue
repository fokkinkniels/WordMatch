<template>
  <div>
    <div v-if="state == -1">
        <div class="user" v-for="(user, i) in users" :key="i" >
            <h3>{{user.name}}</h3>
            <button v-on:click="getUser(user.id)">Login</button>
            <br>
        </div>
    </div>

    <div v-if="state == 0">
        <form @submit.prevent="createUser">
            <input placeholder="Name..." v-model="tmpName" type="text">
            <input placeholder="Email..." v-model="tmpEmail" type="text">
            <input type="submit" value="Submit">
        </form>
        <button v-on:click="state = 1">Back</button>
    </div>

    <div v-if="state == 1">
        <h2>My profile:</h2>
        <p>{{name}}</p>
        <p>{{email}}</p>
        <br>
        <h2>My Friends:</h2>

        <div class="friend" v-for="(friend, i) in myfriends" :key="i">
            <p>{{friend.name}}</p>
            <button>Invite</button>
            <button v-on:click="RemoveFriend(friend.id)">Remove Friend</button>
            <br>
        </div>
        <br>
        <br>
        <button v-on:click="state = 2">Add New Friend</button>
        <button v-on:click="state = 0">Add New User</button>
        <button v-on:click="state = 3">Remove Player</button>
        <br>
        <button v-on:click="state = -1">Logout</button>
    </div>


    <div v-if="state == 2">
        <div class="user" v-for="(user, i) in users" :key="i" >
            <h3>{{user.name}}</h3>
            <p>{{user.email}}</p>
            <button v-on:click="AddFriend(user.id)">Add Friend</button>
            <br>
        </div>
        <button v-on:click="state = 1">Back</button>
    </div>


    <div v-if="state == 3">
        <div class="user" v-for="(user, i) in users" :key="i" >
            <h3>{{user.name}}</h3>
            <p>{{user.email}}</p>
            <button v-on:click="RemoveUser(user.id)">Remove User</button>
            <br>
        </div>
            <br>
            <button v-on:click="state = 1">Back</button>
    </div>
    <br>
    <ChatBox ref="chatbox"/>

    </div>    
</template>

<script>
import ChatBox from './ChatBox'
export default {
    components: {
        ChatBox
    },
    data () {
        return {
            state: -1,

            id: "",
            name: "",
            email: "",   
            friendsIds: [],  
            myfriends: [],

            check: 0,
            tmpId: "",
            tmpName: "",
            tmpEmail: "",
            tmpFriendList: [],
            
            users: [],
            response: "",
        }
    },
    methods: {
        async createUser(){
            const json = JSON.stringify({name: this.tmpName,email: this.tmpEmail,friendIds: this.tmpFriendList})
            const res = await this.$axios.post("/create", json); 
            if (res.status == 200){
                this.state = 1;
                this.getAllUsers();
            }
        },
        async getAllUsers() {
            const res = await this.$axios.get("/all")
            if (res.status == 200){
                this.users = res.data;
            }
        },
        async getUser(id){
            const res = await this.$axios.get("/"+id)
            if (res.status == 200){
                this.id, this.$refs.chatbox.id  = res.data.id;
                this.name, this.$refs.chatbox.username = res.data.name;
                this.email = res.data.email;
                this.friendsIds = res.data.friendsIds;
                this.$refs.chatbox.setUsername()
                this.getMyFriends();
                this.state = 1
            }
        },
        async getMyFriends(){
            const res = await this.$axios.get("/friends/"+this.id)
            if (res.status == 200){
                this.myfriends = res.data;
            }
        },
        async AddFriend(id){
            const json = JSON.stringify([this.id, id])
            const res = await this.$axios.put("/friends/add", json,  {headers: {'Content-Type': 'application/json'}})
            if (res.status == 200){
                this.state = 1;
                this.getMyFriends();
            }
        },
        async RemoveFriend(id){
            const json = JSON.stringify([this.id, id])
            const res = await this.$axios.put("/friends/remove", json,  {headers: {'Content-Type': 'application/json'}})
            if (res.status == 200){
                this.state = 1;
                this.getMyFriends();
            }
        },
        async RemoveUser(id){
            if(this.id != id){
                const res = await this.$axios.delete("/delete/"+id)
                if (res.status == 200){
                    this.getAllUsers();
                    this.getMyFriends();
                }
            }
        },
    },
    beforeMount () {
    },
    mounted () {

        this.getAllUsers();
  }
}
</script>



<style>
*{
    text-align: left;
}
</style>