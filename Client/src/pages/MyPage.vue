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
import { expireToken } from "../api/config";

const editMyInfo = ref({
  nickname: "",
  email: "",
  profilImg: "dvvsd",
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
      const { nickname, email, profilImg } = data;
      editMyInfo.value = {
        nickname,
        email,
        profilImg,
        password: "",
        confirmPassword: "",
      };
    })
    .catch((error) => {
      expireToken(error, getMyInfo);
      console.log(error);
    });
};

const submitForm = () => {
  if (validateForm()) {
    editMyInfoReq(editMyInfo.value)
      .then((res) => {
        console.log("성공");
        console.log(res.data);
        alert("회원정보 수정 완료");
        // router.push("/login");
      })
      .catch((error) => {
        expireToken(error, submitForm);
        window.scrollTo(0, 0);
        alert("회원정보 수정 실패!");
        console.log(error);
      });
  } else {
    alert("회원정보 수정 실패!");
  }
};

const validateForm = () => {
  const { nickname, email, password, confirmPassword } = editMyInfo.value;

  if (password !== confirmPassword) return false;
  if (
    nickname.trim() === "" ||
    email.trim() === "" ||
    password.trim() === "" ||
    confirmPassword.trim() === ""
  ) {
    return false;
  } else return true;
};
</script>

<style scoped>
@import "../assets/css/mypage.css";
</style>
