<!--
<template>
  <div class="project-plan-page">
    <h1>{{ pageTitle }}</h1>
    <div>
      <p>참고 사이트: <a :href="sampleUrl" target="_blank">{{ sampleUrl }}</a></p>
      <input type="file" @change="handleFileUpload" />
      <button @click="uploadFile">파일 업로드</button>
      <img :src="imagePreview" alt="Preview" v-if="imagePreview" />
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
          this.imagePreview = data.sampleImg || this.defaultImage;
        })
        .catch(error => {
          console.error('데이터를 가져오는 데 실패했습니다:', error);
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
    }
  },
  mounted() {
    this.fetchPageData();
  }
};
</script>

<style>
@import "../assets/css/ProjectPlanPage.css";
</style>

  <template>
  <div class="c">
    <h1>{{ pageTitle }}</h1>
    <div>
      <p>참고 사이트: <a :href="sampleUrl" target="_blank" class="url">{{ sampleUrl }}</a></p>
      <button @click="uploadImage" class="butto n1">이미지 업로드: </button>
      <input type="file" id="imgUpload" style="display:none;" @change="onFileChange" />
      <label for="imgUpload"> &nbsp;이미지 첨부</label>
      <br><br>
      <img :src="imagePreview" alt="Image preview" />
    </div>
  </div>

</template>

<script>
import erdImage from '@/assets/images/erd.png';

export default {
  props: {
    pageType: String,
  },
  data() {
    return {
      sampleUrl: 'https://www.erdcloud.com',
      imageFile: null,
      imagePreview: erdImage,
    };
  },
  computed: {
    pageTitle() {
      return this.pageType + ' Page';
    },
  },
  methods: {
    onFileChange(e) {
      const file = e.target.files[0];
      this.imageFile = file;
      this.previewImage(file);
    },
    previewImage(file) {
      const reader = new FileReader();
      reader.readAsDataURL(file);
      reader.onload = (e) => {
        this.imagePreview = e.target.result;
      };
    },
    uploadImage() {
    const formData = new FormData();
    formData.append('file', this.imageFile);
    axios.post(' ', formData, {
      headers: {
        'Content-Type': 'multipart/form-data',
      },
    }).then(response => {
      console.log('이미지 업로드 성공:', response);
    }).catch(error => {
      console.error('이미지 업로드 실패:', error);
    });
  },
  },
};
</script>

<style>
@import "../assets/css/ProjectPlanPage.css";
</style>
-->