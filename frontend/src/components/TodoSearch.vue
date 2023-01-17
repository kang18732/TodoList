<template>
  <div
    class="mt-12 bg-white p-4 rounded-lg border-2 border-black text-black dark:border-white"
  >
    <form @submit.prevent="searchTask" class="mb-4">
      <label for="task" class="block text-gray-700 font-medium mb-2"
        >Search Task</label
      >
      <input
        v-model="task"
        id="task"
        class="border border-gray-400 text-black p-2 rounded-lg w-full"
        type="text"
      />
    </form>
    <ul>
      <li
        v-for="task in tasks"
        :key="task.id"
        class="border-b border-gray-400 py-2"
      >
        <p :class="{ 'line-through': task.done }" class="text-gray-700">
          {{ task.text }}
        </p>
      </li>
    </ul>
  </div>
</template>

<script setup lang="ts">
import { ref } from "vue";
import { getTaskListByText } from "@/api/todoApi";

const tasks = ref();
const task = ref("");

const searchTask = async () => {
  tasks.value = await getTaskListByText(task.value);
  console.log(tasks.value);
};
</script>
