<template>
  <h1>팀 채팅</h1>
  <div id="msgArea"></div>
  <input type="text" placeholder="메시지 작성" v-model="msg">
  <button @click="send">send</button>
</template>

<script setup>
import { ref } from 'vue';
const projectId = 1;
const websocket = new WebSocket("ws://localhost:8090/api/chat");
websocket.onmessage = onMessage;
websocket.onclose = onClose;
websocket.onopen = onOpen;

const msg = ref(null);

function send() {
  let text = msg.value;
  let data = {message : text, projectId : 1, user_id : 1};
  msg.value = data;

  console.log("msg : " + msg.value);
  websocket.send(msg.value);
  msg.value = '';
}

function onClose() {
  console.log("퇴장");
}

function onOpen() {
  console.log("입장");
}

function onMessage(message) {
  let data = message.data;

  const msgArea = document.getElementById("msgArea");

  msgArea.innerHTML += `<div>`;
  msgArea.innerHTML += `<p>${data}</p>`;
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