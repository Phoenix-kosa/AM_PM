<template>
  <div>
    <project-plan :page-type="'ERD'" :page-id="2"></project-plan>
  </div>
</template>

<script>
import ProjectPlan from '@/components/ProjectPlan.vue';

export default {
  components: {
    ProjectPlan
  },
};
</script>


<style scoped>

</style>
