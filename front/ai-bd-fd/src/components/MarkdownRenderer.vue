<template>
  <div v-html="compiledMarkdown" class="prose max-w-full" />
</template>

<script setup>
import { computed } from 'vue'
import { marked } from 'marked'

const props = defineProps({
  content: String
})

const compiledMarkdown = computed(() => {
  return marked.parse(props.content || '')
})
</script>

<style scoped>
.prose pre {
  background-color: #f3f3f3;
  padding: 0.75em;
  overflow-x: auto;
  border-radius: 0.5em;
}
</style>
