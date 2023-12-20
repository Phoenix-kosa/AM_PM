<template>
  <div v-if="modalData != null" class="modal">
    <span class="xButton" @click="close">X</span>
    <div class="imgDiv">
      <img v-if="modalData.profileImg" :src="modalData.profileImg">
    </div>
    <div class="textDiv">
      <span class="nicknameModal">{{ modalData.nickname }}</span>
      <span class="emailModal">{{ modalData.email }}</span>
    </div>
    <div class="routerDiv">
      <RouterLink class="router" :to="{name: 'Chat', query: {user: modalData.userId}}">정보 확인</RouterLink>
    </div>
  </div>
  <div class="chatContainer">
    <div class="msgContainer" id="msgScroll">
      <div id="msgArea" v-for="data in chatList">
        <div class="contentContainer">
          <div @click="show(data.userId)" class="imgContainer">
            <img v-if="data.user" :src="data.user.profileImg"/>
            <img v-else="data.user" :src="data.profileImg"/>
          </div>
          <div class="message">
            <div class="details">
              <span v-if="data.user" @click="show(data.userId)" class="nickname" v-text="data.user.nickname"></span>
              <span v-else="data.user" @click="show(data.userId)" class="nickname" v-text="data.nickName"></span>
              <span class="date">{{ data.createdDate.substring(0, 10) }} {{data.createdDate.substring(11, 16)}}</span>
              <span v-if="data.unread > 0" class="unread" v-text="data.unread"></span>
            </div>
            <span v-text="data.message"></span>
          </div>
        </div>
      </div>
    </div>
    <div class="inputContainer">
      <input class="inputBox" @keyup.enter="send" type="text" placeholder="메시지 작성" v-model="msg" maxlength="250">
      <button class="sendButton" @click="send">전송</button>
    </div>
  </div>
</template>

<script setup>
import { RouterLink } from 'vue-router';
import { ref } from 'vue';
import { onBeforeRouteLeave  } from 'vue-router';
import Stomp from 'webstomp-client';
import axios from 'axios';

const msg = ref(null);
const projectId = sessionStorage.getItem("projectId");
const userId = ref(null);
const chatList = ref([]);
const modalData = ref(null);

function decodeToken(token) {
  const base64Url = token.split('.')[1];
  const base64 = base64Url.replace('-', '+').replace('_', '/');
  return JSON.parse(atob(base64));
}

function show(userId) {
  axios.get('http://localhost:8090/api/user/' + userId, {
    headers: { 
        "Authorization" : sessionStorage.getItem("access-token") 
    }
  })
  .then((response) => {
    modalData.value = response.data;
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

function close() {
  modalData.value = null;
}

const decodedPayload = decodeToken(sessionStorage.getItem("access-token"));
userId.value = decodedPayload.id;

let websocket = new WebSocket('ws://localhost:8090/chat');
let stomp = Stomp.over(websocket);
stomp.connect({}, function() {
  stomp.subscribe("/sub/load/" + projectId, function(chat) {
    let data = JSON.parse(chat.body);
    chatList.value = data;
  });
  loadData();
  setTimeout(function () {
    let msgArea = document.getElementById("msgScroll");
    msgArea.scrollTop = msgArea.scrollHeight;
  }, 300);
  
  stomp.subscribe("/sub/chat/" + projectId, function(chat) {
    let data = JSON.parse(chat.body);
    chatList.value.push(data);
    setTimeout(function () {
      let msgArea = document.getElementById("msgScroll");
      msgArea.scrollTop = msgArea.scrollHeight;
    }, 100);
    loadData();
  });
});

onBeforeRouteLeave(() => {
  stomp.disconnect();
});

function loadData() {
  let data = {
    message : null, 
    projectId : projectId, 
    userId : userId.value
  };
  stomp.send("/pub/chat/enter", JSON.stringify(data), {});
}

function send() {
  let data = {
    message : msg.value, 
    projectId : projectId, 
    userId : userId.value
  };

  stomp.send("/pub/chat/message", JSON.stringify(data), {});
  msg.value = '';
}
</script>

<style scoped>
.modal {
  position: fixed;
  top: 50%;
  left: 50%;
  transform: translate(-40%, -50%);
  width: 400px;
  height: 400px;
  background-color: rgba(22, 106, 220, 0.7);
  border: #166adc solid;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  z-index: 1000;
}

.xButton {
  color: white;
  font-weight: bold;
  position: absolute;
  top: 10px;
  right: 10px;
  cursor: pointer;
}
.imgDiv {
  width: 200px;
  height: 200px;
  margin-bottom: 20px;
  background-color: white;
  border-radius: 50%;
}
.modal img {
  width: 100%;
  height: 100%;
  border-radius: 50%;
}
.textDiv {
  display: flex;
  flex-direction: column;
  align-items: center;
}
.nicknameModal {
  color: black;
  margin-bottom: 10px;
  font-weight: bold;
}
.emailModal {
  color: black;
  margin-bottom: 10px;
}
.chatContainer {
  border-color: #166adc;
  border-radius: 20px;
  border-width: 1px;
  border-style: dashed;
  padding: 10px;
  height: 600px;
  min-width: 500px;
}
#loadData {
  background-color: #d9d9d9;
  color: black;
  width: 150px;
  border-radius: 20px;
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
.imgContainer {
  width: 50px;
  height: 50px;
  cursor: pointer;
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
  cursor: pointer;
}
.date {
  margin-right: 10px;
}
.unread {
  color: #166adc;
}
.routerDiv {
  background-color: white;
  width: 150px;
  height: 50px;
  border-radius: 10px;
  position: relative;
}
.router {
  text-decoration: none;
  color: #166adc;
  font-weight: bold;
  position: absolute;
  left: 50%;
  top: 50%;
  transform: translate(-50%, -50%);
}
</style>