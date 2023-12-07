import { createRouter, createWebHistory } from "vue-router";

import DevPage from "@/pages/DevPage.vue";

const router = createRouter({
  history: createWebHistory(),
  routes: [{ path: "/", component: DevPage }],
});

export default router;
