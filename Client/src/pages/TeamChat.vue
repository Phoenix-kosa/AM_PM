<template>
  <div class="chatContainer">
    <div class="msgContainer" id="msgScroll">
      <div id="msgArea" v-for="data in chatList">
        <div class="contentContainer">
          <div class="imgContainer">
            <img v-if="data.user" :src="data.user.profileImg"/>
            <img v-else="data.user" :src="data.profileImg"/>
          </div>
          <div class="message">
            <div class="details">
              <span v-if="data.user" class="nickname" v-text="data.user.nickname"></span>
              <span v-else="data.user" class="nickname" v-text="data.nickName"></span>
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
import axios from 'axios';
import { ref } from 'vue';
import Stomp from 'webstomp-client';

const msg = ref(null);
const projectId = sessionStorage.getItem("projectId");
const userId = ref(null);
const chatList = ref([]);

function decodeToken(token) {
  const base64Url = token.split('.')[1];
  const base64 = base64Url.replace('-', '+').replace('_', '/');
  return JSON.parse(atob(base64));
}

const decodedPayload = decodeToken(sessionStorage.getItem("access-token"));
userId.value = decodedPayload.id;

var websocket = new WebSocket('ws://localhost:8090/chat');
var stomp = Stomp.over(websocket);
stomp.connect({}, function() {
  stomp.subscribe("/sub/chat/" + projectId + "/user/" + userId.value, function(chat) {
    var data = JSON.parse(chat.body);
    chatList.value = data;
  });
  loadData();
  setTimeout(function () {
      var msgArea = document.getElementById("msgScroll");
      msgArea.scrollTop = msgArea.scrollHeight;
  }, 200);
  
  stomp.subscribe("/sub/chat/" + projectId, function(chat) {
    var data = JSON.parse(chat.body);
    axios.get(`http://localhost:8090/api/read/${data.id}`, {
      headers: { 
          "Authorization" : sessionStorage.getItem("access-token") 
      }
    })
    .then((response) => {
      console.log(response.status);
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
    chatList.value.push(data);
    setTimeout(function () {
      var msgArea = document.getElementById("msgScroll");
      msgArea.scrollTop = msgArea.scrollHeight;
    }, 100);
  });
  setInterval(() => {
    loadData();
  }, 5000);
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
.chatContainer {
  border-color: #166adc;
  border-radius: 20px;
  border-width: 1px;
  border-style: dashed;
  padding: 10px;
  height: 600px;
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