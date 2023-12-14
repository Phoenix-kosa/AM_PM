import axios from "axios";

const apiConfig = {
  baseURL: "http://localhost:8090/",
  headers: {
    "Content-Type": "application/json",
  },
};

const apiFileConfig = {
  baseURL: "http://localhost:8090/",
  headers: {
    "Content-Type": "multipart/form-data",
    accept: "application/json",
  },
};

export const authApi = axios.create(apiConfig);
export const apiInstance = axios.create(apiConfig);
export const authFileApi = axios.create(apiFileConfig);

// 인터 셉터
authApi.interceptors.request.use((config) => {
  config.headers.Authorization = getLocalStorageToken();
  return config;
});

const getLocalStorageToken = () => {
  const token = sessionStorage.getItem("access-token");
  return token;
};
