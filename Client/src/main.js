import "./assets/css/global.css";
import "vuetify/styles"; // Vuetify 스타일 추가
import "bootstrap/dist/css/bootstrap.min.css";

import { createApp } from "vue";
import App from "./App.vue";
import router from "./router";
import axios from 'axios'
import { createVuetify } from "vuetify";
import { createPinia } from "pinia";


const vuetify = createVuetify({
  theme: {
    defaultTheme: "light",
  },
});

const app = createApp(App)
app.config.globalProperties.$axios = axios;
app.config.globalProperties.$serverUrl = '//localhost:8090'
app.use(router).mount('#app')

// createApp(App).use(vuetify).use(router).use(createPinia()).mount("#app");
