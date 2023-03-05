import { defineStore } from "pinia";
import { ref } from "vue";

export const useUserStore = defineStore("user", () => {
  const token = ref("");
  const userId = ref(-1);

  const setToken = (new_token: string) => {
    token.value = new_token;
  };
  const setUserId = (id: number) => {
    userId.value = id;
  };

  return { token, userId, setToken, setUserId };
});
