import { createRouter, createWebHistory } from "vue-router";

import Excel from "@/components/Excel/main.vue";
import DevPage from "@/pages/DevPage.vue";

const index = createRouter({
  history: createWebHistory(),
  routes: [
    { path: "/", component: DevPage },
    { path: "/excel", component: Excel },
  ],
});

export default index;
