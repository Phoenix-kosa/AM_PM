<template>
  <div class="container">
    <div class="inputBox">
      <input type="text" placeholder="제목" v-model="title">
      <textarea placeholder="설명" v-model="content"></textarea>
    </div>
    <div class="buttonBox">
      <button @click="createProject">프로젝트 생성</button>
    </div>
  </div>
</template>
<script setup>
import axios from 'axios';
import { ref } from 'vue';

const title = ref(null);
const content = ref(null);
function createProject() {
  var requestProject = {
    title:title.value,
    content:content.value,
    startDate:null,
    endDate:null
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
      location.href = "/project";
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