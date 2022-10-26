<!-- <script>

import { decodeCredential } from 'vue3-google-login'
import { ref } from 'vue'

import { inject } from 'vue'

//Setup axios
import axios from 'axios'
axios.defaults.baseURL = process.env.VUE_APP_BASE_URL_API
axios.defaults.headers.post['Content-Type'] = 'application/json';

const callback = (response) => {

  const userData = decodeCredential(response.credential)
  const json = JSON.stringify(userData.email)
  const users = ref([])

  ;(async () => {
    const res = await axios.post("/login", json); 
    users.value = res.data;
    if(res.status === 200){
      console.log(users)
      const id = inject(res.data.id)
      console.log
    }
  })()

  return {
    users,
  }
}


</script>

<template>
  <GoogleLogin :callback="callback" prompt /> 
 auto-login
</template> -->