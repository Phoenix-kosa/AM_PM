<template>
  <div>
    <div class="d-grid gap-2 d-md-flex justify-content-md-end">
      <button type="button" class="w3-button text-white bg-dark" v-on:click="fnSave">저장</button>
      <button type="button" class="w3-button text-black bg-white" v-on:click="fnList">목록</button>
    </div>
    <div class="board-contents">
      제목: <input type="text" v-model="title" class="w3-input w3-border" placeholder="제목을 입력하세요." required>
      작성자: <input type="text" v-model="userId" class="w3-input w3-border" placeholder="작성자를 입력하세요." v-if="id === undefined" required>
    </div>
    <div class="board-contents">
      내용: <textarea name="" id="" cols="30" rows="10" v-model="content" class="w3-input w3-border" style="resize: none;"></textarea>
    </div>
  </div>
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
      userId: '',
      content: '',
      createdDate: ''
    }
  },
  mounted(){
    this.fnGetView()
  },
  methods: {
    checkForm(e) {
      e.preventDefault();
      this.errors = [];
      if (!this.title){
        this.errors.push("제목을 입력하세요")
      }
      if (!this.content) {
        this.errors.push("내용을 입력하세요")
      }
    },
    fnGetView(){
      if (this.id !== undefined) {
        axios.get("http://localhost:8090/api/question/"+this.id,{
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
      let apiUrl = "http://localhost:8090/api/question"
      this.form = {
        "id": this.id,
        "title": this.title,
        "content": this.content,
        "userId": this.userId,
        "createdDate": this.createdDate
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

<style scoped></style>
