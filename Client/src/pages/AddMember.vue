<template>
  <h2 class="">멤버 추가</h2>
  <div class="container">
    <div class="search">
      <form class="d-flex">
        <input class="form-control me-2 bg-light" v-model="searchMember" placeholder="Search" >
        <button class="btn btn-outline-primary" @click="submit">Search</button>
      </form>
    </div>
    <div class="list">
      <ul>
        <li v-for="item in filteredData" :key="item.id">{{ item.title }}</li>
      </ul>
    </div>
    <div class="add">
      <button @click="addMember" class="btn btn-primary">멤버 추가하기</button>
    </div>
  </div>
</template>

<script setup>
import axios from 'axios';
import { ref, onMounted } from 'vue';

const projectId = sessionStorage.getItem("projectId");
const userId = ref(null);
const roles = ref(null);
const searchMember = ref('');
const filteredData = ref([]);

onMounted(async () => {
  try {
    const response = await axios.get("http://localhost:8090/api/members/" + projectId);
    data.value = response.data;
  } catch (error) {
    console.error('Error fetching data:', error);
  }
});

// function addMember(){
//   var requestProject = {
//     projectId:projectId.value,
//     userId:userId.value
//   }
//   axios.put(`http://localhost:8090/api/members/` + projectId,
//   requestProject,
//   {
//     headers: { 
//         "Authorization" : sessionStorage.getItem("access-token") 
//   },
//   })
//   .catch((err) => {
//     console.log(err)
//     if(err.response.status == 401) {
//       console.log("토큰 만료");

//       axios.get("http://localhost:8090/api/rtoken", {
//           headers: { 
//               "RefreshToken" : sessionStorage.getItem("refresh-token"),
//               "Authorization" : sessionStorage.getItem("access-token") }
//           }).then(response => {
//               console.log(response)
//               if(response.status == 200){
//                   console.log("토큰 재발급");
//                   console.log(response.headers.authorization);
//                   sessionStorage.setItem("access-token", response.headers.authorization);
//               } else {
//                   console.log("토큰 재발급 실패");
//               }
//           }).catch(error => {console.error(error);})
//     } 
//   });
// }

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
