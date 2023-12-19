<template>
  <div class="container">
    <div v-if="projectData != null">
      <div class="title">
        <span>프로젝트 </span>
        <span class="projectTitle">"{{ projectData.title }}"</span>
        <span>의 멤버</span>
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
    console.log(err)
    if(err.response.status == 401) {
      console.log("토큰 만료");

      axios.get("http://localhost:8090/api/rtoken", {
          headers: { 
              "RefreshToken" : sessionStorage.getItem("refresh-token"),
              "Authorization" : sessionStorage.getItem("access-token") }
          }).then(response => {
              console.log(response)
              if(response.status == 200){
                  console.log("토큰 재발급");
                  console.log(response.headers.authorization);
                  sessionStorage.setItem("access-token", response.headers.authorization);
              } else {
                  console.log("토큰 재발급 실패");
              }
          }).catch(error => {console.error(error);})
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
    console.log(err)
    if(err.response.status == 401) {
      console.log("토큰 만료");

      axios.get("http://localhost:8090/api/rtoken", {
          headers: { 
              "RefreshToken" : sessionStorage.getItem("refresh-token"),
              "Authorization" : sessionStorage.getItem("access-token") }
          }).then(response => {
              console.log(response)
              if(response.status == 200){
                  console.log("토큰 재발급");
                  console.log(response.headers.authorization);
                  sessionStorage.setItem("access-token", response.headers.authorization);
              } else {
                  console.log("토큰 재발급 실패");
              }
          }).catch(error => {console.error(error);})
    } 
  });
}
</script>
<style scoped>
.container {
  background-color: #d9d9d9;
  border-radius: 20px;
  padding: 10px;
  height: 600px;
  min-width: 500px;
}
.title {
  font-weight: bold;
  text-align: center;
  margin-top: 20px;
}
.projectTitle {
  color: #166adc;
}
.imgDiv {
  width: 200px;
  height: 200px;
  background-color: white;
  border-radius: 50%;
  margin: 20px auto;
}
img {
  width: 100%;
  height: 100%;
  border-radius: 50%;
}
.textDiv {
  display: flex;
  flex-direction: column;
  align-items: center;
}
.nickname {
  color: black;
  margin-bottom: 10px;
  font-weight: bold;
}
.email {
  color: black;
  margin-bottom: 10px;
}
</style>