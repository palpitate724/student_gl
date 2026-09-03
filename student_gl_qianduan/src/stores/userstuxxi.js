import { defineStore } from 'pinia'
import { ref } from 'vue'
import axios from 'axios'
import { stuselapi,stuinsapi,stuupapi,stucountapi,studelrhfapi,studelrapi } from '../components/api/apijihe'


export const UserStuXxiStore = defineStore('userstuxxi',{
    state: () =>({
        stusel : [],
        stuselcount : 0
    }),
    getters: {
        
    },
    actions: {
        //信息查询返回
        async userstusel(jsondata){
            let res = await stuselapi(jsondata)
            this.stusel = res.data.data
            if (res.status !== 200){
                console.log("网络繁忙，请稍后重试")
                return 500
            }
            if (res.data.status === 200){
                res = await await stucountapi(jsondata)
                this.stuselcount = res.data.data
                console.log("stusel查询成功")
                return 200
            }
            
        },
        //逻辑删除恢复
        async userdelrhf(id,seljsondata){
            const res = await studelrhfapi(this.stusel[id])
            if ( res.data.status === 200 ){
                await this.userstusel(seljsondata)
                console.log("恢复成功")
            }
            else if ( res.data.status === 500 ){
                console.log("网络繁忙，请稍后重试")
            }
            
        },

        //逻辑删除
        async userstudelr(deljsondata,seljsondata){
            const res = await studelrapi(deljsondata)
            if (res.status !== 200){
                console.log("网络繁忙，请稍后重试")
                return 500
            }
            if ( res.data.status === 200){
                console.log("删除成功")
                await this.userstusel(seljsondata)
                return 200
            }
            if ( res.data.status === 500){
                console.log("网络繁忙，请稍后重试")
                return 500
            }
        },

        //学生信息添加
        async userstuins(insjsondata,seljsondata){
            const res = await stuinsapi(insjsondata)
            if (res.status !== 200){
                console.log("网络繁忙，请稍后重试")
                return 500
            }
            if ( res.data.status === 200){
                await this.userstusel(seljsondata)
                console.log("添加成功")
                return 200
            }
            if ( res.data.status === 500){
                console.log("网络繁忙，请稍后重试")
                return 500
            }
        },

        //学生信息修改
        async userstuup(insjsondata,seljsondata){
            const res = await stuupapi(insjsondata)
            if (res.status !== 200){
                console.log("网络繁忙，请稍后重试")
                return 500
            }
            if ( res.data.status === 200){
                await this.userstusel(seljsondata)
                console.log("修改成功")
                return 200
            }
            if ( res.data.status === 500){
                console.log("网络繁忙，请稍后重试")
                return 500
            }
        },
    },
    persist : true
})