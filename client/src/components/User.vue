
<template>
  <div>
    <div v-if="state == 0">
        <form @submit.prevent="createUser">
            <input placeholder="Name..." v-model="name" type="text">
            <input placeholder="Email..." v-model="email" type="text">
            <input type="submit" value="Submit">
        </form>
    </div>
    <div v-if="state == 1">
        <div class="user" v-for="(user, i) in users" :key="i">
            <h3>{{user.name}}</h3>
            <p>{{user.email}}</p>
            <ul>
            <p>Friends:</p>
               <li v-for="(friend, i) in user.friendIds" :key="i">
                {{isFriend(friend).name}}
                </li> 
            </ul>
            <br>
        </div>
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

            id: "",
            name: "",
            email: "",   
            friendsIds: [],  
            
            users: [],
        }
    },
    methods: {
        async createUser(){
            const json = JSON.stringify({name: this.name,email: this.email,friendIds: this.friendsIds})
            const res = await axios.post(this.baseUrl+"/create", json,  {
                headers: {
                    'Content-Type': 'application/json'
                    }
                }); 
            if (res.status == 200){
                this.id = res.data.split("id:")[1]
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
        isFriend(friendID){
            return this.users.find(x => x.id === friendID)
        }
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