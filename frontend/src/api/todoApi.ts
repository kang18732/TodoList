import axios from "axios";

export const postTask = async (task: object) => {
  try {
    const response = await axios.post("http://localhost:8080/post/task", task, {
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
    const response = await axios.get("http://localhost:8080/get/task-list");
    console.log(response.data);
    return response.data;
  } catch (error) {
    console.error(`axios getTaskList error: ${error}`);
  }
};

export const deleteTask = async (id: number) => {
  try {
    const response = await axios.delete(
      `http://localhost:8080/delete/task/${id}`
    );
    console.log(response.data);
  } catch (error) {
    console.error(`axios deleteTask error: ${error}`);
  }
};

export const updateTask = async (task: object) => {
  try {
    const response = await axios.put("http://localhost:8080/put/task", task, {
      headers: {
        "Content-Type": "application/json",
      },
    });
    console.log(response.data);
  } catch (error) {
    console.error(`axios changeTaskStatus error: ${error}`);
  }
};