<template>
  <div class="container">
    <div class="inputBox">
      <input type="text" placeholder="제목" v-model="title">
      <textarea placeholder="설명" v-model="content"></textarea>
    </div>
    <div class="dateBox">
      <label>시작 날짜<input type="date" v-model="startDate"></label>
      <label>종료 날짜<input type="date" v-model="endDate"></label>
    </div>
    <div class="buttonBox">
      <button @click="createProject">프로젝트 생성</button>
      <button @click="$router.go(-1)">뒤로 가기</button>
    </div>
  </div>
</template>
<script setup>
import axios from 'axios';
import { ref } from 'vue';
import { useRouter } from 'vue-router';

const router = useRouter();
const title = ref(null);
const content = ref(null);
const startDate = ref(null);
const endDate = ref(null);
function createProject() {
  if(title.value == null) {
    window.alert("제목을 입력하세요.");
  }
  else if(startDate.value == null || endDate.value == null) {
    window.alert("날짜를 입력하세요.");
  }
  else {
    var requestProject = {
      title:title.value,
      content:content.value,
      startDate:startDate.value,
      endDate:endDate.value
    }
    axios.post(`http://localhost:8090/api/project`, 
    requestProject,
    {
      headers: { 
          "Authorization" : sessionStorage.getItem("access-token") 
      },
    })
    .then((response) => {
      if(response.status == 201) {
      alert("생성이 완료되었습니다.");
      router.push({path: "/project-list"});
      }
    })
    .catch((err) => {
      console.log(err)
      expireToken(err, createProject);
    });
  }
}
</script>
<style scoped>
@import "@/assets/css/createProject.css";
</style>