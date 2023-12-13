<template>
  <div class="mypage_container">
    <div class="mypage_wrapper">
      <img src="../assets/images/mainPage/santa_flying.png" alt="" />
      <button class="profile_container">프로필 이미지 변경</button>
      <form action="post" class="form_container">
        <div class="input_container">
          <label>닉네임</label>
          <input type="text" />
        </div>
        <div class="input_container">
          <label>이메일</label>
          <input type="email" />
        </div>
        <div class="input_container">
          <label>비밀번호</label>
          <input type="password" />
        </div>
        <div class="input_container">
          <label>비밀번호 확인</label>
          <input type="password" />
        </div>
      </form>
    </div>
  </div>
</template>

<script setup></script>

<style scoped>
@import "../assets/css/mypage.css";
</style>
