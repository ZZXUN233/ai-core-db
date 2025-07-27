<template>
  <div v-html="compiledMarkdown" className="prose max-w-full"/>
</template>

<script setup>
import {computed} from 'vue'
import {marked} from 'marked'

// 自定义 think 块转换
marked.use({
  extensions: [{
    name: 'thinkBlock',
    level: 'inline', // 或 'block' 取决于你使用方式
    start(src) {
      return src.match(/<think>/)?.index
    },
    tokenizer(src) {
      const match = /^<think>([\s\S]*?)<\/think>/.exec(src)
      if (match) {
        return {
          type: 'think',
          raw: match[0],
          text: match[1].trim()
        }
      }
    },
    renderer(token) {
      return `<div class="think-block">${marked.parseInline(token.text)}</div>`
    }
  }]
})

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

.think-block {
  background-color: #f8f9fa;
  font-style: italic;
  padding: 1em;
  border-left: 4px solid #ccc;
  margin: 1em 0;
  border-radius: 4px;
  color: #444;
}
</style>
