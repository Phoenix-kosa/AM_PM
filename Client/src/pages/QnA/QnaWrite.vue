<template>
  <div class="">
    <button type="button" class="" v-on:click="fnSave">저장</button>
    <button type="button" class="" v-on:click="fnList">목록</button>
  </div>
  <div>
    <input type="text" v-model="title" class="" placeholder="제목을 입력하세요.">
    <input type="text" v-model="userId" class="" placeholder="작성자를 입력하세요.">
  </div>
  <div>
    <textarea name="" id="" cols="30" rows="10" v-model="content" class="" style=""></textarea>
  </div>
</template>

<script>

import axios from 'axios'
export default {
  data(){
    return {
      requestBody: this.$route.query,
      id: this.$route.query.idx,

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
