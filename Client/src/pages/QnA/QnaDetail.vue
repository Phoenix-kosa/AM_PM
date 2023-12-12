<template>
  <div class="container">
    <h1>1:1 문의게시판</h1>
    <div class="row row1">
      <table class="table">
        <tr>
          <th width=20% class="text-center warning">번호</th>
          <td width=30% class="text-center">{{ id }}</td>
          <th width=20% class="text-center warning">작성일</th>
          <td width=30% class="text-center">{{ createdDate }}</td>
        </tr>
        <tr>
          <th width=20% class="text-center warning">이름</th>
          <td width=30% class="text-center">{{ userId }}</td>
          <th width=20% class="text-center warning"></th>
          <td width=30% class="text-center"></td>
        </tr>
        <tr>
          <th width=20% class="text-center warning">제목</th>
          <td colspan="3">{{ title }}</td>
        </tr>
        <tr>
          <td colspan="4" class="text-left" valign="top" height="200">
            <pre style="white-space: pre-wrap;border:none;background-color: white;">{{ content }}</pre>
          </td>
        </tr>
        <div class="common-buttons">
        <button type="button" class="btn btn-outline-primary" v-on:click="fnUpdate">수정</button>
        <button type="button" class="btn btn-outline-primary" v-on:click="fnDelete">삭제</button>
        <button type="button" class="btn btn-outline-primary" v-on:click="fnList">목록</button>
        </div>
      </table>     
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