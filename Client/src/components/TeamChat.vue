<template>
  <div class="chatContainer">
    <div class="msgContainer">
      <div class="buttonContainer">
        <button @click="loadData(pageNum++)" id="loadData">불러오기</button>
      </div>
      <div id="msgArea"></div>
    </div>
    <div class="inputContainer">
      <input class="inputBox" @keyup.enter="send" type="text" placeholder="메시지 작성" v-model="msg" maxlength="250">
      <button class="sendButton" @click="send">전송</button>
    </div>
  </div>
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
      let htmlData = `<div class="contentContainer"><div class="imgBox"><img src="${res.user.profileImg}"></img></div>`;
      htmlData += `<div class="message"><div class="details"><span class="nickname">${res.user.nickname}</span>`;
      var date = new Date(res.createdDate);
      var today = new Date();
      if(date.getDate() == today.getDate()) {
        var time = "오전";
        var hour = date.getHours();
        if(hour > 12) {hour -= 12;}
        if(date.getHours() >= 12) {
          time = "오후";
        }
        htmlData += `<span class="date">${time} ${hour}시${date.getMinutes()}분</span>`;
      }
      else {
        htmlData += `<span class="date">${date.getMonth() + 1}월${date.getDate()}일 ${date.getHours()}시${date.getMinutes()}분</span>`;
      }
      htmlData += `<span class="unread">${res.unread}</span></div>`;
      htmlData += `<span>${res.message}</span></div></div>`;
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

  var html = `<div class="contentContainer"><div class="imgBox"><img src="${data.profileImg}"></img></div>`;
  html += `<div class="message"><div class="details"><span class="nickname">${data.nickName}</span>`;
  var date = new Date(data.createdDate);
      
  var time = "오전";
  var hour = date.getHours();
  if(hour > 12) {hour -= 12;}
  if(date.getHours() >= 12) {
    time = "오후";
  }
  html += `<span class="date">${time} ${hour}시${date.getMinutes()}분</span>`;
  html += `<span class="unread">${data.unread}</span></div>`;
  html += `<span>${data.message}</span></div></div>`;
  msgArea.innerHTML += html;
}
</script>

<style>
.chatContainer {
  border-color: #166adc;
  border-radius: 20px;
  border-width: 1px;
  border-style: dashed;
  padding: 10px;
  height: 600px;
}
.buttonContainer {
  margin-top: 10px;
  margin-bottom: 10px;
  text-align: center;
}
#loadData {
  background-color: #d9d9d9;
  color: black;
  width: 150px;
}
.msgContainer {
  height: 500px;
  overflow: auto;
}
.msgContainer::-webkit-scrollbar {
  width: 10px;
}
.msgContainer::-webkit-scrollbar-thumb {
  background-color: #166adc;
  border-radius: 10px;
}
.inputContainer {
  text-align: center;
  margin-top: 20px;
}
.inputBox {
  background-color: #d9d9d9;
  width: 80%;
  height: 50px;
}
.sendButton {
  color: white;
  background-color: #166adc;
  border-radius: 0;
  width: 10%;
  height: 50px;
  margin-left: 5px;
}
.contentContainer {
  display: flex;
  
}
.imgBox {
  width: 50px;
  height: 50px;
}
img {
  width: 100%;
}
.details {
}
.message {
  max-width: 90%;
}
.nickname {
  font-weight: bold;
  margin-right: 10px;
}
.date {
  margin-right: 10px;
}
.unread {
  color: #166adc;
}
</style>