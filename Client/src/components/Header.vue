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
        <router-link to="/mypage">
          <p>MyPage</p>
        </router-link>
        <p>Log out</p>
      </div>
    </header>
  </div>
</template>

<script setup></script>
<style scoped>
@import "../assets/css/header.css";
</style>
