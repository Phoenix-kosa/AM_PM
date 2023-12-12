<template>
  <div class="c">
    <h1>{{ pageTitle }}</h1>
    <div>
      <p>참고 사이트 : <a :href="sampleUrl" target="_blank" class="url">{{ sampleUrl }}</a></p>
      <button @click="uploadImage" class="button1">이미지 업로드 : </button>
      <input type="file" id="imgUpload" style="display:none;" @change="onFileChange" />
      <label for="imgUpload"> 이미지 추가</label>
      <br><br>
      <img :src="imagePreview" alt="Image preview" />
    </div>
  </div>
</template>

<script>
export default {
  props: {
    pageType: String,
  },
  data() {
    return {
      sampleUrl: 'https://www.erdcloud.com',
      imageFile: null,
      imagePreview: '/assets/images/erd.png'
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