<template>
  <div class="about">
    <div class="bg-white p-4 rounded-lg">
      <form @submit.prevent="addTask" class="mb-4">
        <label for="new-task" class="block text-gray-700 font-medium mb-2"
          >Add Task</label
        >
        <input
          v-model="newTask"
          id="new-task"
          class="border border-gray-400 text-black p-2 rounded-lg w-full"
          type="text"
          placeholder="Buy milk"
        />
      </form>
      <ul>
        <li
          v-for="task in tasks"
          :key="task.id"
          class="border-b border-gray-400 py-2"
        >
          <div class="flex items-center">
            <input
              :checked="task.done"
              @change="markDone(task)"
              class="mr-2"
              type="checkbox"
            />
            <p :class="{ 'line-through': task.done }" class="text-gray-700">
              {{ task.text }}
            </p>
          </div>
          <button @click="removeTask(task)" class="text-red-500">Remove</button>
        </li>
      </ul>
    </div>
  </div>
</template>

<script setup lang="ts">
import { postTask, getTaskList, deleteTask, updateTask } from "@/api/todoApi";
import { ref, onMounted } from "vue";

interface Task {
  text: string;
  done: boolean;
  id: number;
}

const tasks = ref<Task[]>([]);
const newTask = ref("");

onMounted(async () => {
  refreshTaskList();
});

const addTask = async () => {
  if (!newTask.value) return;

  const task = await postTask({
    text: newTask.value,
    done: false,
  });

  tasks.value.push(task);

  newTask.value = "";
};

const markDone = (task: Task) => {
  task.done = !task.done;
  updateTask(task);
};

const removeTask = async (task: Task) => {
  // const index = tasks.value.indexOf(task);
  // tasks.value.splice(index, 1);
  await deleteTask(task.id);
  refreshTaskList();
};

const refreshTaskList = async () => {
  tasks.value = await getTaskList();
};
</script>

<style>
/* @media (min-width: 1024px) {
  .about {
    min-height: 100vh;
    display: flex;
    align-items: center;
  }
} */
</style>
