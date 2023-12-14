<template>
  <div class="container">
    <div v-for="data in projectList">
      <div class="project" @click="go(data.id)">
        <span class="title">{{ data.title }}</span>
        <span class="content">{{ data.content }}</span>
        <span>{{ data.startDate }}</span>
        <span>{{ data.endDate }}</span>
      </div>
    </div>
    <div class="buttonBox">
      <button @click="moveToCreate">+새 프로젝트</button>
    </div>
  </div>
</template>
<script setup>
import axios from 'axios';
import { ref } from 'vue';

const projectList = ref([]);

function go(projectId) {
  console.log(projectId);
}

function moveToCreate() {
  location.href = "/create-project";
}

function loadData() {
  axios.get(`http://localhost:8090/api/project`, {
    headers: { 
        "Authorization" : sessionStorage.getItem("access-token") 
    }
  })
  .then((response) => {
    projectList.value = response.data;
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
loadData();
</script>
<style scoped>
.container {
  border-color: #166adc;
  border-radius: 20px;
  border-width: 1px;
  border-style: dashed;
  padding: 10px;
  height: 600px;
  min-width: 700px;
  overflow: auto;
}
.container::-webkit-scrollbar {
  width: 10px;
}
.container::-webkit-scrollbar-thumb {
  background-color: #166adc;
  border-radius: 10px;
}
.project {
  width: 30%;
  height: 150px;
  background-color: #d9d9d9;
  border-radius: 10px;
  text-align: center;
  max-width: 1000px;
  float: left;
  margin: 1.5%;
}
.title {
  font-weight: bold;
  display: block;
}
.content{
  display: block;
}
.buttonBox {
  text-align: center;
  width: 100%;
  clear: both;
}
.buttonBox > button {
  width: 150px;
  height: 50px;
  background-color: #166adc;
  border-radius: 10px;
  color: white;
}
</style>