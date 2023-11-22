import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [vue()],
  server: {
    host: true,
    port: 8000,
    watch: {
      usePolling: true
    }
  },
  define: {
    'global': 'window'
  },
  proxy: {
    '/api': {
      target: 'http://bellidel.eu:7800',
      changeOrigin: true,
      rewrite: (path) => path.replace(/^\/api/, '')
    }
  }
})
