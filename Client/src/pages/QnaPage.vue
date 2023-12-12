<template>
  <div class="">
    <div class="">
      <button type="button" class="btn btn-primary" v-on:click="fnWrite">문의등록</button>
    </div>
    <table class="table">
      <thead class="table-primary">
        <tr>
          <th>번호</th>
          <th>제목</th>
          <th>글쓴이</th>
          <th>상태</th>
          <th>등록일</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="(row,idx) in list" :key="idx">
          <td>{{ row.id }}</td>
          <td><a v-on:click="fnView(`${row.id}`)">{{ row.title}}</a></td>
          <td>{{ row.userId }}</td>
          <td>{{ row.status }}</td>
          <td>{{ row.createdDate }}</td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
import axios from 'axios';
export default {
  data() {
    return {
      requestBody: {},
      list: {},
      no: '',
    }
  },
  mounted() {
    this.fnGetList()
  },
  methods: {
    async fnGetList() {
      this.requestBody = {
        keyword: this.keyword,
        
      }

      axios.get("http://localhost:8090/api/question", {
        params: this.requestBody,
        headers: {}
      }).then((res) => {
        
        this.list = res.data

      }).catch((error) => {
        console.log(error.message);
      })        
      
    },
    fnView(id){
      this.requestBody.id = id
      this.$router.push({
        path: './detail',
        query: this.requestBody
      })
    },
    fnWrite() {
      this.$router.push({
        path: './write'
      })
    }
  }
  
}

</script>

<style scoped>
/* @import '@/assets/css/question.css'; */
</style>