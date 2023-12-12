import { createRouter, createWebHistory } from "vue-router";

import Excel from "@/components/Excel/main.vue";
import DevPage from "@/pages/DevPage.vue";
import MyPage from "@/pages/MyPage.vue";
import MainLayout from "../layouts/MainLayout.vue";
import QnaPage from "@/pages/QnaPage.vue";
import QnaDetail from "@/pages/QnA/QnaDetail.vue"

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
    { path: "/question", component: QnaPage},
    { path: "/detail", component: QnaDetail},
  ],
});

export default index;
