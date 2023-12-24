<template>
  <div class="container">
    <div v-if="projectData != null">
      <div v-if="isMember" class="title">
        <span>프로젝트 </span>
        <span class="projectTitle">"{{ projectData.title }}"</span>
        <span>의 멤버</span>
      </div>
      <div v-else="isMember" class="title">
        <span>탈퇴한 멤버</span>
      </div>
    </div>
    <div v-if="targetData != null">
      <div class="imgDiv">
        <img v-if="targetData.profileImg" :src="targetData.profileImg">
      </div>
        <div class="textDiv">
        <span class="nickname">{{ targetData.nickname }}</span>
        <span class="email">{{ targetData.email }}</span>
      </div>
    </div>
    <div v-if="isMember" class="buttonContainer">
      <button id="chatButton">1:1 채팅</button>
    </div>
  </div>
</template>
<script setup>
import axios from 'axios';
import { ref } from 'vue';
import { useRoute } from 'vue-router';
const route = useRoute();
const target = route.query.user;
const targetData = ref(null);
const projectId = sessionStorage.getItem("projectId");
const projectData = ref(null);
const isMember = ref(false);

onMounted(() => {
  window.scrollTo(0, 0);
  const projectId = sessionStorage.getItem("projectId");
  if (projectId === null) {
    alert("프로젝트를 선택하세요.");
    router.push("/project-list");
  }
});

loadData();

function loadData() {
  axios.get('http://localhost:8090/api/user/' + target, {
    headers: { 
        "Authorization" : sessionStorage.getItem("access-token") 
    }
  })
  .then((response) => {
    targetData.value = response.data;
  })
  .catch((err) => {
    expireToken(err, loadData);
    } 
  });

  axios.get('http://localhost:8090/api/project/' + projectId, {
    headers: { 
        "Authorization" : sessionStorage.getItem("access-token") 
    }
  })
  .then((response) => {
    projectData.value = response.data;
  })
  .catch((err) => {
    expireToken(err, loadData);
    } 
  });

  axios.get('http://localhost:8090/api/members/' + projectId, {
    headers: { 
        "Authorization" : sessionStorage.getItem("access-token") 
    }
  })
  .then((response) => {
    for(let member of response.data) {
      if(member.userId == target) {
        isMember.value = true;
        break;
      }
    }
  })
  .catch((err) => {
    expireToken(err, loadData);
    } 
  });
}
</script>
<style scoped>
@import '@/assets/css/modifyProject.css';
</style>