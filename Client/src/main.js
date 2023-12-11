import "./assets/css/global.css";
import "vuetify/styles"; // Vuetify 스타일 추가
import "bootstrap/dist/css/bootstrap.min.css";

import { createApp } from "vue";
import App from "./App.vue";
import router from "./router";
import { createVuetify } from "vuetify";
import { createPinia } from "pinia";

const vuetify = createVuetify({
  theme: {
    defaultTheme: "light",
  },
});

createApp(App).use(vuetify).use(router).use(createPinia()).mount("#app");
