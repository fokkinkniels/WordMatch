<template>
  <div>
    <div v-if="state == 0">
        <form @submit.prevent="createUser">
            <input placeholder="Name..." v-model="tmpName" type="text">
            <input placeholder="Email..." v-model="tmpEmail" type="text">
            <input type="submit" value="Submit">
        </form>
    </div>

    <div v-if="state == 1">
        <h1>My profile:</h1>
        <p>{{name}}</p>
        <p>{{email}}</p>
        <br>
        <p>My Friends:</p>

        <div class="friend" v-for="(friend, i) in myfriends" :key="i">
            <h3>{{friend.name}}</h3>
            <button><p>Invite</p></button>
            <button v-on:click="RemoveFriend(friend.id)">Remove Friend</button>
            <br>
        </div>
        <br>
        <br>
        <button v-on:click="state = 2">Add New Friend</button>
        <button v-on:click="state = 0">Add New User</button>
        <button v-on:click="state = 3">Remove Player</button>
    </div>

    <div v-if="state == 2">
        <div class="user" v-for="(user, i) in users" :key="i" >
            <h3>{{user.name}}</h3>
            <p>{{user.email}}</p>
            <button v-on:click="AddFriend(user.id)">Add Friend</button>
            <br>
        </div>
        <br>
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

</div>    
</template>

<script>
import axios from 'axios';

export default {
    
    data () {
        return {
            state: 1,
            data: "",
            baseUrl: "http://localhost:8093/api/player",

            id: "634001317da2a40207f13413",
            name: "",
            email: "",   
            friendsIds: [],  
            myfriends: [],

            tmpId: "",
            tmpName: "",
            tmpEmail: "",
            tmpFriendList: [],
            
            users: [],
        }
    },
    methods: {
        async createUser(){
            const json = JSON.stringify({name: this.tmpName,email: this.tmpEmail,friendIds: []})

            const res = await axios.post(this.baseUrl+"/create", json,  {
                headers: {
                    'Content-Type': 'application/json'
                    }
                }); 
            if (res.status == 200){
                this.state = 1;
                this.getAllUsers();
            }
        },
        async getAllUsers() {
            const res = await axios.get(this.baseUrl+"/all")
            if (res.status == 200){
                this.users = res.data;
            }
        },
        async getMyFriends(){
            const res = await axios.get(this.baseUrl+"/friends/"+this.id)
            if (res.status == 200){
                this.myfriends = res.data;
            }
        },
        async getProfile(id){
            const res = await axios.get(this.baseUrl+"/"+id)
            if (res.status == 200){
                this.tmpId = res.data.id;
                this.tmpName = res.data.name;
                this.tmpEmail = res.data.email;
                this.tmpFriendsIds = res.data.friendsIds;
            }
        },
        async AddFriend(id){
            const json = JSON.stringify([this.id, id])
            const res = await axios.put(this.baseUrl+"/friends/add", json,  {headers: {'Content-Type': 'application/json'}})
            if (res.status == 200){
                this.state = 1;
                this.getMyFriends();
            }
        },
        async RemoveFriend(id){
            const json = JSON.stringify([this.id, id])
            const res = await axios.put(this.baseUrl+"/friends/remove", json,  {headers: {'Content-Type': 'application/json'}})
            if (res.status == 200){
                this.state = 1;
                this.getMyFriends();
            }
        },
        async RemoveUser(id){
            if(this.id != id){
                await this.getProfile(id);
                const json = await JSON.stringify({id: this.tmpId,name: this.tmpName,email: this.tmpEmail,friendIds: this.tmpFriendList})
                const res = await axios.delete(this.baseUrl+"/delete", json,  {headers: {'Content-Type': 'application/json'}})
                if (res.status == 200){
                    this.state = 1;
                    this.getAllUsers();
                }
            }
        },

    },
    mounted () {
        var user = this.getProfile(this.id);
        this.name = user[1]
        this.email = user[2]
        this.friendsIds = user[3]

        this.getAllUsers();
        this.getMyFriends();
  }
}
</script>



<style>
*{
    text-align: left;
}
</style>