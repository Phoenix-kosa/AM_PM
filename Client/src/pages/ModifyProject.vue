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
import { ref } from 'vue';
import { useRouter } from 'vue-router';

const router = useRouter();
const projectId = sessionStorage.getItem("projectId");
const projectTitle = ref(null);
const projectContent = ref(null);

const title = ref(null);
const content = ref(null);
const startDate = ref(null);
const endDate = ref(null);
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
      alert("수정이 완료되었습니다.");
      router.go();
    }
    else {
      window.alert("권한이 없습니다.");
    }
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
  height: 600px;
  min-width: 700px;
}
.inputBox {
  width: 90%;
  margin: 5%;
}
input {
  background-color: #d9d9d9;
  display: block;
  margin-bottom: 10px;
  width: 100%;
}
textarea {
  background-color: #d9d9d9;
  display: block;
  width: 100%;
  height: 200px;
}
.dateBox {
  width: 90%;
  margin: auto;
}
.dateBox > label {
  width: 45%;
  margin-right: 5%;
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
  margin-top: 50px;
}
</style>