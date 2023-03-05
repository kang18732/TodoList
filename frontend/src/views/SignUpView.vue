<template>
  <div class="w-1/2 h-1/2 ml-52 mt-20">
    <h1
      class="mb-4 text-4xl font-extrabold leading-none tracking-tight text-gray-900 md:text-5xl lg:text-8xl dark:text-white"
    >
      Your Todo List
    </h1>
    <form class="mt-12 w-10/12">
      <!-- <div class="mb-6">
        <div>
          <label
            for="name"
            class="block mb-2 text-sm font-medium text-gray-900 dark:text-white"
            >Name</label
          >
          <input
            v-model="name"
            type="text"
            id="name"
            class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
            placeholder="John"
            required
          />
        </div>
      </div> -->

      <div class="mb-6">
        <div>
          <label
            for="user-id"
            class="block mb-2 text-sm font-medium text-gray-900 dark:text-white"
            >ID</label
          >
          <input
            v-model="userID"
            type="text"
            id="user-id"
            class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
            placeholder="todo1234"
            required
          />
        </div>
      </div>

      <div class="mb-6">
        <label
          for="email"
          class="block mb-2 text-sm font-medium text-gray-900 dark:text-white"
          >Email address</label
        >
        <input
          v-model="email"
          type="email"
          id="email"
          class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
          placeholder="todo@company.com"
          required
        />
      </div>

      <div class="mb-6">
        <label
          for="password"
          class="block mb-2 text-sm font-medium text-gray-900 dark:text-white"
          >Password</label
        >
        <input
          v-model="firstPW"
          type="password"
          id="password"
          class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
          placeholder="•••••••••"
          required
        />
      </div>

      <div class="mb-10">
        <form @submit.prevent="sendUserInfo">
          <label
            for="confirm_password"
            class="block mb-2 text-sm font-medium text-gray-900 dark:text-white"
            >Confirm password</label
          >
          <input
            v-model="secondPW"
            type="password"
            id="confirm_password"
            class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
            placeholder="•••••••••"
            required
          />
        </form>
      </div>

      <div class="flex justify-center">
        <button
          @click="sendUserInfo"
          type="button"
          class="text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300 font-medium rounded-lg text-sm w-full sm:w-auto px-5 py-2.5 text-center dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800"
        >
          제출
        </button>
      </div>
    </form>
  </div>
</template>

<script setup lang="ts">
import { ref } from "vue";
import { signUp } from "@/api/userApi";
import { useRouter } from "vue-router";

interface User {
  // name: string;
  username: string;
  email: string;
  password: string;
}

const router = useRouter();

// const name = ref("");
const userID = ref("");
const email = ref("");
const firstPW = ref("");
const secondPW = ref("");

const sendUserInfo = async () => {
  // if (name.value === "" || userID.value === "" || email.value === "") {
  if (userID.value === "" || email.value === "") {
    alert("모든 필드를 채워야 합니다.");
    return;
  }
  if (firstPW.value !== secondPW.value) {
    alert("비밀번호가 서로 일치하지 않습니다.");
    return;
  }

  const user: User = {
    // name: name.value,
    username: userID.value,
    email: email.value,
    password: firstPW.value,
  };

  const response = await signUp(user);

  if (response !== undefined) {
    router.push("/");
  } else {
    alert("이미 존재하는 아이디입니다.");
  }
};
</script>
