<template>
  <div class="ai-result" v-html="renderedHtml"></div>
</template>

<script setup>
import {computed, watchEffect} from 'vue'
import markdownit from 'markdown-it'
import hljs from 'highlight.js'
import 'highlight.js/styles/github.css'

// props
const props = defineProps({
  content: {
    type: String,
    required: true,
  },
})

// markdown 渲染器
const md = markdownit({
  highlight: function (str, lang) {
    if (lang && hljs.getLanguage(lang)) {
      try {
        return '<pre class="hljs"><code>' +
          hljs.highlight(str, {language: lang}).value +
          '</code></pre>';
      } catch (_) {
      }
    }

    return '<pre class="hljs"><code>' + md.utils.escapeHtml(str) + '</code></pre>';
  }
})

const renderedHtml = computed(() => {
  return md.render(props.content)
})
</script>

<style scoped>
.ai-result {
  padding: 1rem;
  background-color: #fafafa;
  border-radius: 0.5rem;
  border: 1px solid #ddd;
  font-family: "Segoe UI", sans-serif;
  line-height: 1.6;
}

.hljs {
  padding: 0.5rem;
  background-color: #f0f0f0;
  border-radius: 0.25rem;
  overflow-x: auto;
}
</style>
