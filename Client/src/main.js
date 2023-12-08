import './assets/main.css'
import 'vuetify/styles' 


import { createApp } from 'vue'
import App from './App.vue'
import router from "./router";
import { createVuetify } from 'vuetify'

const vuetify = createVuetify({
  theme: {
    defaultTheme: 'light'
  }
})

createApp(App).use(vuetify).use(router).mount('#app')
