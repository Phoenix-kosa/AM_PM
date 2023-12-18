<template>
  <v-card>
    <v-layout>
      <v-navigation-drawer id="sidebar_container" permanent>
        <p class="p">기획</p>
        <v-list density="compact" nav>
          <v-list-item
            prepend-icon="mdi-home-city"
            title="요구사항 명세서"
            value="요구사항 명세서"
            style="color: white"
          ></v-list-item>
          <v-list-item
            prepend-icon="mdi-account"
            title="Usecase"
            to="/usecase"
            style="color: white"
          ></v-list-item>
          <v-list-item
            prepend-icon="mdi-account-group-outline"
            title="ERD"
            to="/erd"
            style="color: white"
          ></v-list-item>
          <v-list-item
            prepend-icon="mdi-account-group-outline"
            title="페이지 명세서"
            to="/ui"
            style="color: white"
          ></v-list-item>
        </v-list>
        <v-divider
          style="border-top-color: white; border-top-width: 2px"
        ></v-divider>
        
        
        <p class="p">개발</p>
        <v-list density="compact" nav>
          <v-list-item
            prepend-icon="mdi-home-city"
            title="공지사항/간트차트"
            to="dev"
            style="color: white"
          ></v-list-item>
          <v-list-item
            prepend-icon="mdi-account"
            title="채팅"
            to="team-chat"
            style="color: white"
          ></v-list-item>
        </v-list>
        <v-divider
          style="border-top-color: white; border-top-width: 2px"
        ></v-divider>

        <p class="p">멤버</p>
        <v-list density="compact" nav>
          <v-list-item
            prepend-icon="mdi-account"
            title="민재"
            value="민재"
            style="color: white"
          ></v-list-item>
          <v-list-item
            prepend-icon="mdi-account"
            title="승완"
            value="승완"
            style="color: white"
          ></v-list-item>
          <v-list-item
            prepend-icon="mdi-account"
            title="희지"
            value="희지"
            style="color: white"
          ></v-list-item>
          <v-list-item
            prepend-icon="mdi-account"
            title="인수"
            value="인수"
            style="color: white"
          ></v-list-item>
          <v-list-item
            prepend-icon="mdi-account"
            title="도회"
            value="도회"
            style="color: white"
          ></v-list-item>
          <v-divider
          style="border-top-color: white; border-top-width: 2px"
        ></v-divider>     
         </v-list>
        
        
        <v-list density="compact" nav>
        <p class="p">관리</p>
        <v-list-item
            prepend-icon="mdi-account"
            title="프로젝트 수정"
            style="color: white"
            to="/modify-project"
          ></v-list-item>
          <v-list-item
            prepend-icon="mdi-account"
            title="멤버 추가"
            style="color: white"
          ></v-list-item>
        </v-list>
        <v-divider
          style="border-top-color: white; border-top-width: 2px"
        ></v-divider>
        <p class="q">문의하기</p>
        <v-list density="compact" nav>
          
          </v-list>



        <v-list-item nav>
          <template v-slot:append>
            <v-btn variant="text" icon="mdi-chevron-left"></v-btn>
          </template>
        </v-list-item>
      </v-navigation-drawer>
    </v-layout>
  </v-card>
</template>
<script>
export default {
  data() {
    return {
      drawer: true,
      rail: true,
    };
  },
};
</script>
<style scoped>
@import "../assets/css/sidebar.css";
</style>

<script setup>
import { ref } from "vue";

const drawer = ref(true);
const rail = ref(true);
</script>
