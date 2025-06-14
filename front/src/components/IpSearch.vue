<template>
  <div class="container">
    <h1>IP地址查询系统</h1>
    <div class="search-tabs">
      <button class="tab" :class="{ active: searchType === 'ip' }" @click="searchType = 'ip'">IP地址查询</button>
      <button class="tab" :class="{ active: searchType === 'port' }" @click="searchType = 'port'">端口查询</button>
      <button class="tab" :class="{ active: searchType === 'description' }" @click="searchType = 'description'">描述查询</button>
    </div>
    <div class="search-box">
      <template v-if="searchType === 'ip'">
        <input type="text" v-model="ipAddress" placeholder="请输入IP地址" @keyup.enter="searchIp">
        <button @click="searchIp">查询</button>
      </template>
      <template v-if="searchType === 'port'">
        <input type="number" v-model="port" placeholder="请输入端口号" @keyup.enter="searchPort">
        <button @click="searchPort">查询</button>
      </template>
      <template v-if="searchType === 'description'">
        <input type="text" v-model="keyword" placeholder="请输入描述关键词" @keyup.enter="searchDescription">
        <button @click="searchDescription">查询</button>
      </template>
    </div>
    <div class="result" v-if="results.length > 0">
      <div class="info-box">
        <h3>查询结果：</h3>
        <div class="result-list">
          <div v-for="result in results" :key="result.id" class="result-item">
            <p><strong>IP地址：</strong>{{ result.ipAddress }}</p>
            <p><strong>端口号：</strong>{{ result.port }}</p>
            <p><strong>描述：</strong>{{ result.description }}</p>
          </div>
        </div>
      </div>
    </div>
    <div class="error" v-if="error">
      {{ error }}
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue'
import axios from 'axios'

const searchType = ref('ip')
const ipAddress = ref('')
const port = ref('')
const keyword = ref('')
const results = ref([])
const error = ref(null)

const searchIp = async () => {
  if (!ipAddress.value) {
    error.value = '请输入IP地址'
    return
  }
  
  try {
    const response = await axios.get(`http://localhost:8080/api/ip/search?ipAddress=${ipAddress.value}`)
    results.value = [response.data]
    error.value = null
  } catch (err) {
    error.value = '未找到该IP地址的信息'
    results.value = []
  }
}

const searchPort = async () => {
  if (!port.value) {
    error.value = '请输入端口号'
    return
  }
  
  try {
    const response = await axios.get(`http://localhost:8080/api/ip/search/port?port=${port.value}`)
    results.value = response.data
    error.value = null
  } catch (err) {
    error.value = '未找到该端口的信息'
    results.value = []
  }
}

const searchDescription = async () => {
  if (!keyword.value) {
    error.value = '请输入描述关键词'
    return
  }
  
  try {
    const response = await axios.get(`http://localhost:8080/api/ip/search/description?keyword=${keyword.value}`)
    results.value = response.data
    error.value = null
  } catch (err) {
    error.value = '未找到相关描述的信息'
    results.value = []
  }
}
</script>

<style scoped>
.container {
  background-color: white;
  padding: 20px;
  border-radius: 8px;
  box-shadow: 0 2px 4px rgba(0,0,0,0.1);
  max-width: 800px;
  margin: 0 auto;
}

.search-box {
  margin-bottom: 20px;
}

.search-tabs {
  margin-bottom: 15px;
}

.tab {
  padding: 8px 16px;
  margin-right: 10px;
  border: none;
  background-color: #e0e0e0;
  cursor: pointer;
  border-radius: 4px;
}

.tab.active {
  background-color: #4CAF50;
  color: white;
}

input[type="text"], input[type="number"] {
  width: 300px;
  padding: 8px;
  font-size: 16px;
  border: 1px solid #ddd;
  border-radius: 4px;
}

button {
  padding: 8px 16px;
  font-size: 16px;
  background-color: #4CAF50;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

button:hover {
  background-color: #45a049;
}

.result {
  margin-top: 20px;
}

.error {
  color: red;
  margin-top: 10px;
}

.info-box {
  background-color: #f8f9fa;
  padding: 15px;
  border-radius: 4px;
  margin-top: 10px;
}

.result-list {
  margin-top: 10px;
}

.result-item {
  border-bottom: 1px solid #eee;
  padding: 10px 0;
}

.result-item:last-child {
  border-bottom: none;
}
</style> 