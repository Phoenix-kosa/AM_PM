import { createRouter, createWebHistory } from "vue-router";

import Excel from '@/components/Excel/main.vue'

const index = createRouter({
  history: createWebHistory(),
  routes: [
    { path: '/excel', component: Excel, },
  ],
});

export default index;










