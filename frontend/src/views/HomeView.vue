<template>
  <div class="w-1/2 h-1/2 ml-52 mt-48">
    <h1
      class="mb-4 text-4xl font-extrabold leading-none tracking-tight text-gray-900 md:text-5xl lg:text-8xl dark:text-white"
    >
      Your Todo List
    </h1>

    <form class="w-10/12 mt-12">
      <div class="mb-6">
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

      <div class="mb-12">
        <label
          for="password"
          class="block mb-2 text-sm font-medium text-gray-900 dark:text-white"
          >Password</label
        >
        <input
          v-model="password"
          type="password"
          id="password"
          class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
          placeholder="•••••••••"
          required
        />
      </div>

      <div class="flex justify-around">
        <button
          @click="sendLoginInfo"
          type="button"
          class="text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300 font-medium rounded-lg text-sm w-full sm:w-auto px-5 py-2.5 text-center dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800"
        >
          로그인
        </button>
        <button
          @click="goToSignUp"
          type="button"
          class="text-white bg-green-700 hover:bg-green-800 focus:ring-4 focus:outline-none focus:ring-green-300 font-medium rounded-lg text-sm w-full sm:w-auto px-5 py-2.5 text-center dark:bg-green-600 dark:hover:bg-green-700 dark:focus:ring-green-800"
        >
          회원가입
        </button>
      </div>
    </form>
  </div>
</template>

<script setup lang="ts">
import { ref } from "vue";
import { useRouter } from "vue-router";
import { signIn } from "@/api/userApi";

const router = useRouter();
const userID = ref("");
const password = ref("");

interface LoginInfo {
  userID: string;
  password: string;
}

const goToSignUp = () => {
  router.push("/sign-up");
};

const sendLoginInfo = async () => {
  if (userID.value === "" || password.value === "") {
    alert("모든 필드를 채우셔야 합니다.");
    return;
  }

  const loginInfo: LoginInfo = {
    userID: userID.value,
    password: password.value,
  };

  const success = await signIn(loginInfo);

  if (success) {
    router.push("/todo");
  } else {
    alert("로그인에 실패하였습니다.");
  }
};
</script>
