<template>
    <section class="mulu-layout">
        <aside class="mulu-sidebar" :class="{ collapsed }"><!--侧边栏是否展开-->
            <header class="sidebar-header">
                <span class="logo">学管</span>
                <button class="collapse-btn" @click="toggleCollapsed" aria-label="切换侧边栏">
                    <span v-if="collapsed">›</span>
                    <span v-else>‹</span>
                </button>
            </header>

            <nav class="menu">
                <ul class="menu-list">
                    <li
                        v-for="(item, idx) in items"
                        :key="idx"
                        :class="['menu-item', { active: active === idx }]"
                        @click="select(idx)"
                    >
                        <span class="menu-dot"></span>
                        <span class="menu-label">{{ item }}</span>
                    </li>
                </ul>
            </nav>
        </aside>
        
        <main class="mulu-main">
            <student_xianshi v-if="active===0"></student_xianshi>
            <student_jilu v-if="active===1"></student_jilu>
        </main>
    </section>
</template>

<script setup>

import student_xianshi from '../zujian/caozuo/student_xianshi.vue'
import student_jilu from '../zujian/caozuo/student_jilu.vue'

import { ref } from 'vue'

const items = [
    '学生信息查询',
    '删除记录',
]

const active = ref(0)
const collapsed = ref(false)

function select(i) {
    active.value = i
}

function toggleCollapsed() {
    collapsed.value = !collapsed.value
}
</script>

<style scoped>
.mulu-layout {
    display: flex;
    min-height: calc(100vh - var(--toulan-height, 72px));
    background: transparent;
    box-sizing: border-box;
}

.mulu-sidebar,
.mulu-main {
    min-height: calc(100vh - var(--toulan-height, 72px));
}

.mulu-sidebar {
    position: sticky;
    top: var(--toulan-height, 72px);
    align-self: flex-start;
    width: 260px;
    max-height: calc(100vh - var(--toulan-height, 72px));
    background: #ffffff;
    border-right: 1px solid rgba(15, 23, 42, 0.06);
    box-shadow: 0 6px 18px rgba(12, 17, 26, 0.04);
    transition: width 0.22s ease, transform 0.22s ease;
    display: flex;
    flex-direction: column;
    z-index: 20;
}

.mulu-sidebar.collapsed {
    width: 64px;
}

.sidebar-header {
    display: flex;
    align-items: center;
    justify-content: space-between;
    padding: 16px;
    gap: 8px;
}

.logo {
    font-weight: 800;
    color: #2563eb;
    font-size: 1.05rem;
}

.collapse-btn {
    background: transparent;
    border: none;
    font-size: 1.1rem;
    cursor: pointer;
    color: #375cdd;
    padding: 6px;
}

.menu {
    display: flex;
    flex-direction: column;
    gap: 10px;
    padding: 14px 12px 18px 12px;
    overflow-y: auto;
    flex: 1;
}

.menu-list {
    list-style: none;
    margin: 0;
    padding: 0;
    display: flex;
    flex-direction: column;
    gap: 8px;
}

.menu-item {
    display: flex;
    align-items: center;
    gap: 12px;
    padding: 12px 14px;
    border-radius: 14px;
    background: #f8fbff;
    color: #0f172a;
    text-align: left;
    cursor: pointer;
    border: 1px solid transparent;
    transition: background 0.2s ease, border-color 0.2s ease, box-shadow 0.2s ease, transform 0.12s ease;
}

.menu-item .menu-dot {
    width: 10px;
    height: 10px;
    border-radius: 50%;
    background: #bfdbfe;
    flex-shrink: 0;
}

.menu-item .menu-label {
    flex: 1;
    white-space: nowrap;
    overflow: hidden;
    text-overflow: ellipsis;
}

.menu-item:hover {
    background: #eff6ff;
    transform: translateX(1px);
    box-shadow: 0 6px 12px rgba(59, 130, 246, 0.08);
}

.menu-item.active {
    background: #dbeafe;
    border-color: #93c5fd;
    box-shadow: inset 0 0 0 1px rgba(59, 130, 246, 0.16);
}

.mulu-sidebar.collapsed .menu-item .menu-label {
    display: none;
}

.mulu-sidebar.collapsed .logo {
    font-size: 0.95rem;
}

.mulu-main {
    flex: 1;
    margin-left: 10px;
    padding: 24px 32px;
    min-height: calc(100vh - var(--toulan-height, 72px));
    overflow-y: auto;
}

.mulu-sidebar.collapsed + .mulu-main,
.mulu-main.collapsed {
    margin-left: 72px;
}

.main-header h2 {
    margin: 0 0 12px 0;
    font-size: 1.1rem;
    color: #0f172a;
}

.main-body {
    background: #ffffff;
    border-radius: 10px;
    padding: 18px;
    box-shadow: 0 8px 20px rgba(12, 17, 26, 0.04);
}

@media (max-width: 860px) {
    .mulu-layout {
        flex-direction: column;
        padding-top: var(--toulan-height, 72px);
    }

    .mulu-sidebar {
        position: relative;
        width: 100%;
        max-height: none;
        border-right: none;
        border-bottom: 1px solid rgba(15, 23, 42, 0.08);
        box-shadow: none;
    }

    .mulu-main {
        margin-left: 0;
        padding: 18px 16px;
    }

    .mulu-sidebar.collapsed {
        width: 100%;
    }
}
</style>