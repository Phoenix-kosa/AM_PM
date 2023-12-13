<template>
  <h1>팀 채팅</h1>
  <button @click="loadData(pageNum++)" id="loadData">불러오기</button>
  <div id="msgArea"></div>
  <input @keyup.enter="send" type="text" placeholder="메시지 작성" v-model="msg">
  <button @click="send">send</button>
</template>

<script setup>
import axios from 'axios';
import { ref } from 'vue';

const msg = ref(null);
const projectId = ref(1);
const userId = ref(3);
const pageNum = ref(null);

function loadData(cursor) {
  axios.get(`http://localhost:8090/api/chat/${projectId.value}?cursorId=${cursor}`, {
            headers: { 
                "Authorization" : sessionStorage.getItem("access-token") }
            }
            )
  .then((response) => {
    console.log(response)
    console.log(response.data.pageInfo);
    if(!response.data.pageInfo.hasNext) {
      document.getElementById("loadData").style.display = 'none';
    }
    
    const msgArea = document.getElementById("msgArea");

    for(var res of response.data.data) {
      let htmlData = `<div><img src="${res.profileImg}"></img>`;
      htmlData += `<span>${res.user.nickname}</span>`;
      htmlData += `<span>${res.message}</span>`;
      htmlData += `<span>${res.createdDate}</span>`;
      htmlData += `<span>${res.unread}</span></div>`;
      msgArea.innerHTML = htmlData + msgArea.innerHTML;
      pageNum.value = res.id;
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

const websocket = new WebSocket("ws://localhost:8090/chat/" + projectId.value);
websocket.onmessage = onMessage;
websocket.onclose = onClose;
websocket.onopen = onOpen;

function send() {
  let data = {
    message : msg.value, 
    projectId : projectId.value, 
    userId : userId.value
  };

  websocket.send(JSON.stringify(data));
  msg.value = '';
}

function onClose() {
  console.log("퇴장");
}

function onOpen() {
  loadData(-1);
  console.log("입장");
}

function onMessage(message) {
  let data = JSON.parse(message.data);
  console.log(data);

  const msgArea = document.getElementById("msgArea");

  msgArea.innerHTML += `<div>`;
  msgArea.innerHTML += `<img src="${data.profileImg}"></img>`;
  msgArea.innerHTML += `<span>${data.nickName}</span>`;
  msgArea.innerHTML += `<span>${data.message}</span>`;
  msgArea.innerHTML += `<span>${data.createdDate}</span>`;
  msgArea.innerHTML += `<span>${data.unread}</span>`;
  msgArea.innerHTML += `</div>`;
  console.log(message);
}
</script>

<style>
button {
  background-color: aquamarine;
  color: white;
  border-radius: 20px;
}
</style>