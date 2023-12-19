<template>
  <h2 class="">멤버 추가</h2>
  <div class="container">
    <div class="search">
      <form class="d-flex" @submit.prevent="submitForm">
        <input class="form-control me-2 bg-light" v-model="searchMember" placeholder="Search" >
        <button class="btn btn-outline-primary" type="submit">Search</button>
      </form>
    </div>
    <div class="list">
        <div class ="search-user" v-for="item in filteredData" :key="item.id">
            <input type="checkbox" v-model="userform" :value="item.id">
            <label :for="item.id"> {{ item.nickname }}</label>
        </div>
    </div>
    <div class="add">
      <button @click="memberList" class="btn btn-primary">멤버 추가하기</button>
    </div>
  </div>
</template>

<script setup>
import axios from 'axios';
import { ref } from 'vue';

const projectId = sessionStorage.getItem("projectId");
const userId = ref(null);
const roles = ref(null);
const searchMember = ref('');
const filteredData = ref([]);
let userform = ref([]);
let memberIdList = ref([]);

const memberList = () => {
  let formdata = []
  for(let o of userform.value){
    formdata.push(o);
  }
  console.log("FormData " + formdata);
  axios.put("http://localhost:8090/api/members/" + projectId, {"members":formdata}, {
    headers: {
      "Authorization" : sessionStorage.getItem("access-token") 
    }
  }).then(response => {
    console.log(response)
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

const submitForm = () => {
  // console.log(searchMember.value);
  axios.get(`http://localhost:8090/api/user/nickname?nickname=${searchMember.value}`)
  .then(request => {
    // console.log(request.data)
    // filteredData.value = request.data
    filteredData.value = [];
    let checkid = [];
    for (let o of memberIdList.value){
      checkid.push(o.userId)
    }
    for(let o of request.data){
      console.log(memberIdList.value)
      if(o.roles == "ROLE_USER" && !checkid.includes(o.id)){
        filteredData.value.push({"id":o.id, "nickname":o.nickname})
      }
    }
  })
}

function loadData(){
  axios.get(`http://localhost:8090/api/members/` + projectId, {
    headers: { 
        "Authorization" : sessionStorage.getItem("access-token") 
    }
  })
  .then((response) => {
    memberIdList.value = response.data;
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
  display: inline-block;
  text-align: center;

}

.search {
  display: inline-block;
  text-align: center;
  margin-top: 10px;
}

.list {
  height: 400px;
  width: 600px;
  background-color: beige;
  margin-top: 15px;
  margin-left: 25%;
  text-align: center;
}

.add{
  text-align: center;
  margin-top: 15px;
}
</style>
