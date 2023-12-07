import { createRouter, createWebHistory } from "vue-router";

import Excel from "@/components/Excel/main.vue";
import DevPage from "@/pages/DevPage.vue";
import TeamChat from "@/components/TeamChat.vue";

const index = createRouter({
  history: createWebHistory(),
  routes: [
    { path: "/", component: DevPage },
    { path: "/excel", component: Excel },
    { path: "/team-chat", component: TeamChat },
  ],
});

export default index;
