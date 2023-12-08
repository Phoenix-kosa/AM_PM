import { createRouter, createWebHistory } from "vue-router";

import Excel from '@/components/Excel/main.vue'
import Login from '@/components/Auth/login.vue'
import Register from '@/components/Auth/register.vue'
import TestToken from '@/components/Auth/testtoken.vue'

const index = createRouter({
  history: createWebHistory(),
  routes: [
    { path: '/excel', component: Excel },
    { path: '/login', component: Login },
    { path: '/register', component: Register },
    { path: '/testtoken', component: TestToken },
  ],
});

export default index;










