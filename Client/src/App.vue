<template>
  <v-app>
    <v-navigation-drawer v-model="drawer" :rail="rail" permanent>
      <v-list-item>
        <v-list-item-avatar>
          <img src="https://randomuser.me/api/portraits/men/85.jpg">
        </v-list-item-avatar>
        <div>John Leider</div> 
        <v-list-item-action>
          <v-btn icon @click.stop="rail = !rail">
            <v-icon>mdi-chevron-left</v-icon>
          </v-btn>
        </v-list-item-action>
      </v-list-item>

      <v-divider></v-divider>

      <v-list dense nav>
        <v-list-item v-for="menu in menus" :key="menu.title" :to="menu.to">
          <v-list-item-icon>
            <v-icon>{{ menu.icon }}</v-icon>
          </v-list-item-icon>
          <div>{{ menu.title }}</div>
        </v-list-item>
      </v-list>
    </v-navigation-drawer>

    <v-main>
      <router-view></router-view>
    </v-main>
  </v-app>
</template>


<script setup>
import { ref } from 'vue';

const drawer = ref(true);
const rail = ref(false);

const menus = ref([
  {
    title: '홈',
    icon: 'mdi-home',
    to: '/home',
  },
  {
    title: '엑셀',
    icon: 'mdi-file-excel',
    to: '/excel',
  },
]);
</script>
