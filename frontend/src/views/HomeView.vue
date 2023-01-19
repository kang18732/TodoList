<template>
  <div class="w-1/2 h-1/2 ml-52 mt-48">
    <h1
      class="mb-4 text-4xl font-extrabold leading-none tracking-tight text-gray-900 md:text-5xl lg:text-8xl dark:text-white"
    >
      Your Todo List
    </h1>

    <SignIn />
    <!-- <SignUp /> -->
  </div>
</template>

<script setup lang="ts">
import SignIn from "@/components/SignIn.vue";
import SignUp from "@/components/SignUp.vue";
import { ref } from "vue";

const name = ref("");
const enterName = () => {};
</script>
