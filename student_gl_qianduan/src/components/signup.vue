<template>
    <main id="signup">
        <form id="signup_form" @submit.prevent="signup_zc">
            <header class="form-header">
                <h2>注册新账户</h2>
            </header>

            <section class="form-item">
                <label for="username">用户id</label>
                <input 
                    type="text" 
                    v-model="sid" 
                    placeholder="请输入用户id"
                    autocomplete="username"
                >
            </section>

            <section class="form-item">
                <label for="username">姓名</label>
                <input 
                    type="text" 
                    v-model="sname" 
                    placeholder="请输入姓名"
                >
            </section>

            <section class="form-item">
                <label for="password">密码</label>
                <input 
                    type="password" 
                    v-model="spassword" 
                    placeholder="请输入密码" 
                >
            </section>

            <section class="form-item">
                <label for="password">确认密码</label>
                <input 
                    type="password" 
                    v-model="spassword_tet"
                    placeholder="请再次输入密码" 
                >
            </section>

            <section class="form-item">
                <label for="role">角色</label>
                <select id="role" v-model="sjiaose">
                    <option value="0">学生</option>
                    <option value="1">教师</option>
                    <option value="2">管理员</option>
                </select>
            </section>

            <p class="error-text" v-if="errorMessage">{{ errorMessage }}</p>
            <input type="submit" value="注册" />
            <footer class="form-footer">已有账号？<RouterLink to="/login">去登录</RouterLink></footer>
        </form>
    </main>
</template>

<script setup>
    import { ref } from 'vue'
    import axios from 'axios'
    import { useRouter } from 'vue-router'
    import { signupapi } from './api/apijihe'
    import { UserStuStore } from '../stores/userstu'

    const sid = ref('')
    const sname = ref('')
    const spassword = ref('')
    const spassword_tet = ref('')
    const sjiaose = ref('1')
    const errorMessage = ref('')
    const router = useRouter()
    const fhui = ref('')

    const userstusig = UserStuStore()

    const signup_zc = async () => {
        errorMessage.value = ''

        if (!sid.value.trim()) {
            errorMessage.value = '请输入用户id'
            return
        }
        if(sid.value.length != 10){
            errorMessage.value = '用户id为10位'
        }
        if (!sname.value.trim()) {
            errorMessage.value = '请输入姓名'
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
        if (spassword.value !== spassword_tet.value) {
            errorMessage.value = '两次输入的密码不一致'
            return
        }

        const jsondata = {
            "sid": sid.value,
            "sname": sname.value,
            "spassword": spassword.value,
            "sjiaose": sjiaose.value
        }

        fhui.value = await userstusig.signup(jsondata)
        
        if (fhui.value === 200) {
            alert('注册成功，即将跳转登录页登录')
            router.push('/login')
            return
        }
        if (fhui.value === 409) {
            alert('用户已存在，请直接登录')
            router.push('/login')
            return
        }
        if (fhui.value === 500) {
            alert('网络繁忙，请稍后再试')
            return
        }
        
        
    }
</script>

<style src="../assets/css/signup.css"></style>