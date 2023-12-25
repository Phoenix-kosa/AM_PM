<template>
  <div class="container">
    <div v-for="data in projectList">
      <div class="project" @click="go(data.id)">
        <span class="title">{{ data.title }}</span>
        <span class="content">{{ data.content }}</span>
        <span v-if="data.startDate" class="startDate">시작 날짜 : {{ data.startDate }}</span>
        <span v-if="data.endDate" class="endDate">종료 날짜 : {{ data.endDate }}</span>
      </div>
    </div>
    <div class="buttonBox">
      <RouterLink class="button" to="/create-project">+새 프로젝트</RouterLink>
    </div>
  </div>
</template>
<script setup>
import axios from 'axios';
import { ref } from 'vue';
import { useRouter } from 'vue-router';

const projectList = ref([]);
const router = useRouter();

function go(projectId) {
  sessionStorage.setItem("projectId", projectId);
  router.push({ name: "ProjectPlanPage", params: { pageType: 'srs' } });
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
  overflow: hidden;
  height: 50%;
}
.startDate {
  display: block;
}
.endDate {
  display: block;
}
.buttonBox {
  text-align: center;
  width: 100%;
  clear: both;
}
.button {
  text-decoration: none;
  display: flex;
  align-items: center;
  justify-content: center;
  width: 150px;
  height: 50px;
  background-color: #166adc;
  border-radius: 10px;
  color: white;
  margin: auto;
}
</style>