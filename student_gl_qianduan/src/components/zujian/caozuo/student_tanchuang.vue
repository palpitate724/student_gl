<template>


    <el-dialog v-model="dialogFormVisible" :title="frommj[fromname]" width="500">
    <el-form :model="from">
      <el-form-item label="学号" :label-width="formLabelWidth">
        <el-input v-if="fromname===1" v-model="from.sid" autocomplete="off"  />
        <el-input v-if="fromname===2" v-model="from.sid" autocomplete="off" disabled />
      </el-form-item>
      <el-form-item label="姓名" :label-width="formLabelWidth">
        <el-input v-model="from.sname" autocomplete="off" />
      </el-form-item>
      <el-form-item label="年龄" :label-width="formLabelWidth">
        <el-input v-model="from.sage" autocomplete="off" />
      </el-form-item>
      <el-form-item label="性别" :label-width="formLabelWidth">
        <el-input v-model="from.ssex" autocomplete="off" />
      </el-form-item>
      <el-form-item label="专业" :label-width="formLabelWidth">
        <el-input v-model="from.ssion" autocomplete="off" />
      </el-form-item>
    </el-form>
    <template #footer>
      <div class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取消</el-button>
        <el-button type="primary" @click="stuinsandupdate()">
          {{ cznamemj[czname] }}
        </el-button>
      </div>
    </template>
  </el-dialog>
</template>

<script setup>

import { ref, reactive } from 'vue'
import { UserStuXxiStore } from '../../../stores/userstuxxi'

const userstuxxi = UserStuXxiStore()

const dialogFormVisible = ref(false)
const formLabelWidth = '50px'

const fromname = ref(0)
const czname = ref(0)
const cznamemj = {
    1: '添加',
    2: '修改'
}
const frommj = {
    1: '添加学生信息',
    2: '修改学生信息'
}

const id = ref(null) // 用于存储当前操作的学生索引
let seljsondata // 用于存储当前操作的学生数据

const from = reactive({
    sid: '',
    sname: '',
    sage: '',
    ssex: '',
    ssion: ''
})
//外部调用
const xianshi = (cznameref,fromnameref,id,seljsondata1) =>{
    czname.value = cznameref
    fromname.value = fromnameref
    Object.assign(from, userstuxxi.stusel[id]) // 将当前学生信息复制到 from 对象中
    seljsondata = seljsondata1
    dialogFormVisible.value = true
}

defineExpose({
    xianshi
})

const stuinsandupdate = async () => {
    if (czname.value === 1) {
        //添加逻辑0012011328

        await userstuxxi.userstuins(from,seljsondata)
        console.log('添加学生信息:', from)
    } else if (czname.value === 2) {
        //修改逻辑
        await userstuxxi.userstuup(from,seljsondata)
    }
    dialogFormVisible.value = false
}

</script>