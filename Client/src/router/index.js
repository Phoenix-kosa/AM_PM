import { createRouter, createWebHistory } from "vue-router";

import Excel from "@/components/Excel/main.vue";
import DevPage from "@/pages/DevPage.vue";
import MainPage from "@/pages/MainPage.vue";
import TeamChat from "@/pages/TeamChat.vue";
import Chat from "@/pages/Chat.vue";
import MyPage from "@/pages/MyPage.vue";
import MainLayout from "../layouts/MainLayout.vue";
import Login from '@/pages/Auth/login.vue'
import Register from "@/pages/Auth/register.vue"
import TestToken from '@/pages/Auth/testtoken.vue'
import Redirect from "@/pages/Auth/redirect.vue"
// import ProjectPlanPage from "@/pages/ProjectPlanPage.vue"
import QnaPage from "@/pages/QnaPage.vue"
import ProjectPlan from "@/components/ProjectPlan.vue"; 
import ProjectList from "@/pages/ProjectList.vue";
import CreateProject from "@/pages/CreateProject.vue";
import ModifyProject from "@/pages/ModifyProject.vue";
import AddMember from "@/pages/AddMember.vue";
import QnaDetail from "@/components/QnA/QnaDetail.vue"
import QnaWrite from "@/components/QnA/QnaWrite.vue"
import QnaAnswer from "@/components/QnA/QnaAnswer.vue"
import MemberList from "@/pages/MemberList.vue";
import LeaderChange from "@/pages/LeaderChange.vue";
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

        { path: 'testtoken', component: TestToken },
        { path: '/erd', component: ProjectPlan, props: { pageType: 'ERD', pageId: 1 } },
        { path: '/usecase', component: ProjectPlan, props: { pageType: 'USECASE', pageId: 2 } },
        { path: '/ui', component: ProjectPlan, props: { pageType: 'UI', pageId: 3 } },
        { path: "question", component: QnaPage},
        { path: "detail", component: QnaDetail},
        { path: "team-chat", component: TeamChat},
        { path: "/chat",
          component: Chat,
          name: "Chat"},
        { path: "project-list", component: ProjectList },
        { path: "create-project", component: CreateProject },
        { path: "modify-project", component: ModifyProject },
        { path: "add-member", component: AddMember },
        { path: "write", component: QnaWrite},
        { path: "answer", component: QnaAnswer},
        { path: "member-list", component: MemberList},
        { path: "leader-change", component: LeaderChange},
      ],
    },
    { path: '/login', component: Login },
    { path: '/register', component: Register },
    { path: '/oauth2/redirect', component: Redirect}
  ],
});

export default index;