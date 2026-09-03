import { defineStore } from 'pinia'
import { ref } from 'vue'
import axios from 'axios'
import { loginapi,signupapi } from '../components/api/apijihe'

export const UserStuStore = defineStore ('userstu',{
    state: () => ({
        sid : '',
        sname : '',
        spassword : '',
        sjiaose : 0,
        loginyesno : false,
        token : ''
    }),
    getters: {
    },
    actions: {
        //更新状态
        setsta(statefan){
            this.$reset()
            this.$patch(statefan)
        },
        //登录
        async login (userjson){
            const res = await loginapi(userjson)
            this.setsta(res.data.data)
            if ( res.status !== 200 ) {
                console.log("网络繁忙，请稍后重试") 
                return 500
            }
            if ( res.data.status === 200 ){
                this.loginyesno=true
                console.log(this.sid+"  "+this.sname+" "+this.token+"登录成功")
                return 200
            }
            if ( res.data.status === 409 ){
                console.log("登录失败，用户不存在")
                return 409
            }
            if ( res.data.status === 500 ){
                console.log("网络繁忙，请稍后重试") 
                return 500
            }
            
        },
        //退出登录
        logout (){
            this.$reset()
        },

        //注册
        async signup(signjsondata){
            const res = await signupapi(signjsondata);
            if ( res.status !== 200 ) {
                console.log("网络繁忙，请稍后重试") 
                return 500
            }
            if ( res.data.status === 200 ){
                console.log("注册成功"+res.data.data.sid)
                return 200
            }
            if ( res.data.status === 409 ){
                console.log("注册失败，用户已存在"+res.data.data.sid)
                return 409
            }
            if ( res.data.status === 500 ){
                console.log("网络繁忙，请稍后重试") 
                return 500
            }
        }
    },
    persist: true
})