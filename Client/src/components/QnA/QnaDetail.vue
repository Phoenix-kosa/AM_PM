<template>
  <div class="container">
    <h2>1:1 문의</h2>
    <div class="row row1">
      <table class="table table-success table-striped">
        <colgroup>
					<col width="15%"/>
					<col width="35%"/>
					<col width="15%"/>
					<col width="35%"/>
				</colgroup>
        <tr class="table-primary">
          <th scope="row" class="text-center">번호</th>
          <td class="text-center">{{ id }}</td>
          <th scope="row" class="text-center warning">작성일</th>
          <td class="text-center">{{ createdDate }}</td>
        </tr>
        <tr class="table-primary">
          <th width=20% class="text-center warning">이름</th>
          <td width=30% class="text-center">{{ userId }}</td>
          <th width=20% class="text-center warning"></th>
          <td width=30% class="text-center"></td>
        </tr>
        <tr>
          <th class="text-center">제목</th>
          <td class="text-center">{{ Qtitle }}</td>
        </tr>
        <tr>
          <td  colspan="4" class="text-left" valign="top" height="200">
            <pre style="white-space: pre-wrap;border:none;background-color: white;">{{ Qcontent }}</pre>
          </td>
        </tr>
        <div class="d-grid gap-2 d-md-flex justify-content-md-end">
          <button type="button" class="btn btn-outline-primary" v-on:click="fnUpdate">수정</button>
          <button type="button" class="btn btn-outline-primary" v-on:click="fnQdelete">삭제</button>
          <button type="button" class="btn btn-outline-primary" v-on:click="fnList">목록</button>
        </div>
      </table>
      <hr><br>
      
      <div class="row row1">
          <tr class="table-primary">
            <th scope="row" class="text-center">제목: </th>
            <td class="text-center">{{ Atitle }}</td>
            <th scope="row" class="text-center warning">내용: </th>
            <td class="text-center">{{ Acontent }}</td>
          </tr>
      </div>

      <table v-show="isAdmin">      
        <div class="mb-3">
          <label for="title" class="form-label">제목: </label>
          <input type="text" v-model="Atitle" class="form-control" placeholder="제목을 입력하세요.">
          <label for="content" class="form-label">내용: </label>
          <textarea name="" id="" cols="90" rows="10" v-model="comments" class="form-control form-control-sm" style="resize: none;" required></textarea>
        </div>
        <div class="d-grid gap-2 d-md-flex justify-content-md-end">
          <button type="button" class="btn btn-outline-primary" v-on:click="fnASave">저장</button>
          <button type="button" class="btn btn-outline-primary" v-on:click="fnAdelete">삭제</button>
        </div>
      </table>
    </div>   
  </div>
</template>

<script>
import axios from 'axios';
import { toHandlers } from 'vue';

export default{
  data(){
    return {
      isAdmin: false, 
      requestBody: this.$route.query,
      id: this.$route.query.id,
      questionId: '',
      userId: '',
      Qtitle: '',      
      Qcontent: '',
      Atitle: '',
      Acontent: '',
      createdDate: '',
      comments: '',     
      bullentinId: '',
    }
  },
  mounted(){
    this.fnGetQuestion()
    this.fnGetAnswer()
    // this.fnGetView2()
  },
  methods: {
    fnGetQuestion(){
      axios.get("http://localhost:8090/api/question/"+this.id,{
        params: this.requestBody
      }).then((res) => {
        console.log(res)
        // this.Qtitle = res.data.title
        // this.userId = res.data.userId
        // this.Qcontent = res.data.content
        // this.createdDate = res.data.createdDate
        // this.roles = res.data.roles
      })
    },
    fnGetAnswer(){
      axios.get("http://localhost:8090/api/answer/"+this.id,{
      }).then((res) => {
        console.log(res)
        this.Atitle = res.data.title
        this.Acontent = res.data.content
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

    fnAList() {
      delete this.requestBody.id
      this.$router.push({
        path: './answer',
        query: this.requestBody
      })
    },
    fnUpdate(){
      this.$router.push({
        path: './write',
        query: this.requestBody
      })
    },
    fnQdelete(){
      if (!confirm("글을 삭제하시겠습니까?")) return

      axios.delete("http://localhost:8090/api/question/"+this.id,{})
        .then(() => {
          alert('삭제되었습니다.')
          this.fnList();
      })
    },
    fnASave(){
      let apiUrl = "http://localhost:8090/api/answer"
      this.form = {
        "title": this.Atitle,
        "content": this.Acontent
      }
    },
    fnAdelete(){
      if (!confirm("글을 삭제하시겠습니까?")) return

        axios.delete("http://localhost:8090/api/answer/"+this.id)
        .then(() => {
          alert('삭제되었습니다.')
        this.fnGetQuestion();
      })

      this.$router.push({
        path: './question',
        query: this.requestBody
      })
    },

  }  
}
</script>
<style scoped></style>