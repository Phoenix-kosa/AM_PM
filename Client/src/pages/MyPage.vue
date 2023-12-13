<template>
  <div class="mypage_container">
    <div class="mypage_wrapper">
      <img src="../assets/images/mainPage/santa_flying.png" alt="" />
      <button class="profile_btn">프로필 이미지 변경</button>
      <form @submit.prevent="submitForm" class="form_container">
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
        <button class="edit_btn">수정</button>
      </form>
    </div>
  </div>
</template>

<script setup>
import { onMounted } from "vue";
import { myInfoReq } from "../api/common";

onMounted(() => {
  console.log("Awdw");
});

const submitForm = () => {
  myInfoReq()
    .then((res) => {
      console.log("성공");
    })
    .catch((error) => {});
};
</script>

<style scoped>
@import "../assets/css/mypage.css";
</style>
