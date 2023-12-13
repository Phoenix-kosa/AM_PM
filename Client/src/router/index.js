import { createRouter, createWebHistory } from "vue-router";


import Excel from "@/components/Excel/main.vue";
import DevPage from "@/pages/DevPage.vue";
import MainPage from "@/pages/MainPage.vue";
import TeamChat from "@/components/TeamChat.vue";
import MyPage from "@/pages/MyPage.vue";
import MainLayout from "../layouts/MainLayout.vue";
import TeamChat2 from "@/components/TeamChat2.vue";
import Login from '@/components/Auth/login.vue'
import Register from '@/components/Auth/register.vue'
import TestToken from '@/components/Auth/testtoken.vue'
import ProjectPlanPage from "@/pages/ProjectPlanPage.vue"
import QnaPage from "@/pages/QnaPage.vue"
import QnaDetail from "@/pages/QnA/QnaDetail.vue"
import QnaWrite from "@/pages/QnA/QnaWrite.vue"


const index = createRouter({
  history: createWebHistory(),
  routes: [
    { path: "/", component: MainPage },
    {
      path: "/",
      component: MainLayout,
      children: [
        { path: "dev", component: DevPage },
        { path: "mypage", component: MyPage },
        { path: 'excel', component: Excel },
        { path: 'login', component: Login },
        { path: 'register', component: Register },
        { path: 'testtoken', component: TestToken },
        { path: 'erd', component: ProjectPlanPage, props: { pageType: 'ERD' } },
        { path: 'usecase', component: ProjectPlanPage, props: { pageType: 'USECASE' } },
        { path: 'ui', component: ProjectPlanPage, props: { pageType: 'UI' } },
        { path: "question", component: QnaPage},
        { path: "detail", component: QnaDetail},
        { path: "team-chat", component: TeamChat},
        { path: "write", component: QnaWrite},
      ],
    },
  ],
});

export default index;
