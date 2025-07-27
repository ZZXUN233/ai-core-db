<template>
  <div class="p-4">
    <h1 class="text-2xl font-bold mb-4">AI 助手</h1>

    <div class="mb-4">
      <input
        v-model="message"
        @keyup.enter="sendMessage"
        placeholder="请输入问题..."
        class="border p-2 w-full rounded"
      />
    </div>

    <button @click="sendMessage" class="bg-blue-500 text-white px-4 py-2 rounded">
      发送
    </button>

    <div v-if="answer" class="mt-4 whitespace-pre-line bg-gray-100 p-4 rounded">
      <h2 class="font-semibold">AI 回复：</h2>
      <p>{{ answer }}</p>
    </div>

    <div v-if="loading" class="mt-4 text-gray-500">AI 正在思考中...</div>
  </div>
</template>

<script setup>
import { ref } from 'vue'

const message = ref('')
const answer = ref('')
const loading = ref(false)

const sendMessage = async () => {
  if (!message.value.trim()) return

  loading.value = true
  answer.value = ''

  try {
    const res = await fetch(`/ai/generate?message=${encodeURIComponent(message.value)}`)
    const data = await res.json()
    answer.value = data.generation
  } catch (error) {
    answer.value = '请求失败，请检查服务是否正常。'
    console.error(error)
  } finally {
    loading.value = false
  }
}
</script>
