import './assets/main.css'
import 'vuetify/styles' // Vuetify 스타일 추가

import { createApp } from 'vue'
import App from './App.vue'
import router from "./router";
import { createVuetify } from 'vuetify'

import "bootstrap/dist/css/bootstrap.min.css"
import "bootstrap/dist/js/bootstrap.bundle.min.js"

const vuetify = createVuetify({
  theme: {
    defaultTheme: 'light'
  }
})

createApp(App).use(vuetify).use(router).mount('#app')
