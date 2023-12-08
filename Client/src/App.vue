<template>
  <v-app>
    <v-navigation-drawer v-model="drawer" :rail="rail" permanent @click="rail = false">
      <!-- v-avatar로 변경된 부분 -->
      <v-list-item>
        <v-avatar>
          <img src="https://randomuser.me/api/portraits/men/85.jpg">
        </v-avatar>
        <div>John Leider</div>
        <v-list-item-action>
          <v-btn icon @click.stop="rail = !rail">
            <v-icon>mdi-chevron-left</v-icon>
          </v-btn>
        </v-list-item-action>
      </v-list-item>

      <v-divider></v-divider>

      <!-- 리스트 아이템들 -->
      <v-list dense nav>
        <v-list-item prepend-icon="mdi-home-city" title="Home" value="home"></v-list-item>
        <v-list-item prepend-icon="mdi-account" title="My Account" value="account"></v-list-item>
        <v-list-item prepend-icon="mdi-account-group-outline" title="Users" value="users"></v-list-item>
      </v-list>
    </v-navigation-drawer>

    <v-main style="height: 250px">
      <router-view></router-view>
    </v-main>
  </v-app>
</template>

<script setup>
import { ref } from 'vue';

const drawer = ref(true);
const rail = ref(true);
</script>





<!--
<template>
  <v-card>
    <v-layout>
      <v-navigation-drawer
        v-model="drawer"
        :rail="rail"
        permanent
        @click="rail = false"
      >
        <v-list-item
          prepend-avatar="https://randomuser.me/api/portraits/men/85.jpg"
          title="John Leider"
          nav
        >
          <template v-slot:append>
            <v-btn
              variant="text"
              icon="mdi-chevron-left"
              @click.stop="rail = !rail"
            ></v-btn>
          </template>
        </v-list-item>

        <v-divider></v-divider>

        <v-list density="compact" nav>
          <v-list-item prepend-icon="mdi-home-city" title="Home" value="home"></v-list-item>
          <v-list-item prepend-icon="mdi-account" title="My Account" value="account"></v-list-item>
          <v-list-item prepend-icon="mdi-account-group-outline" title="Users" value="users"></v-list-item>
        </v-list>
      </v-navigation-drawer>
      <v-main style="height: 250px"></v-main>
    </v-layout>
  </v-card>
</template>

<script setup>
  import { ref } from 'vue'

  const drawer = ref(true)
  const rail = ref(true)
</script>

<script>
  export default {
    data () {
      return {
        drawer: true,
        rail: true,
      }
    },
  }
</script>










<template>
  <v-app>
    <v-navigation-drawer v-model="drawer" :rail="rail" permanent @click="rail = false"
>
      <v-list-item>
        <v-list-item-avatar>                                              
          <img src="https://randomuser.me/api/portraits/men/85.jpg">
        </v-list-item-avatar>
        <div>John Leider</div>  
        <v-list-item-action>
          <v-btn icon @click.stop="rail = !rail">
            <v-btn>mdi-chevron-left</v-btn>
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

    <v-main style="height: 250px">
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
    
  },
]);
</script>
-->