<template>
  <section class="record-page">
    <div class="record-card">
      <div class="record-header">
        <div>
          <p class="eyebrow">学生管理</p>
          <h2>删除记录</h2>
          <p class="subtitle">这里展示最近的删除操作记录，便于查看和追踪。</p>
        </div>
        
      </div>

      <div class="stats">
        <div class="stat-item">
          <span class="stat-label">删除条数</span>
          <strong>{{ userstuxxi.stuselcount }}</strong>
        </div>
      </div>

      <div class="record-list">
        <div v-for="(item,id) in userstuxxi.stusel" :key="id" class="record-item">
          <div class="record-main">
            <div class="record-top">
              <span class="record-id">学号 {{ item.sid }}</span>
              <div>
                <span class="record-tag">已删除</span>
                <button class="butxiug" @click.prevent="huifu(id)">恢复</button>
              </div>
            </div>
            <div class="record-info-grid">
              <div class="info-item">
                <span class="info-label">姓名</span>
                <strong>{{ item.sname }}</strong>
              </div>
              <div class="info-item">
                <span class="info-label">年龄</span>
                <strong>{{ item.sage }}</strong>
              </div>
              <div class="info-item">
                <span class="info-label">性别</span>
                <strong>{{ item.ssex }}</strong>
              </div>
              <div class="info-item">
                <span class="info-label">专业</span>
                <strong>{{ item.ssion }}</strong>
              </div>
            </div>
          </div>
        </div>

        <div class="pagination-panel">
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

      </div>
    </div>
  </section>
</template>

<script setup>

    import { ref,onMounted, computed } from 'vue'
    import { UserStuXxiStore } from '../../../stores/userstuxxi'

    const zhuangtai = ref('')

    const userstuxxi = UserStuXxiStore()

    const maiyeshul = ref(10)
    const currentPage = ref(1)

    const seljsondata= computed(() => ({
        "sid" : null,
        "sname" : null,
        "sage" : null,
         "ssex" : null,
         "ssion" : null,
         "sdelete" : 1,
         "ts": currentPage.value,
         "shul": maiyeshul.value
    }))

    const options = Array.from({ length: 20 }).map((_, idx) => ({
        value: idx + 1,
        label: `${idx + 1}`,
    }))
    
    onMounted(async () => {
        await userstuxxi.userstusel(seljsondata.value)
    })
    
    

    const huifu = async (id) => {
        await userstuxxi.userdelrhf(id,seljsondata.value)
    }

</script>

<style scoped>


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
.page-size-select {
  min-width: 88px;
}





.record-page {
  min-height: 100%;
  padding: 24px;
  background: linear-gradient(135deg, #f4f8ff 0%, #eef4ff 100%);
}

.record-card {
  max-width: 980px;
  margin: 0 auto;
  padding: 24px;
  border-radius: 24px;
  background: rgba(255, 255, 255, 0.96);
  border: 1px solid rgba(148, 163, 184, 0.2);
  box-shadow: 0 18px 40px rgba(59, 130, 246, 0.14);
}

.record-header {
  display: flex;
  justify-content: space-between;
  align-items: flex-start;
  gap: 16px;
}

.eyebrow {
  margin: 0 0 6px;
  color: #3b82f6;
  font-size: 0.8rem;
  font-weight: 700;
  letter-spacing: 0.16em;
  text-transform: uppercase;
}

.record-header h2 {
  margin: 0;
  font-size: 1.5rem;
  color: #0f172a;
}

.subtitle {
  margin: 6px 0 0;
  color: #64748b;
  line-height: 1.6;
}



.refresh-btn:hover {
  transform: translateY(-1px);
  box-shadow: 0 12px 24px rgba(59, 130, 246, 0.24);
}

.stats {
  display: grid;
  grid-template-columns: repeat(3, minmax(0, 1fr));
  gap: 12px;
  margin-top: 20px;
}

.stat-item {
  padding: 16px;
  border-radius: 16px;
  background: linear-gradient(135deg, #f8fbff 0%, #eef6ff 100%);
  border: 1px solid rgba(59, 130, 246, 0.12);
}

.stat-label {
  display: block;
  margin-bottom: 8px;
  color: #64748b;
  font-size: 0.9rem;
}

.stat-item strong {
  font-size: 1.25rem;
  color: #0f172a;
}

.record-list {
  display: grid;
  gap: 12px;
  margin-top: 20px;
}

.record-item {
  padding: 16px 18px;
  border-radius: 16px;
  background: linear-gradient(135deg, #ffffff 0%, #f8fbff 100%);
  border: 1px solid #e2e8f0;
  box-shadow: 0 6px 16px rgba(15, 23, 42, 0.04);
  transition: transform 0.2s ease, box-shadow 0.2s ease;
}

.record-item:hover {
  transform: translateY(-2px);
  box-shadow: 0 10px 24px rgba(59, 130, 246, 0.12);
}

.record-main {
  display: flex;
  flex-direction: column;
  gap: 12px;
  width: 100%;
}

.record-top {
  display: flex;
  justify-content: space-between;
  align-items: center;
  gap: 10px;
}

.record-tag {
  padding: 5px 10px;
  border-radius: 999px;
  background: #eff6ff;
  color: #2563eb;
  font-size: 0.78rem;
  font-weight: 700;
}

.record-id {
  color: #475569;
  font-size: 0.9rem;
  font-weight: 600;
}

.butxiug {
  margin-left: 8px;
  padding: 6px 10px;
  border: 1px solid #bbf7d0;
  border-radius: 999px;
  background: #f0fdf4;
  color: #15803d;
  font-size: 0.78rem;
  font-weight: 700;
  cursor: pointer;
  box-shadow: inset 0 1px 0 rgba(255,255,255,0.8);
  transition: transform 0.2s ease, box-shadow 0.2s ease, background 0.2s ease;
}

.butxiug:hover {
  transform: translateY(-1px);
  background: #dcfce7;
  box-shadow: 0 4px 10px rgba(22, 163, 74, 0.12);
}

.butxiug:active {
  transform: scale(0.97);
}

.record-info-grid {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(140px, 1fr));
  gap: 10px;
}

.info-item {
  padding: 10px 12px;
  border-radius: 12px;
  background: #f8fbff;
  border: 1px solid #eaf2ff;
}

.info-label {
  display: block;
  margin-bottom: 4px;
  color: #64748b;
  font-size: 0.75rem;
}

.info-item strong {
  display: block;
  color: #0f172a;
  font-size: 0.95rem;
}

.record-badge {
  padding: 6px 10px;
  border-radius: 999px;
  font-size: 0.8rem;
  font-weight: 700;
  white-space: nowrap;
}

.record-badge.done {
  background: #dcfce7;
  color: #166534;
}

.record-badge.pending {
  background: #fef3c7;
  color: #92400e;
}

@media (max-width: 720px) {
  .record-page {
    padding: 14px;
  }

  .record-card {
    padding: 18px;
  }

  .record-header {
    flex-direction: column;
    align-items: flex-start;
  }

  .stats {
    grid-template-columns: 1fr;
  }

  .record-item {
    flex-direction: column;
    align-items: flex-start;
  }
}
</style>