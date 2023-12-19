<template>
  <h2 class="" style="text-align: center;">멤버 목록</h2>
  <div class="container">
    <div class="list">
      <ul>
        <li v-for="item in filteredData" :key="item.id">{{ item.title }}</li>
      </ul>
    </div>
    <div class="clickbutton">
      <button @click="addMember" class="btn btn-primary">멤버 추가</button>
      <button @click="" class="btn btn-primary">멤버 제거</button>
      <button @click="leaderChange" class="btn btn-primary">프로젝트 대표 변경</button>
    </div>
  </div>
  
</template>

<script setup>

function addMember() {
  location.href = "/add-member";
}

function leaderChange() {
  location.href = "/leader-change";
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
  display: inline-block;
  text-align: center;
}
.list {
  height: 400px;
  width: 600px;
  background-color: beige;
  margin-top: 15px;
  margin-left: 25%;
  text-align: center;
}



.clickbutton {
  margin-left: 10px;
}
</style>
