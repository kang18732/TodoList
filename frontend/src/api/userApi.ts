import axios from "axios";

export const signUp = async (user: object) => {
  try {
    const response = await axios.post(
      "http://localhost:8080/user/sign-up",
      user,
      {
        headers: {
          "Content-Type": "application/json",
        },
      }
    );
    console.log(response.data);
    return response.data;
  } catch (error) {
    console.error(`axios signUp error: ${error}`);
  }
};

export const signIn = async (user: object) => {
  try {
    const response = await axios.post(
      "http://localhost:8080/user/sign-in",
      user,
      {
        headers: {
          "Content-Type": "application/json",
        },
      }
    );
    console.log(response.data);
    return response.data;
  } catch (error) {
    console.error(`axios signIn error: ${error}`);
  }
};
