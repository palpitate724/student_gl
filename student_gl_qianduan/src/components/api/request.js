import axios from 'axios';
import { UserStuStore } from '../../stores/userstu';
import router from '../../router/index.js';

const VITE_API = import.meta.env.VITE_API;

const service = axios.create({
  baseURL: VITE_API,
  timeout: 5000
});

service.interceptors.request.use(
  config => {
    const userStore = UserStuStore();
    const token = userStore.token;
    if (token!== '' && token !== null && token !== undefined) {
      config.headers.token = token;
    }
    return config;
  },
  error => {
    return Promise.reject(error);
  }
);

service.interceptors.response.use(
  response => {
    //自定义状态码处理
    return response;
  },
  error => {
    console.log("响应拦截器：", error);
    console.log("登录过期，请重新登录");
    router.push('/login');
    return Promise.reject(error);
  }
);

export default service;
