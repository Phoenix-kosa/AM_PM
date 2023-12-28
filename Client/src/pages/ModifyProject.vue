<template>
  <div class="container">
    <div class="inputBox">
      <input type="text" :placeholder="projectTitle" v-model="title">
      <textarea :placeholder="projectContent" v-model="content"></textarea>
    </div>
    <div class="dateBox">
      <label>시작 날짜<input type="date" v-model="startDate"></label>
      <label>종료 날짜<input type="date" v-model="endDate"></label>
    </div>
    <div class="buttonBox">
      <button @click="modifyProject">수정</button>
    </div>
  </div>
</template>
<script setup>
import axios from 'axios';
import { ref, onMounted } from 'vue';
import { useRouter } from 'vue-router';
import { expireToken } from "../api/config";
import Swal from 'sweetalert2';

const router = useRouter();
const projectId = sessionStorage.getItem("projectId");
const projectTitle = ref(null);
const projectContent = ref(null);

const title = ref(null);
const content = ref(null);
const startDate = ref(null);
const endDate = ref(null);

onMounted(() => {
  window.scrollTo(0, 0);
  const projectId = sessionStorage.getItem("projectId");
  if (projectId === null) {
    Swal.fire({
      icon: 'warning',
      title: '프로젝트 선택 안 됨',
      text: '프로젝트를 선택하여주세요.',
    });
    router.push("/project-list");
  }
});

function modifyProject() {
  var requestProject = {
    title:title.value,
    content:content.value,
    startDate:startDate.value,
    endDate:endDate.value
  }
  axios.put(`http://localhost:8090/api/project/` + projectId, 
  requestProject,
  {
    headers: { 
        "Authorization" : sessionStorage.getItem("access-token") 
    },
  })
  .then((response) => {
    if(response.status == 205) {
      Swal.fire({
        icon: 'success',
        title: '수정이 완료되었습니다.',
        text: '프로젝트가 성공적으로 수정되었습니다.',
      });
      router.push("project-list");
    }
    else {
      Swal.fire({
        icon: 'warning',
        title: '오류 발생',
        text: '권한이 없거나 수정 중 오류가 발생했습니다.',
      });
    }
  })
  .catch((err) => {
    console.log(err)
    expireToken(err, modifyProject);
  });
}
function loadData() {
  axios.get(`http://localhost:8090/api/project/` + projectId, 
    {
      headers: { 
          "Authorization" : sessionStorage.getItem("access-token") 
      },
    })
    .then((response) => {
      projectTitle.value = response.data.title;
      projectContent.value = response.data.content;
      startDate.value = response.data.startDate;
      endDate.value = response.data.endDate;
    })
    .catch((err) => {
      console.log(err)
      expireToken(err, loadData);
    });
}
loadData();
</script>
<style scoped>
@import '@/assets/css/modifyProject.css';
</style>