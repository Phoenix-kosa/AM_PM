import { createRouter, createWebHistory } from "vue-router";

import Excel from "@/components/Excel/main.vue";
import DevPage from "@/pages/DevPage.vue";
import MyPage from "@/pages/MyPage.vue";
import MainLayout from "../layouts/MainLayout.vue";
import ProjectPlan from "@/components/ProjectPlan.vue"; 

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
        { path: '/erd', component: ProjectPlan, props: { pageType: 'ERD', pageId: 1 } },
        { path: '/usecase', component: ProjectPlan, props: { pageType: 'USECASE', pageId: 2 } },
        { path: '/ui', component: ProjectPlan, props: { pageType: 'UI', pageId: 3 } },

      ],
    },
  ],
});

export default index;
