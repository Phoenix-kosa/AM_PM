<template>
  <div>
    <div @click="closeModalFunction" class="black_bg"></div>
    <div class="alert">
      <p class="alert_title">Add Notice</p>
      <div class="input_container">
        <label for="title">Title</label>
        <input v-model="formData.title" name="title" id="title" type="text" />
        <label for="content">Content</label>
        <textarea
          v-model="formData.content"
          name="content"
          id="content"
          cols="30"
          rows="10"
        ></textarea>
      </div>
      <button @click="addNotiFunction" class="alert_btn">생성</button>
    </div>
  </div>
</template>
<script setup>
import { defineProps, ref } from "vue";
import { addNoti } from "../api/common";
import { expireToken } from "../api/config";

const props = defineProps(["closeModal", "projectId", "getNotiList"]);

const formData = ref({
  projectId: "1",
  content: "",
  title: "",
});

const closeModalFunction = () => {
  // 프로젝트 아이디 수정 필요
  const a = formData.value.projectId;
  props.getNotiList(a);
  props.closeModal();
};

const addNotiFunction = () => {
  const data = {
    projectId: formData.value.projectId,
    content: formData.value.content,
    title: formData.value.title,
  };

  if (validation(data)) {
    addNoti(data)
      .then((res) => {
        alert(res.data);
        props.getNotiList(data.projectId);
        props.closeModal();
      })
      .catch((err) => {
        expireToken(err, addNotiFunction);
      });
  } else alert("Notice 생성 실패");
};

const validation = (data) => {
  if (
    data.content.trim() === "" || // 수정: data.contnet → data.content
    data.projectId.trim() === "" ||
    data.title.trim() === ""
  ) {
    return false;
  } else return true;
};
</script>

<style scoped>
@import "../assets/css/notiModle.css";
</style>
