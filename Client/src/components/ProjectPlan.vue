<template>
  <div class="project-plan" :key="pageId">
    <h1>{{ pageTitle }}</h1>
    <div>
      <p>참고 사이트: <a :href="sampleUrl" target="_blank">{{ sampleUrl }}</a></p>
      <input type="text" v-model="editableSampleUrl" />
      <button @click="saveUrl">저장</button>
      <br><br>
      <input type="file" @change="handleFileUpload" />
      <button @click="uploadFile">파일 업로드</button><br><br>
      <button @click="createNewPage">새 페이지 추가</button>
      <br>
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
      editableSampleUrl: '',
      imagePreview: null,
      uploadedFile: null,
    };
  },
  computed: {
    pageTitle() {
      return `${this.pageType} 페이지`;
    },
    uploadUrl() {
      return `http://localhost:8090/api/plan/user-${this.pageType.toLowerCase()}`;
    },
    defaultData() {
      const defaults = {
        ERD: {
          sampleUrl: 'https://www.erdcloud.com/',
          imagePreview: 'http://localhost:8090/img/plan/default-erd-image.png'
        },
        USECASE: {
          sampleUrl: 'https://darw.io/',
          imagePreview: 'http://localhost:8090/img/plan/default-usecase-image.png'
        },
        UI: {
          sampleUrl: 'https://www.figma.com/',
          imagePreview: 'http://localhost:8090/img/plan/default-ui-image.png'
        }
      };
      return defaults[this.pageType] || {};
    }
  },
  watch: {
  '$route.params.pageType': function(newType, oldType) {
    if (newType !== oldType) {
      this.fetchPageData();
    }
  },
  '$route.params.pageId': function(newId, oldId) {
    if (newId !== oldId) {
      this.fetchPageData();
    }
  }
},

  methods: {
    createNewPage() {
    axios.post('/api/plan/create-page', {
      projectId: yourProjectId, // 새 페이지를 추가할 프로젝트 ID
      file: yourFile, // 업로드할 파일
      sampleUrl: yourSampleUrl, // 샘플 URL
      sampleImg: yourSampleImg // 샘플 이미지 URL
    })
    .then(response => {
      const newPageData = response.data; 
      this.pages.push(newPageData);
      this.$notify({
        title: '새 페이지 생성됨',
        message: `새로운 페이지가 생성되었습니다: ${newPageData.title}`,
        type: 'success'
      });
    })
    .catch(error => {
      console.error('페이지 생성 실패:', error);
    });
  },
    saveUrl() {
    axios.put(`/api/plan/update-url/${this.pageId}`, { newSampleUrl: this.editableSampleUrl })
      .then(response => {
        this.sampleUrl = this.editableSampleUrl;
        console.log('URL 업데이트 성공:', response);
      })
      .catch(error => {
        console.error('URL 업데이트 실패:', error);
      });
  },
    handleFileUpload(event) {
      const file = event.target.files[0];
      this.imagePreview = URL.createObjectURL(file);
      this.uploadedFile = file;
    },
    fetchPageData() {
      axios.get(`http://localhost:8090/api/plan/${this.pageType.toLowerCase()}-example`, { params: { id: this.pageId } })
        .then(response => {
          const data = response.data;
          this.sampleUrl = data.sampleUrl || this.defaultData.sampleUrl;
          this.imagePreview = `http://localhost:8090${data.sampleImg}` || this.defaultData.imagePreview;
        })
        .catch(error => {
          console.error('데이터를 가져오는 데 실패했습니다:', error);
          this.sampleUrl = this.defaultData.sampleUrl;
          this.imagePreview = this.defaultData.imagePreview;
        });
    },
    uploadFile() {
      const formData = new FormData();
      formData.append('file', this.uploadedFile);
      formData.append('id', this.pageId);

      axios.post(this.uploadUrl, formData, {
        headers: {
          'Content-Type': 'multipart/form-data',
        },
      }).then(response => {
        console.log('이미지 업로드 성공:', response);
        this.fetchPageData();
      }).catch(error => {
        console.error('이미지 업로드 실패:', error);
      });
    },
    beforeRouteUpdate(to, from, next) {
    if (to.params.pageType !== from.params.pageType || to.params.pageId !== from.params.pageId) {
      this.pageType = to.params.pageType;
      this.pageId = to.params.pageId;
      this.fetchPageData();
    }
    next();
  },
  },
  mounted() {
    this.fetchPageData();
  }
};
</script>

<style scoped>
@import "../assets/css/ProjectPlanPage.css";
</style>