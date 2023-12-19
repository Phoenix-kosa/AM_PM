<template>
  <div>
    <div @click="closeModalFunction" class="black_bg"></div>
    <div class="alert">
      <p class="alert_title">Notice</p>
      <div class="top_btn_container">
        <div>
          <p class="modifiers_text">Modifiers : {{ DetailModalDTO.userId }}</p>
        </div>
        <div>
          <button class="close_btn bg_yellow">수정</button>
          <button @click="deleteNotiHandler" class="close_btn bg_red">
            삭제
          </button>
        </div>
      </div>
      <div class="input_container">
        <label for="title">Title</label>
        <p class="mb20" name="title" id="title">
          {{ DetailModalDTO.title }}
        </p>
        <label for="content">Content</label>
        <p name="content" id="content">
          {{ DetailModalDTO.content }}
        </p>
      </div>
      <button @click="closeModalFunction" class="close_btn">닫기</button>
    </div>
  </div>
</template>
<script setup>
import { defineProps, ref } from "vue";
import { addNoti, deleteNoti } from "../api/common";

const props = defineProps([
  "closeDetailModal",
  "projectId",
  "getNotiList",
  "DetailModalDTO",
]);

const formData = ref({
  projectId: "1",
  content: "",
  title: "",
});

const closeModalFunction = () => {
  // 프로젝트 아이디 수정 필요
  const a = formData.value.projectId;
  props.getNotiList(a);
  props.closeDetailModal();
};

const deleteNotiHandler = () => {
  console.log(props.DetailModalDTO.id);
  deleteNoti(props.DetailModalDTO.id).then((res) => {
    alert("해당 Notice가 삭제 되었습니다.");
    closeModalFunction();
  });
};

const addNotiFunction = () => {
  const data = {
    projectId: formData.value.projectId,
    content: formData.value.content,
    title: formData.value.title,
  };

  if (validation(data)) {
    addNoti(data).then((res) => {
      alert(res.data);
      props.getNotiList(data.projectId);
      props.closeModal();
    });
  } else alert("Notice 생성 실패");
};

const validation = (data) => {
  if (
    data.content.trim() === "" ||
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
