<template>
  <main class="home-shell">
    <toulan />
    <mulu>
      
    </mulu>
  </main>
</template>

<script setup>
import toulan from './zujian/toulan.vue'
import mulu from './zujian/mulu.vue'

</script>

<style scoped>
.home-shell {
  min-height: 100vh;
  background: var(--page-bg);
}
</style>