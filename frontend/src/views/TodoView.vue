<template>
  <div class="w-1/2 h-1/2 mx-auto">
    <h2 class="text-4xl font-extrabold dark:text-white text-center mt-24">
      Welcome, {{ userStore.username }}.
    </h2>
    <TodoList class="mt-12" />
    <TodoSearch />
  </div>
</template>

<script setup lang="ts">
import TodoList from "@/components/TodoList.vue";
import TodoSearch from "@/components/TodoSearch.vue";
import { useUserStore } from "@/store/userStore";

const userStore = useUserStore();
</script>
