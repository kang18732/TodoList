import axios from "axios";

interface Task {
  text: string;
  done: boolean;
}

export const postTask = async (task: Task) => {
  try {
    const response = await axios.post(`http://localhost:8080/post/task`, task, {
      headers: {
        "Content-Type": "application/json",
      },
    });
    console.log(response.data);
    return response.data;
  } catch (error) {
    console.error(`axios postTask error: ${error}`);
  }
};

export const getTaskList = async () => {
  try {
    const response = await axios.get(`http://localhost:8080/get/task-list`);
    console.log(response.data);
    return response.data;
  } catch (error) {
    console.error(`axios getTask error: ${error}`);
  }
};

export const deleteTask = async () => {};
