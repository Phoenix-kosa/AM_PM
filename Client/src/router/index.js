import { createRouter, createWebHistory } from "vue-router";

import Excel from "@/components/Excel/main.vue";
import DevPage from "@/pages/DevPage.vue";
import MyPage from "@/pages/MyPage.vue";
import MainLayout from "../layouts/MainLayout.vue";
import ProjectPlanPage from "@/pages/ProjectPlanPage.vue"

const index = createRouter({
  history: createWebHistory(),
  routes: [
    {
      path: "/",
      component: MainLayout,
      children: [
        { path: "dev", component: DevPage },
        { path: "mypage", component: MyPage },
        { path: "/excel", component: Excel },
        { path: '/erd', component: ProjectPlanPage, props: { pageType: 'ERD' } },
        { path: '/usecase', component: ProjectPlanPage, props: { pageType: 'USECASE' } },
        { path: '/ui', component: ProjectPlanPage, props: { pageType: 'UI' } },

      ],
    },
  ],
});

export default index;
