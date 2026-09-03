<template>
  <section class="student-display">


    <form class="student-card" @submit.prevent="searchStudents">
      <label for="student-id">学号</label>
      <label for="student-name">姓名</label>
      <label for="student-age">年龄</label>
      <label for="student-gender">性别</label>
      <label for="student-major">专业</label>

      <input id="student-id" v-model="sid" type="text" placeholder="请输入学号" />
      <input id="student-name" v-model="sname" type="text" placeholder="请输入姓名" />
      <input id="student-age" v-model="sage" type="text" placeholder="请输入年龄" />
      <input id="student-gender" v-model="ssex" type="text" placeholder="请输入性别" />
      <input id="student-major" v-model="ssion" type="text" placeholder="请输入专业" />
      
      <footer class="button-row">
        <button type="submit" class="primary-btn" @click.prevent="chaxun">查询</button>
        <button type="button" class="secondary-btn" @click.prevent="resetQuery">清空</button>
      </footer>

      <p class="hint-text">提示：支持学号精确匹配与姓名模糊匹配。输入条件可进行筛选。</p>
    </form>

    <div class="tjia-panel">
      <button @click="stuinsref.xianshi(1,1,null,seljsondata)" class="primary-btn">添加学生</button>
      <inszujian ref="stuinsref" />
    </div>

    <section class="result-panel">

      <section v-if="userstuxxi.stuselcount === 0" class="empty-state">
        <p>暂无匹配记录，可修改查询条件后重试。</p>
      </section>

      <table v-else class="result-table">
        <thead>
          <tr>
            <th>学号</th>
            <th>姓名</th>
            <th>年龄</th>
            <th>性别</th>
            <th>专业</th>
            <th>操作</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="(student,id) in userstuxxi.stusel" :key="id">
            <td>{{ student.sid }}</td>
            <td>{{ student.sname }}</td>
            <td>{{ student.sage }}</td>
            <td>{{ student.ssex }}</td>
            <td>{{ student.ssion }}</td>
            <td><deletandupdate
               :id = "id"
               :seljsondata = "seljsondata"
            ></deletandupdate></td>
            
          </tr>
        </tbody>
      </table>

      <div class="pagination-panel" name="fye">
        <div class="page-size-control">
          <span>每页显示数据量：</span>
          <el-select-v2
            v-model="maiyeshul"
            :options="options"
            class="page-size-select"
            @change="stusel()"
          ></el-select-v2>
        </div>

        <div class="pagination-right">
          <span class="record-summary">共 {{ userstuxxi.stuselcount }} 条记录</span>

          <el-pagination
            :page-size="maiyeshul"
            v-model:current-page="currentPage"
            background
            layout="prev, pager, next"
            :total="userstuxxi.stuselcount"
            @click="stusel()"
          />
        </div>
      </div>
    </section>
  </section>
</template>

<script setup>
import { reactive, ref, computed ,onMounted } from 'vue'
import axios from 'axios'
import deletandupdate from './student_delete.vue'
import { UserStuXxiStore } from '../../../stores/userstuxxi'
import inszujian from './student_tanchuang.vue'

const stuinsref = ref(null)

const sid = ref('')
const sname = ref('')
const sage = ref('')
const ssex = ref('')
const ssion = ref('')

const maiyeshul = ref(10)
const currentPage = ref(1)

const userstuxxi = UserStuXxiStore()

const seljsondata = computed(() => ({
   "sid": sid.value,
    "sname": sname.value,
    "sage": sage.value,
    "ssex": ssex.value,
    "ssion": ssion.value,
    "sdelete" : 0,
    "ts": currentPage.value,
    "shul": maiyeshul.value
}))


const options = Array.from({ length: 20 }).map((_, idx) => ({
  value: idx + 1,
  label: `${idx + 1}`,
}))


const chaxun = async () =>{
  await userstuxxi.userstusel(seljsondata.value)
}


const stusel = async () =>{
  console.log(seljsondata.value)
  await userstuxxi.userstusel(seljsondata.value)
}

onMounted( async () => {
  stusel()
})


function searchStudents() {
  // 由计算属性自动更新结果，仅阻止默认提交
}

function resetQuery() {
  sid.value = ''
  sname.value = ''
  sage.value = ''
  ssex.value = ''
  ssion.value = ''

  stusel()
}
</script>

<style scoped>
.student-display {
  min-height: 100vh;
  padding: 30px 32px 40px;
  background: linear-gradient(180deg, #eef4ff 0%, #f8fbff 100%);
}

.page-header {
  display: flex;
  align-items: flex-end;
  justify-content: space-between;
  gap: 20px;
  padding: 28px 30px;
  margin-bottom: 24px;
  border-radius: 28px;
  background: rgba(255, 255, 255, 0.92);
  border: 1px solid rgba(59, 130, 246, 0.18);
  box-shadow: 0 24px 44px rgba(59, 130, 246, 0.08);
}

.page-header h1 {
  margin: 0;
  font-size: clamp(1.75rem, 2.2vw, 2.4rem);
  color: #0f172a;
  letter-spacing: 0.02em;
}

.page-header p {
  margin: 8px 0 0;
  color: #475569;
  line-height: 1.7;
  max-width: 720px;
}

.student-card {
  position: sticky;
  top: 0;
  z-index: 10;
  display: grid;
  grid-template-columns: repeat(5, minmax(0, 1fr));
  grid-auto-rows: auto;
  align-items: start;
  gap: 18px;
  padding: 15px;
  background: rgba(255, 255, 255, 0.96);
  border: 1px solid rgba(148, 163, 184, 0.18);
  border-radius: 28px;
  box-shadow: 0 22px 36px rgba(96, 165, 250, 0.12);
  
}

.student-card label {
  
  color: #475569;
  font-size: 0.82rem;
  font-weight: 600;
  text-transform: uppercase;
  letter-spacing: 0.08em;
  margin-bottom: 0;
  align-self: center;
  padding: 4px 6px;
}

.student-card input {
  width: 100%;
  min-height: 20px;
  max-height: 40px;
  padding: 14px 16px;
  border: 1px solid rgba(96, 165, 250, 0.24);
  border-radius: 16px;
  background: rgba(255, 255, 255, 0.98);
  color: #0f172a;
  font-size: 0.95rem;
  transition: border-color 0.24s ease, box-shadow 0.24s ease;
}

.student-card input::placeholder {
  color: rgba(71, 85, 105, 0.48);
}

.student-card input:focus {
  outline: none;
  border-color: rgba(59, 130, 246, 0.85);
  box-shadow: 0 0 0 6px rgba(59, 130, 246, 0.12);
}

.button-row {
  height: 45px;
  grid-column: 1 / -1;
  display: flex;
  justify-content: flex-end;
  gap: 14px;
  margin-top: 6px;
}

.primary-btn,
.secondary-btn {
  min-width: 150px;
  padding: 14px 18px;
  border-radius: 18px;
  border: 1px solid transparent;
  font-weight: 700;
  letter-spacing: 0.02em;
  cursor: pointer;
  transition: transform 0.22s ease, box-shadow 0.22s ease, opacity 0.22s ease;
}

.primary-btn {
  background: linear-gradient(135deg, #60a5fa 0%, #3b82f6 100%);
  color: #ffffff;
  box-shadow: 0 12px 28px rgba(59, 130, 246, 0.22);
}

.secondary-btn {
  background: rgba(255, 255, 255, 0.95);
  color: #0f172a;
  border-color: rgba(96, 165, 250, 0.28);
  box-shadow: inset 0 0 0 1px rgba(148, 163, 184, 0.15);
}

.primary-btn:hover,
.secondary-btn:hover {
  transform: translateY(-1px);
  opacity: 0.98;
}

.hint-text {
  height: 30px;
  grid-column: 1 / -1;
  margin: 0px;
  color: #64748b;
  font-size: 0.95rem;

}

.tjia-panel {
  margin-top: 18px;
  display: flex;
  justify-content: flex-end;
  gap: 12px;
}

.tjia-panel .primary-btn {
  min-width: 170px;
  padding: 14px 24px;
  border-radius: 20px;
  background: linear-gradient(135deg, #4f46e5 0%, #2563eb 100%);
  box-shadow: 0 16px 30px rgba(37, 99, 235, 0.18);
}

.tjia-panel .primary-btn:hover {
  transform: translateY(-1px);
}

.result-panel {
  margin-top: 28px;
  padding: 26px;
  background: rgba(255, 255, 255, 0.97);
  border-radius: 28px;
  border: 1px solid rgba(148, 163, 184, 0.18);
  box-shadow: 0 24px 44px rgba(59, 130, 246, 0.08);
}

.result-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  gap: 16px;
  margin-bottom: 20px;
}

.result-header h2 {
  margin: 0;
  font-size: 1.5rem;
  color: #0f172a;
}

.result-header span {
  color: #475569;
  font-size: 0.95rem;
}

.result-table {
  width: 100%;
  border-collapse: collapse;
  min-width: 560px;
  background: #ffffff;
  border-radius: 18px;
  overflow: hidden;
}

.result-table thead th {
  text-align: left;
  padding: 18px 16px;
  font-size: 0.93rem;
  color: #0f172a;
  background: rgba(59, 130, 246, 0.08);
}

.pagination-panel {
  display: flex;
  flex-wrap: wrap;
  align-items: center;
  gap: 16px;
  margin-top: 18px;
  padding: 18px 22px;
  border-radius: 18px;
  background: rgba(248, 250, 252, 0.96);
  border: 1px solid rgba(148, 163, 184, 0.18);
}

.page-size-control {
  display: flex;
  align-items: center;
  gap: 10px;
  color: #475569;
  font-size: 0.96rem;
}

.page-size-control span {
  white-space: nowrap;
}

.page-size-select {
  min-width: 88px;
}

.pagination-right {
  display: flex;
  align-items: center;
  gap: 16px;
  margin-left: auto;
}

.record-summary {
  color: #334155;
  font-size: 0.96rem;
  white-space: nowrap;
}

.result-table tbody tr {
  border-bottom: 1px solid rgba(148, 163, 184, 0.14);
}

.result-table tbody tr:last-child {
  border-bottom: none;
}

.result-table td {
  padding: 16px;
  color: #475569;
  font-size: 0.96rem;
}

.result-table tbody tr:nth-child(even) {
  background: rgba(59, 130, 246, 0.04);
}

.empty-state {
  padding: 32px 18px;
  border-radius: 20px;
  background: rgba(236, 246, 255, 0.8);
  color: #334155;
  text-align: center;
  font-size: 1rem;
}

@media (max-width: 1024px) {
  .student-card {
    grid-template-columns: repeat(2, minmax(0, 1fr));
  }

  .student-card label,
  .student-card input {
    width: 100%;
  }
}

@media (max-width: 720px) {
  .student-display {
    padding: 18px 18px 30px;
  }

  .page-header {
    flex-direction: column;
    align-items: flex-start;
    padding: 22px;
  }

  .student-card {
    grid-template-columns: 1fr;
    padding: 22px;
  }

  .button-row {
    justify-content: stretch;
    flex-direction: column;
  }

  .primary-btn,
  .secondary-btn {
    width: 100%;
  }

  .result-panel {
    padding: 22px;
  }

  .result-table {
    min-width: 0;
  }

  .tjia-panel {
    justify-content: stretch;
  }

  .tjia-panel .primary-btn {
    width: 100%;
    min-width: auto;
  }
}
</style>

