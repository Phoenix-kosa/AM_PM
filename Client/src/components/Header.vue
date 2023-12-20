<template>
  <div class="parent">
    <header class="child">
      <img
        class="main_logo_img"
        src="../assets/images/main_logo.png"
        alt="main_logo"
      />
      <div class="login_container">
        <p>{{ name }}</p>
        <router-link to="/mypage">
          <p>MyPage</p>
        </router-link>
        <a v-on:click="logout">Log out</a>
      </div>
    </header>
  </div>
</template>

<script setup>

import axios from 'axios';
import { authApi } from "@/api/config";
import { useRouter } from 'vue-router';
import { refresh } from "@/api/refresh";
import { getMyInfoReq } from "../api/common";
import { expireToken } from "../api/config";
import { ref, onMounted } from "vue";

const router = useRouter();

const logout = () => {
  axios.delete("http://localhost:8090/api/auth", {
      headers: { 
            "Authorization" : sessionStorage.getItem("access-token") }
        })
    .then(response => {
        if (response.status == 200) {
            // 로그아웃 성공시 login 페이지로
            sessionStorage.removeItem("access-token");
            sessionStorage.removeItem("refresh-token");
            sessionStorage.removeItem("projectId");
            alert("로그아웃 되었습니다.");
            router.push("/login")
        } 
    })
    .catch(error => {
      expireToken(error, logout)
    });
}

const name = ref("");
onMounted(() => {
  getName();
});

const getName = () => {
  getMyInfoReq()
    .then((res) => {
      return res.data;
    })
    .then((data) => {
      name.value = data.nickname;
      console.log(data);
    })
    .catch((err) => {
      expireToken(err, getName);
    });
};

</script>
<style scoped>
@import "../assets/css/header.css";
</style>
