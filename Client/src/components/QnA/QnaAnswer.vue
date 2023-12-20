<template>
  <form @submit.prevent="validateAndSave">
    <h2>댓글</h2>
    
    <div class="d-grid gap-2 d-md-flex justify-content-md-end">
      <button type="button" class="w3-button text-white bg-dark" v-on:click="validateAndSave">저장</button>
      <button type="button" class="w3-button text-black bg-white" v-on:click="fnList">목록</button>
    </div>
    <div class="board-contents">
      <label for="title">제목: </label>
      <input type="text" v-model="title" class="w3-input w3-border" placeholder="제목을 입력하세요.">
      <div v-if="!title" class="error-message"></div>
      이름:<input type="text" v-model="bullentinId" class="w3-input w3-border" placeholder="작성자를 입력하세요." v-if="id === undefined">
      <div v-if="id === undefined && !bullentinId" class="error-message"></div>
    </div>
    
    <br>
    
    <div class="board-contents">
      <label for="content">내용: </label>
      <textarea name="" id="" cols="90" rows="10" v-model="content" class="w3-input w3-border" style="resize: none;"></textarea>
      <div v-if="!content" class="error-message"></div>
    </div>
  </form>
</template>

<script>

import axios from 'axios'
export default {
  data(){
    return {
      requestBody: this.$route.query,
      id: this.$route.query.id,
      errors: [],
      title: '',
      bullentinId: '',
      content: '',
      createdDate: ''
    }
  },
  mounted(){
    this.fnGetView()
  },
  methods: {
    validateAndSave() {
      this.errors = [];
      if (!this.title){
        this.errors.push("제목을 입력하세요")
      }
      if (!this.content) {
        this.errors.push("내용을 입력하세요")
      }

      if (this.errors.length === 0) {
        this.fnSave();
      }
    },
    fnGetView(){
      if (this.id !== undefined) {
        axios.get("http://localhost:8090/api/answer/"+this.id,{
          params: this.requestBody
        }).then((res) => {
        this.title = res.data.title
        this.userId = res.data.userId
        this.content = res.data.content
        this.createdDate = res.data.createdDate
      })
      }
    },
    fnList() {
      delete this.requestBody.id
      this.$router.push({
        path: './question',
        query: this.requestBody
      })
    },
    fnView(id){
      this.requestBody.id = id
      this.$router.push({
        path: './detail',
        query: this.requestBody
      })
    },
    fnSave(){
      let apiUrl = "http://localhost:8090/api/answer"
      this.form = {
        "id": this.id,
        "title": this.title,
        "content": this.content,
        "bullentinId": this.bullentinId,
        
      } 

      if (this.id === undefined){
        axios.post(apiUrl, this.form)
        .then((res) => {
          alert("저장되었습니다.")
          this.fnView(res.data.id)
        })
      } else {
        axios.patch(apiUrl, this.form)
        .then((res) => {
          alert("수정되었습니다.")
          this.fnView(res.data.id)
        })
      }
    }
  }
}
</script>
<style scoped>
</style>
