<template>
  <div class="project-plan">
    <h1>{{ pageTitle }}</h1>
    <div>
      <p>참고 사이트: <a :href="sampleUrl" target="_blank">{{ sampleUrl }}</a></p>
      <input type="file" @change="handleFileUpload" />
      <button @click="uploadFile">파일 업로드</button><br><br>
      <img :src="imagePreview" alt="Preview" v-if="imagePreview" height="650px" width="850px"/>
    </div>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  props: {
    pageType: {
      type: String,
      required: true
    },
    pageId: {
      type: Number,
      required: true
    }
  },
  data() {
    return {
      sampleUrl: '',
      imagePreview: null,
      uploadedFile: null,
    };
  },
  computed: {
    pageTitle() {
      return `${this.pageType} 페이지`;
    },
    uploadUrl() {
      return `http://localhost:8090/api/plan/user-${this.pageType.toLowerCase()}/${this.pageId}`;
    }
  },
  methods: {
    handleFileUpload(event) {
      const file = event.target.files[0];
      this.imagePreview = URL.createObjectURL(file);
      this.uploadedFile = file;
    },
    fetchPageData() {
      axios.get(`http://localhost:8090/api/plan/${this.pageType.toLowerCase()}-example`, { params: { id: this.pageId } })
        .then(response => {
          const data = response.data;
          this.sampleUrl = data.sampleUrl;
          this.imagePreview = `http://localhost:8090${data.sampleImg}` || this.defaultImage;
        })
        .catch(error => {
          console.error('데이터를 가져오는 데 실패했습니다:', error);
        });
    },
    uploadFile() {
  const formData = new FormData();
  formData.append('file', this.uploadedFile);
  formData.append('id', this.pageId);

  axios.post(`http://localhost:8090/api/plan/user-${this.pageType.toLowerCase()}`, formData, { 
    headers: {
      'Content-Type': 'multipart/form-data',
    },
  }).then(response => {
    console.log('이미지 업로드 성공:', response);
    this.fetchPageData(); 
  }).catch(error => {
    console.error('이미지 업로드 실패:', error);
  });
}
  },
  mounted() {
    this.fetchPageData();
  }
};
</script>

<style scoped>
@import "../assets/css/ProjectPlanPage.css";

</style>
