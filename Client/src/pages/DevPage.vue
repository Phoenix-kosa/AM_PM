<template>
  <h1>🛠 Development</h1>
  <hr class="dev_title" />
  <Gantt />
  <Notice />
</template>

<script setup>
import Gantt from "@/components/Gantt.vue";
import Notice from "@/components/Notice.vue";
</script>

<style scoped>
.dev_title {
  margin-bottom: 40px;
}
</style>
