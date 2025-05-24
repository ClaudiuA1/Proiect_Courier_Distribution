import { createApp } from 'vue'
import App from './App.vue'
import vuetify from './plugins/vuetify'
import { loadFonts } from './plugins/webfontloader'
import axios from 'axios'

loadFonts()
// axios.config.js (importă în main.js sau la începutul aplicaţiei)
// Cere browser-ului ă accepte cookie-uri CORS
axios.defaults.withCredentials = true
// Baza API-ului
axios.defaults.baseURL = 'http://localhost:8080'

createApp(App)
  .use(vuetify)
  .mount('#app')
