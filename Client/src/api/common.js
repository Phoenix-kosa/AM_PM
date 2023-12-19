import axios from "axios";
import { authApi, apiInstance, expireToken } from "./config";

const api = async (url, method, data) => {
  return (
    await axios({
      method: method,
      url,
      data,
    }).catch((e) => {
      console.log(e);
    })
  ).data;
};

export { api };

export const getMyInfoReq = async () => {
  const response = await authApi.get("/api/user");
  return response;
};

export const editMyInfoReq = async (updateData) => {
  const response = await authApi.put("/api/user", updateData);
  return response;
};

export const getNoti = async (projectId) => {
  const response = await apiInstance.get(`/api/notice/${projectId}`);
  return response;
};

export const addNoti = async (formdata) => {
  const response = await authApi.post("/api/notice", formdata);
  return response;
};

export const deleteNoti = async (noticeId) => {
  const response = await apiInstance.delete(`/api/notice/${noticeId}`);
  return response;
};

export const editNoti = async (noticeId, formdata) => {
  const response = await apiInstance.put(`/api/notice/${noticeId}`, formdata);
  return response;
};

// 간트차트
export const getTask = async (projectId) => {
  const response = await apiInstance.get(`/api/task/${projectId}`);
  return response;
};
