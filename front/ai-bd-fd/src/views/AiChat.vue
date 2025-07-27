<template>
  <div class="flex flex-col h-screen bg-gray-100">
    <!-- 消息列表 -->
    <div class="flex-1 overflow-y-auto p-4 space-y-4">
      <div v-for="(msg, index) in chatHistory" :key="index" :class="msg.from === 'user' ? 'flex justify-end' : 'flex justify-start'">
        <div
          :class="[
            'max-w-[70%] px-4 py-2 rounded-xl shadow',
            msg.from === 'user'
              ? 'bg-blue-500 text-white rounded-br-none'
              : 'bg-white text-gray-800 rounded-bl-none'
          ]"
        >
          <MarkdownBlock :content="msg.content" />
        </div>
      </div>

      <div v-if="loading" class="flex justify-start">
        <div class="bg-white text-gray-400 px-4 py-2 rounded-xl shadow">正在思考中……</div>
      </div>
    </div>

    <!-- 输入框 -->
    <div class="p-4 border-t bg-white flex items-center gap-2">
      <input
        v-model="message"
        @keydown.enter="sendMessage"
        placeholder="输入你的问题…"
        class="flex-1 border border-gray-300 rounded px-3 py-2 focus:outline-none focus:ring-2 focus:ring-blue-400"
      />
      <button
        @click="sendMessage"
        class="bg-blue-500 text-white px-4 py-2 rounded hover:bg-blue-600 transition"
      >
        发送
      </button>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue'
import MarkdownBlock from '../components/MarkdownBlock.vue'

const message = ref('')
const chatHistory = ref([])
const loading = ref(false)

const sendMessage = async () => {
  if (!message.value.trim()) return

  const userMsg = message.value.trim()
  chatHistory.value.push({ from: 'user', content: userMsg })
  message.value = ''
  loading.value = true

  try {
    const res = await fetch(`/ai/generate?message=${encodeURIComponent(userMsg)}`)
    const data = await res.json()
    chatHistory.value.push({ from: 'ai', content: data.generation || '[无内容]' })
  } catch (err) {
    chatHistory.value.push({ from: 'ai', content: '❌ 请求失败，请检查后端接口。' })
  } finally {
    loading.value = false
  }
}
</script>

<style scoped>
/* 可选：滚动条样式优化 */
::-webkit-scrollbar {
  width: 6px;
}
::-webkit-scrollbar-thumb {
  background-color: rgba(0, 0, 0, 0.2);
  border-radius: 3px;
}
</style>
