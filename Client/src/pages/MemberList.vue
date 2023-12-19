<template>
  <h2 class="" style="text-align: center;">멤버 목록</h2>
  <div class="container">
    <div class="list">
      <ul>
        <div class ="member-list" v-for="item in memberList" :key="item.id">
            <input class="checkbox" type="checkbox" v-model="userform" :value="item.userId">
            <label :for="item.id"> {{ item.nickName }}</label>
        </div>
      </ul>
    </div>
  </div>
  <div class="clickbutton">
    <button @click="addMember" class="btn btn-primary">멤버 추가</button>
    <button @click="removeMember" class="btn btn-primary">멤버 제거</button>
    <button @click="leaderChange" class="btn btn-primary">프로젝트 대표 변경</button>
  </div>
  
</template>

<script setup>
import axios from 'axios';
import { ref } from 'vue';
import { refresh } from "@/api/refresh";
import { useRouter } from 'vue-router';
const router = useRouter();

const memberList = ref([]);
let userform = ref([]);

const projectId = sessionStorage.getItem("projectId");

function addMember() {
  router.push("/add-member");
}

function leaderChange() {
  router.push("/leader-change");
}

const removeMember = () => {
  let formdata = []
  for(let o of userform.value){
    formdata.push(o);
  }

  console.log(formdata)
  if(formdata.length> 0){
    axios.delete("http://localhost:8090/api/members/" + projectId, {
      headers: {
        "Authorization" : sessionStorage.getItem("access-token") 
      } , data: {"members":formdata}
    }).then(response => {
      console.log(response.status)
      alert("맴버를 제거했습니다");
      loadData();
    })
    .catch((err) => {
      console.log(err)
      if(err.response.status == 401) {
        console.log("토큰 만료");
        refresh();
      } 
    });
  } else {
    alert("체크한 유저가 없습니다!");
  }
}

function loadData(){
  axios.get(`http://localhost:8090/api/members/` + projectId, {
    headers: { 
        "Authorization" : sessionStorage.getItem("access-token") 
    }
  })
  .then((response) => {
    memberList.value = response.data;
  })
  .catch((err) => {
    console.log(err)
    if(err.response.status == 401) {
      console.log("토큰 만료");
      refresh();
    } 
  });
}
loadData();
</script>

<style scoped>
@import "@/assets/css/memberlist.css"
</style>
