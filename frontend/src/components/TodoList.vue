<template>
  <div
    class="max-h-96 overflow-scroll bg-white p-4 rounded-lg border-2 border-black dark:border-white"
  >
    <label for="new-task" class="block text-gray-700 font-medium mb-2"
      >Add Task</label
    >
    <form @submit.prevent="addTask" class="flex mb-4">
      <input
        v-model="newTask"
        id="new-task"
        class="border border-gray-400 text-black p-2 rounded-lg w-2/3"
        type="text"
        placeholder="Buy milk"
      />
      <button
        type="button"
        class="ml-auto w-1/5 text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:ring-blue-300 font-medium rounded-lg text-sm dark:bg-blue-600 dark:hover:bg-blue-700 focus:outline-none dark:focus:ring-blue-800"
        @click="addTask"
      >
        Add
      </button>
    </form>

    <ul>
      <li
        v-for="task in tasks"
        :key="task.taskId"
        class="border-b border-gray-400 py-2"
      >
        <div class="flex">
          <input
            :checked="task.done"
            @change="changeTaskStatus(task)"
            class="mr-2"
            type="checkbox"
          />
          <p :class="{ 'line-through': task.done }" class="text-gray-700">
            {{ task.text }}
          </p>
          <button
            @click="removeTask(task.taskId)"
            class="absolute right-0 text-red-500"
          >
            Remove
          </button>
        </div>
      </li>
    </ul>
  </div>
</template>

<script setup lang="ts">
import { postTask, getTaskList, deleteTask, patchTask } from "@/api/todoApi";
import { ref, onMounted } from "vue";
import { useRouter } from "vue-router";
import { useUserStore } from "@/store/userStore";

interface Task {
  taskId: number;
  text: string;
  done: boolean;
}

const tasks = ref<Task[]>([]);
const newTask = ref("");

const userStore = useUserStore();
const router = useRouter();

onMounted(async () => {
  if (userStore.token === "") router.push("/");
  else refreshTaskList();
});

const addTask = async () => {
  if (!newTask.value) return;

  await postTask(newTask.value);

  refreshTaskList();

  newTask.value = "";
};

const changeTaskStatus = async (task: Task) => {
  await patchTask(task.taskId, task.text, !task.done);
  refreshTaskList();
};

const removeTask = async (taskId: number) => {
  await deleteTask(taskId);
  refreshTaskList();
};

const refreshTaskList = async () => {
  const response = await getTaskList();
  tasks.value = response.data;
};
</script>
