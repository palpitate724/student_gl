<template>
  <header class="toulan-bar">
    <div class="toulan-container">
      <section class="toulan-left">
        <span class="toulan-icon" aria-hidden="true">{{ userstulog.sname[0] }}</span>
        <div class="toulan-text">
          <p class="toulan-greeting">欢迎您，{{ userstulog.sname }}</p>
          <p class="toulan-title">学生信息管理系统</p>
        </div>
      </section>
      <button class="toulan-logout" type="button" @click.prevent="loginout">退出登录</button>
    </div>
  </header>
</template>

<script setup>

    import { ref } from 'vue'
    import { UserStuStore } from '../../stores/userstu'
    import { useRouter } from 'vue-router'

    const userstulog = UserStuStore()

    const router = useRouter()
    const sname = ref(userstulog.getsname)

    const loginout = () => {
        userstulog.logout()
        router.push('/login')
    }

</script>

<style scoped>
.toulan-bar {
  position: sticky;
  height: 70px;
  top: 0;
  z-index: 50;
  width: 100%;
  background: rgba(255, 255, 255, 0.96);
  border-bottom: 1px solid rgba(148, 163, 184, 0.15);
  box-shadow: 0 10px 24px rgba(15, 23, 42, 0.06);
  backdrop-filter: blur(14px);
}

.toulan-container {
  max-width: 1180px;
  margin: 0 auto;
  padding: 0.85rem 1.25rem;
  display: flex;
  align-items: center;
  justify-content: space-between;
  gap: 1rem;
}

.toulan-left {
  display: flex;
  align-items: center;
  gap: 0.85rem;
}

.toulan-icon {
  width: 44px;
  height: 44px;
  border-radius: 50%;
  background: linear-gradient(135deg, #3b82f6, #2563eb);
  color: #ffffff;
  display: grid;
  place-items: center;
  font-size: 1.05rem;
  font-weight: 700;
  box-shadow: 0 10px 20px rgba(37, 99, 235, 0.18);
}

.toulan-text {
  display: flex;
  flex-direction: column;
  gap: 0.12rem;
}

.toulan-greeting {
  margin: 0;
  font-size: 0.98rem;
  font-weight: 700;
  color: #1f3c88;
}

.toulan-title {
  margin: 0;
  font-size: 0.85rem;
  color: #475569;
  letter-spacing: 0.04em;
}

.toulan-logout {
  padding: 0.72rem 1.2rem;
  font-size: 0.95rem;
  font-weight: 700;
  color: #ffffff;
  background: linear-gradient(135deg, #3b82f6, #2563eb);
  border: 1px solid transparent;
  border-radius: 999px;
  cursor: pointer;
  transition: transform 0.18s ease, box-shadow 0.18s ease, background 0.18s ease;
}

.toulan-logout:hover {
  transform: translateY(-1px);
  box-shadow: 0 14px 24px rgba(37, 99, 235, 0.2);
  background: linear-gradient(135deg, #2563eb, #1d4ed8);
}

.toulan-logout:active {
  transform: translateY(1px);
}

@media (max-width: 720px) {
  .toulan-container {
    flex-direction: column;
    align-items: stretch;
    padding: 0.9rem 1rem;
  }

  .toulan-left {
    width: 100%;
    align-items: flex-start;
  }

  .toulan-logout {
    width: 100%;
  }
}
</style>