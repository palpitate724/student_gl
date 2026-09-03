<template>
    
    <main id="login">
        <form id="login_form" @submit.prevent="login_dl">
            <header class="form-header">
                <h2>欢迎登录</h2>
            </header>

            <section class="form-item">
                <label for="username">用户id</label>
                <input 
                    type="text" 
                    v-model="sid" 
                    placeholder="请输入用户id"
                >
            </section>

            <section class="form-item">
                <label for="password">密码</label>
                <input 
                    type="password" 
                    v-model="spassword" 
                    placeholder="请输入用户密码"
                >
            </section>

            <section class="form-extra">
                <label class="checkbox-label">
                    <input type="checkbox" v-model="rememberMe">
                    记住密码
                </label>
                <a class="forgot-link" href="#">忘记密码？</a>
            </section>

            <p class="error-text" v-if="errorMessage">{{ errorMessage }}</p>
            <input type="submit" value="登录">
            <footer class="form-footer">还没有账号？<RouterLink to="/signup">去注册</RouterLink></footer>
        </form>
        
    </main>
</template>

<script setup>

    import { ref } from 'vue'
    import { useRouter } from 'vue-router'
    import axios from 'axios'
    import { UserStuStore } from '../stores/userstu'

    console.log(import.meta.env.VITE_API)

    const router = useRouter()
    const userstustore = UserStuStore()
    const sid = ref('')
    const spassword = ref('')
    const rememberMe = ref(false)
    const errorMessage = ref('')
    const fhui = ref('')

    const login_dl =async () => {
        errorMessage.value = ''

        if (!sid.value.trim()) {
            errorMessage.value = '请输入用户id'
            return
        }
        if(sid.value.length != 10){
            errorMessage.value = '用户id长度应为10位'
            return
        }
        if (!spassword.value) {
            errorMessage.value = '请输入密码'
            return
        }
        if (spassword.value.length < 6) {
            errorMessage.value = '密码长度不能少于6位'
            return
        }

        fhui.value = await userstustore.login({"sid": sid.value, "spassword": spassword.value})
        console.log(fhui.value)
        if (fhui.value == 200) {
            alert('登录成功,即将跳转到主页')
            router.push('/zhuye')
            return
        }
        if (fhui.value == 409) {
            alert('用户不存在,请重新输入')
            return
        }
        if (fhui.value == 500) {
            alert('网络繁忙,请稍后再试')
            return
        }
        
    }

</script>

<style src="../assets/css/login.css">

</style>