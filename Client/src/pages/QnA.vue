<template>
  <div class="">
    <div class="">
      <button type="button" v-on:click="">문의등록</button>
    </div>
    <table class="">
      <thead>
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
          <td>{{ row.idx }}</td>
          <td><a v-on:click="">{{ row.title}}</a></td>
          <td>{{ row.userId }}</td>
          <td>{{ row.status }}</td>
          <td>{{ row.createdDate }}</td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
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
    fnGetList() {
      this.requestBody = {
        keyword: this.keyword,
        
      }

      this.$axios.get(this.$serverUrl + "/api/question", {
        params: this.requestBody,
        headers: {}
      }).then((res) => {
        
        this.list = res.data

      }).catch(error => {
        console.log(error.message);
      })        
      
    }
  }
}
</script>

<style scoped>
/* @import '@/assets/css/question.css'; */
</style>