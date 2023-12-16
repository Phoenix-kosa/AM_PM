<template>
  <div class="parent">
      <header class="child">
    <img
      class="main_logo_img"
      src="../assets/images/main_logo.png"
      alt="main_logo"
    />
    <div class="login_container">
      <p>노인수 님</p>
      <a href="">MyPage</a>
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
const router = useRouter();

const logout = async () => {
  axios.delete("http://localhost:8090/api/auth", {
      headers: { 
            "Authorization" : sessionStorage.getItem("access-token") }
        })
    .then(response => {
        if (response.status == 200) {
            // 로그아웃 성공시 login 페이지로
            sessionStorage.removeItem("access-token");
            sessionStorage.removeItem("refresh-token");
            alert("로그아웃 되었습니다.");
            router.push("/login")
        } 
    })
    .catch(error => {
        if(error.response.status == 401) {
          console.log("토큰 만료");
          refresh();
          logout();
        } else {
          alert("로그아웃 실패");
          console.error(error);
          console.log(error.response.status)
        }
    });
}
</script>
<style scoped>
@import "../assets/css/header.css";
</style>
