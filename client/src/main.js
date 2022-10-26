import { createApp } from 'vue'
import App from './App.vue'
import axios from 'axios'



const app = createApp(App)

//global variables
app.config.globalProperties.$axios = axios
app.config.globalProperties.$id = ""


axios.defaults.baseURL = process.env.VUE_APP_BASE_URL_API
axios.defaults.headers.post['Content-Type'] = 'application/json';


app.mount('#app')