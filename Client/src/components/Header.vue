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
        <p>Log out</p>
      </div>
    </header>
  </div>
</template>

<script setup>
import { getMyInfoReq } from "../api/common";
import { expireToken } from "../api/config";
import { ref, onMounted } from "vue";

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
