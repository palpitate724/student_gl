import service from "./request.js";


const login = import.meta.env.VITE_API_LOGIN;//登录
const signup = import.meta.env.VITE_API_SIGNUP;//注册
const stusel = import.meta.env.VITE_API_SELECT;//查询
const stuins = import.meta.env.VITE_API_INSERT;//插入
const studelr = import.meta.env.VITE_API_DELETE;//删除
const studelrhuifu = import.meta.env.VITE_API_HUIFU;//恢复
const stuup = import.meta.env.VITE_API_UPDATE;//更新
const stucount = import.meta.env.VITE_API_STUCOUNT;//统计

console.log(import.meta.env.VITE_API+stusel);

//登录
export const loginapi = function (data) {
    return service.post(login, data);
};

//注册
export const signupapi = function (data) {
    return service.post(signup, data);
};

//查询
export const stuselapi = function (data) {
    return service.post(stusel, data);
};

//插入
export const stuinsapi = function (data) {
    return service.post(stuins, data);
};

//逻辑删除
export const studelrapi = function (data) {
    return service.post(studelr, data);
};
//恢复
export const studelrhfapi = (data) => {
    return service.post(studelrhuifu, data);
};

//修改
export const stuupapi = function (data) {
    return service.post(stuup, data);
};

//统计
export const stucountapi = (data) => {
    return service.post(stucount,data);
};