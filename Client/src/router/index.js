import { createRouter, createWebHistory } from "vue-router";

import Excel from "@/components/Excel/main.vue";
import DevPage from "@/pages/DevPage.vue";
import TeamChat from "@/components/TeamChat.vue";
import MyPage from "@/pages/MyPage.vue";
import MainLayout from "../layouts/MainLayout.vue";
import TeamChat2 from "@/components/TeamChat2.vue";

const index = createRouter({
  history: createWebHistory(),
  routes: [
    {
      path: "/",
      component: MainLayout,
      children: [
        { path: "dev", component: DevPage },
        { path: "mypage", component: MyPage },
      ],
    },
    { path: "/excel", component: Excel },
    { path: "/team-chat", component: TeamChat },
    { path: "/team-chat2", component: TeamChat2 },
  ],
});

export default index;
