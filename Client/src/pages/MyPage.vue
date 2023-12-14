<template>
  <div class="mypage_container">
    <div class="mypage_wrapper">
      <img src="../assets/images/mainPage/santa_flying.png" alt="" />
      <button class="profile_btn">프로필 이미지 변경</button>
      <form @submit.prevent="submitForm" class="form_container">
        <div class="input_container">
          <label for="nickname">닉네임</label>
          <input
            v-model="editMyInfo.nickname"
            id="nickname"
            name="nickname"
            type="text"
          />
        </div>
        <div class="input_container">
          <label for="email">이메일</label>
          <input
            v-model="editMyInfo.email"
            id="email"
            name="email"
            type="email"
          />
        </div>
        <div class="input_container">
          <label for="password">비밀번호</label>
          <input
            v-model="editMyInfo.password"
            id="password"
            name="password"
            type="password"
          />
        </div>
        <div class="input_container">
          <label for="confirmPassword">비밀번호 확인</label>
          <input
            v-model="editMyInfo.confirmPassword"
            id="confirmPassword"
            name="confirmPassword"
            type="password"
          />
        </div>
        <button class="edit_btn">수정</button>
      </form>
    </div>
  </div>
</template>

<script setup>
import { onMounted, ref } from "vue";
import { getMyInfoReq, editMyInfoReq } from "../api/common";
const editMyInfo = ref({
  nickname: "",
  email: "",
  profilImg: "",
  password: "",
  confirmPassword: "",
});

onMounted(() => {
  window.scrollTo(0, 0);
  getMyInfo();
});

const getMyInfo = () => {
  getMyInfoReq()
    .then((res) => res.data)
    .then((data) => {
      console.log(data);
      const { nickname, email, profilImg } = data;
      editMyInfo.value = {
        nickname,
        email,
        profilImg,
        password: "",
        confirmPassword: "",
      };
    })
    .catch((error) => console.log(error));
};

const submitForm = () => {
  if (validateForm()) {
    editMyInfoReq()
      .then((res) => {
        console.log("성공");
        // router.push("/login");
      })
      .catch((error) => {
        window.scrollTo(0, 0);
        console.log(error);
      });
  }
};

const validateForm = () => {
  return true;
};
</script>

<style scoped>
@import "../assets/css/mypage.css";
</style>
