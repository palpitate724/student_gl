<template>
    <td class="action-cell">
        <button class="btn-delete" @click="studelrbut()">
            删除
        </button>
        <button class="btn-edit" @click="stuinszj.xianshi(2,2,id,seljsondata)">
            修改
        </button>
    </td>
    <instanchaung ref="stuinszj" />
</template>

<script setup>

    import { ref } from 'vue'
    import axios from 'axios'
    import { UserStuXxiStore } from '../../../stores/userstuxxi'
    import instanchaung from './student_tanchuang.vue'

    const stuinszj = ref(null)
    


    const userstuxxi = UserStuXxiStore()
    

    let studentlist = defineProps({
        id: Number,
        seljsondata: Object
        
    })
    let id=studentlist.id
    const studelrbut = function(){
        
        const deljsondata={
            "sid" : userstuxxi.stusel[id].sid,
            "sname" : null,
            "sage" : null,
            "ssex" : null,
            "ssion" : null
        }
        console.log(userstuxxi.stusel[id].sid+" "+id)
        userstuxxi.userstudelr(deljsondata,studentlist.seljsondata)        
    }

</script>

<style>

.action-cell {
    display: flex;
    gap: 6px;
    align-items: center;
    justify-content: center;
    padding: 4px 8px;
    white-space: nowrap;
}

/* ===== 通用按钮基础样式 ===== */
.action-cell button {
    padding: 4px 12px;
    border: none;
    border-radius: 12px;
    font-size: 11px;
    font-weight: 500;
    cursor: pointer;
    transition: all 0.25s ease;
    letter-spacing: 0.3px;
    line-height: 1.8;
    min-width: 44px;
    position: relative;
    overflow: hidden;
}

/* ===== 修改按钮（淡蓝色主调） ===== */
.btn-edit {
    background: #e8f4fd;
    color: #3b82f6;
    box-shadow: 0 1px 3px rgba(59, 130, 246, 0.12);
}

.btn-edit:hover {
    background: #d4eafc;
    color: #2563eb;
    box-shadow: 0 2px 8px rgba(59, 130, 246, 0.2);
    transform: translateY(-1px);
}

.btn-edit:active {
    transform: translateY(0px) scale(0.96);
    box-shadow: 0 1px 3px rgba(59, 130, 246, 0.1);
}

/* ===== 删除按钮（淡红色点缀） ===== */
.btn-delete {
    background: #fef2f2;
    color: #ef4444;
    box-shadow: 0 1px 3px rgba(239, 68, 68, 0.1);
}

.btn-delete:hover {
    background: #fee2e2;
    color: #dc2626;
    box-shadow: 0 2px 8px rgba(239, 68, 68, 0.15);
    transform: translateY(-1px);
}

.btn-delete:active {
    transform: translateY(0px) scale(0.96);
    box-shadow: 0 1px 3px rgba(239, 68, 68, 0.08);
}

/* ===== 点击波纹效果（可选） ===== */
.action-cell button::after {
    content: '';
    position: absolute;
    inset: 0;
    background: rgba(255, 255, 255, 0.3);
    border-radius: 12px;
    opacity: 0;
    transition: opacity 0.3s;
}

.action-cell button:active::after {
    opacity: 1;
    transition: opacity 0s;
}
</style>