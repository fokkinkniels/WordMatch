import { createApp } from 'vue'
import App from './App.vue'
import axios from 'axios'
import vue3GoogleLogin from 'vue3-google-login'

const app = createApp(App)

//global variables
app.config.globalProperties.$axios = axios
app.config.globalProperties.$id = ""


axios.defaults.baseURL = process.env.VUE_APP_BASE_URL_API
axios.defaults.headers.post['Content-Type'] = 'application/json';
axios.defaults.headers.put['Content-Type'] = 'application/json';

app.use(vue3GoogleLogin, {
  clientId: '937641477436-alps1oc5b96kplmck0gr3v2ts7tu8qao.apps.googleusercontent.com'
})


app.mount('#app')