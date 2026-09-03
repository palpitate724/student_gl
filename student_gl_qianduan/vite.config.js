import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'

// https://vite.dev/config/
export default defineConfig({
  plugins: [vue()],
  base: './',
  build: {
    // 直接输出到 nginx 的站点目录，打包完成不用手动复制
    outDir: 'D:/javaweb/Nginx/nginx-1.30.4/html',
    emptyOutDir: true
  }
})
