import './assets/css/global.css';
import 'vuetify/styles';
import "bootstrap/dist/css/bootstrap.min.css";
import * as components from "vuetify/components";
import * as directives from "vuetify/directives";

import { fa } from "vuetify/iconsets/fa";
import { aliases, mdi } from "vuetify/lib/iconsets/mdi";
import { createApp } from 'vue';
import App from './App.vue';
import router from "./router";
import { createVuetify } from 'vuetify';
import {createPinia} from "pinia";

import "bootstrap/dist/css/bootstrap.min.css"
import "bootstrap/dist/js/bootstrap.bundle.min.js"

const vuetify = createVuetify({
  theme: {
    defaultTheme: 'light'
  },
  icons: {
    defaultSet: "mdi",
    aliases,
    sets: {
      mdi,
      fa,
    },
  },
  components,
  directives,
});

createApp(App).use(vuetify).use(router).use(createPinia()).mount("#app");