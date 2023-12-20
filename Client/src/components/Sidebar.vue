<template>
    <div v-if="modalData != null" class="modal">
    <span class="xButton" @click="close">X</span>
    <div class="imgDiv">
      <img v-if="modalData.profileImg" :src="modalData.profileImg">
    </div>
    <div class="textDiv">
      <span class="nicknameModal">{{ modalData.nickname }}</span>
      <span class="emailModal">{{ modalData.email }}</span>
    </div>
    <div class="routerDiv">
      <RouterLink class="router" :to="{name: 'Chat', query: {user: modalData.userId}}">정보 확인</RouterLink>
    </div>
  </div>
  <v-card>
    <v-layout>
      <v-navigation-drawer id="sidebar_container" permanent>
        <p class="p">기획</p>
        <v-list density="compact" nav>
          <v-list-item
            prepend-icon="mdi-home-city"
            title="요구사항 명세서"
            :to="`/srs/${currentProjectId}`"

            style="color: white"
          ></v-list-item>

          

          <v-list-item
          prepend-icon="mdi-account"
          title="Usecase"
          :to="`/usecase/${currentProjectId}`"
          style="color: white"
          ></v-list-item>

          <v-list-item
            prepend-icon="mdi-account-group-outline"
            title="ERD"
            :to="`/erd/${currentProjectId}`"
            style="color: white"
      ></v-list-item>

          <v-list-item
            prepend-icon="mdi-account-group-outline"
            title="UI"
            :to="`/ui/${currentProjectId}`"
            style="color: white"
          ></v-list-item>

        <v-list-item
          v-for="page in etcPages"
          :key="page.id"
          prepend-icon="mdi-file-document-outline"
          :title="page.title"
          :to="`/${page.title}/${currentProjectId}`"
          style="color: white"
    ></v-list-item>

        </v-list>
        <v-container>
          <v-row align="end" justify="end">
          <v-col cols="auto">
        <v-btn @click="createNewEtcPage" icon="mdi-plus" size="small">+</v-btn>
          </v-col>
          </v-row>
      </v-container>
        <v-divider
          style="border-top-color: white; border-top-width: 2px"
        ></v-divider>
        
        
        <p class="p">개발</p>
        <v-list density="compact" nav>
          <v-list-item
            prepend-icon="mdi-home-city"
            title="공지사항/간트차트"
            to="/dev" 
            style="color: white"
          ></v-list-item>
          <v-list-item
            prepend-icon="mdi-account"
            title="채팅"
            to="/team-chat"
            style="color: white"
          ></v-list-item>
        </v-list>
        <v-divider
          style="border-top-color: white; border-top-width: 2px"
        ></v-divider>

        <p class="p">멤버</p>
        <v-list density="compact" nav >
          <v-list-item
            prepend-icon="mdi-account"
            :title= item.nickName
            :value= item.nickName
            style="color: white"
            v-for="item in member_list" :key="item.id" @click="show(item.userId)"></v-list-item>
          <!-- <v-list-item
            prepend-icon="mdi-account"
            title="민재"
            value="민재"
            style="color: white"
          ></v-list-item>
          -->
          <v-divider
          style="border-top-color: white; border-top-width: 2px"
        ></v-divider>     
         </v-list>
        
        <p class="p">관리</p>
        <v-list density="compact" nav>       
        <v-list-item
            prepend-icon="mdi-account"
            title="프로젝트 수정"
            to="modify-project"
            style="color: white"
          ></v-list-item>
          <v-list-item
            prepend-icon="mdi-account"
            title="멤버 관리"
            style="color: white"
            to="member-list"
          ></v-list-item>

        </v-list>
        <v-divider
          style="border-top-color: white; border-top-width: 2px"
        ></v-divider>
        <v-list density="compact" nav>
          <v-list-item
            prepend-icon="mdi-account"
            title="문의하기"
            to="question"
            style="color: white"
          ></v-list-item>
          </v-list>


        <v-list-item nav>
          <template v-slot:append>
            <v-btn variant="text" icon="mdi-chevron-left"></v-btn>
          </template>
        </v-list-item>
      </v-navigation-drawer>
    </v-layout>
  </v-card>

  <v-dialog v-model="showModal" persistent max-width="300px">
    <v-card>
      <v-card-title>새 페이지 생성</v-card-title>
      <v-card-text>
        <v-text-field v-model="newPageTitle" label="페이지 제목" :rules="titleRules"></v-text-field>
      </v-card-text>
      <v-card-actions>
        <v-spacer></v-spacer>
        <v-btn color="blue darken-1" text @click="createPage">생성</v-btn>
      </v-card-actions>
    </v-card>
  </v-dialog>

</template>

<script>

import axios from 'axios';
export default {
  watch: {
    etcPages(newVal, oldVal) {
      console.log('etcPages 변경됨:', newVal);
    }
  },
  props: {
    currentProjectId: {
    type: Number,
    required: false, 
  }
  },
  data() {
    return {
      drawer: true,
      rail: true,
      etcPages: [],
      currentProjectId: sessionStorage.getItem("projectId") || 0,
      showModal: false,
      newPageTitle: '',
      titleRules: [
        v => !!v || '제목은 필수입니다',
        v => /^[a-zA-Z\s]*$/.test(v) || '영어 제목만 가능합니다'
      ]

    };
  },
  methods: {
    createNewEtcPage() {
    this.showModal = true;
  },

    createPage() {
      localStorage.clear();
      console.log('createPage method called');

    this.addEtcPage(this.newPageTitle);
    this.showModal = false;
    this.newPageTitle = '';
    console.log(this.etcPages);

  },
    
    requestProjectIdUpdate(newProjectId) {
      this.$emit('update-project-id', newProjectId);
    },

    addEtcPage(newPageTitle) {
        this.createNewPage(newPageTitle).then(response => {
            if (response && response.data) {
                this.etcPages = [...this.etcPages, response.data];
                 localStorage.setItem('etcPages', JSON.stringify(this.etcPages));
                // 로컬스토리지 사용 부분 

                // this.$router.push(`/${response.data.title}/${this.currentProjectId}`); 페이지 생성 후 이동 하는
            }
        }).catch(error => {
            console.error('Page creation failed:', error);
        });
  },
  createNewPage(title) {
    console.log('Request URL:', this.createPageUrl);
    return axios.post('http://localhost:8090/api/plan/create-page', { title: title, projectId: this.currentProjectId });
  }
},

mounted(){
  const savedPages = localStorage.getItem('etcPages');
  if (savedPages) {
    this.etcPages = JSON.parse(savedPages);
  }
 },    


 computed: {
    isPlanningVisible() {
      return this.currentProjectId || this.etcPages.length > 0;
    }
  }
};
</script>


<style scoped>
@import "../assets/css/sidebar.css";
</style>

<script setup>
import { ref } from "vue";
import { expireToken } from "../api/config";
import axios from "axios";
const drawer = ref(true);
const rail = ref(true);
const projectId = sessionStorage.getItem("projectId");
const modalData = ref(null);

let member_list = ref([])
function loadData(){
  axios.get(`http://localhost:8090/api/members/` + projectId, {
    headers: { 
        "Authorization" : sessionStorage.getItem("access-token") 
    }
  })
  .then((response) => {
    member_list.value = response.data;
  })
  .catch((err) => {
    console.log(err)
    expireToken(err, loadData)
  });
}
window.onload = loadData();

function show(userId) {
  axios.get('http://localhost:8090/api/user/' + userId, {
    headers: { 
        "Authorization" : sessionStorage.getItem("access-token") 
    }
  })
  .then((response) => {
    modalData.value = response.data;
    console.log(modalData.value)
  })
  .catch((err) => {
    console.log(err)
    expireToken(err, show(userId))
  });
}

function close() {
  modalData.value = null;
}
</script>

<style scoped>
.imgDiv {
  width: 200px;
  height: 200px;
  margin-bottom: 20px;
  background-color: white;
  border-radius: 50%;
}
.modal img {
  width: 100%;
  height: 100%;
  border-radius: 50%;
}
.textDiv {
  display: flex;
  flex-direction: column;
  align-items: center;
}

.xButton {
  color: white;
  font-weight: bold;
  position: absolute;
  top: 10px;
  right: 10px;
  cursor: pointer;
}
.modal {
  position: fixed;
  top: 50%;
  left: 50%;
  transform: translate(-95%, -3%);
  width: 400px;
  height: 400px;
  background-color: rgb(16 22 30 / 70%);
  border: #166adc solid;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  z-index: 1000;
}

.routerDiv {
  background-color: white;
  width: 150px;
  height: 50px;
  border-radius: 10px;
  position: relative;
}
.router {
  text-decoration: none;
  color: #166adc;
  font-weight: bold;
  position: absolute;
  left: 50%;
  top: 50%;
  transform: translate(-50%, -50%);
}
.nicknameModal {
  color: #ffffff;
  margin-bottom: 10px;
  font-weight: bold;
}
.emailModal {
  color: #ffffff;
  margin-bottom: 10px;
}
</style>