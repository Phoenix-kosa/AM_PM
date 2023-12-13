<template>
  <div class="board-detail">
    <div class="common-buttons">
      <button type="button" class="btn btn-outline-primary" v-on:click="fnUpdate">수정</button>
      <button type="button" class="btn btn-outline-primary" v-on:click="fnDelete">삭제</button>
      <button type="button" class="btn btn-outline-primary" v-on:click="fnList">목록</button>
    </div>
    <div class="board-contents">
      <h3>{{ title }}</h3>
      <div>
        <span class="">{{ userId }}</span>
        <br>
        <span>{{ createdDate }}</span>
      </div>
    </div>
    <div class="board-contents">
      <span>{{ content }}</span>
    </div>
  </div>
</template>

<script>
import axios from 'axios';

export default{
  data(){
    return {
      requestBody: this.$route.query,
      id: this.$route.query.id,

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
      axios.get("http://localhost:8090/api/question/"+this.id,{
        params: this.requestBody
      }).then((res) => {
        this.title = res.data.title
        this.userId = res.data.userId
        this.content = res.data.content
        this.createdDate = res.data.createdDate
      })
    },
    fnList() {
      delete this.requestBody.id
      this.$router.push({
        path: './question',
        query: this.requestBody
      })
    },
    fnUpdate(){
      this.$router.push({
        path: './write',
        query: this.requestBody
      })
    },
    fnDelete(){
    if (!confirm("글을 삭제하시겠습니까?")) return

    axios.delete("http://localhost:8090/api/question/"+this.id,{})
      .then(() => {
        alert('삭제되었습니다.')
        this.fnList();
      })
    }
  }  
}
</script>