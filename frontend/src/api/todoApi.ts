import axios from "axios";

export const postTask = async (task: object) => {
  try {
    const response = await axios.post("http://localhost:8080/todo/task", task, {
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
    const response = await axios.get("http://localhost:8080/todo/task-list");
    console.log(response.data);
    return response.data;
  } catch (error) {
    console.error(`axios getTaskList error: ${error}`);
  }
};

export const getTaskListByText = async (text: string) => {
  try {
    const response = await axios.get(
      `http://localhost:8080/todo/task-list/${text}`
    );
    console.log(response.data);
    return response.data;
  } catch (error) {
    console.error(`axios getTaskListByText error: ${error}`);
  }
};

export const deleteTask = async (id: number) => {
  try {
    const response = await axios.delete(
      `http://localhost:8080/todo/task/${id}`
    );
    console.log(response.data);
    return response.data;
  } catch (error) {
    console.error(`axios deleteTask error: ${error}`);
  }
};
