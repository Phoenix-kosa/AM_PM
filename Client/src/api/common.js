import axios from "axios";
import { authApi } from "./config";

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
