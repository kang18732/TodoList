import axios from "axios";
import { useUserStore } from "@/store/userStore";

const userStore = useUserStore();

// export const postTask = async (taskText: object) => {
//   try {
//     const response = await axios.post(
//       "http://localhost:8080/todo/task",
//       taskText,
//       {
//         headers: {
//           "Content-Type": "application/json",
//           "X-AUTH-TOKEN": userStore.token,
//         },
//       }
//     );
//     console.log(response.data);
//     return response.data;
//   } catch (error) {
//     console.error(`axios postTask error: ${error}`);
//   }
// };

export const postTask = async (taskText: string) => {
  try {
    const response = await axios.post(
      "http://localhost:8080/todo/task",
      {
        text: taskText,
      },
      {
        headers: {
          "Content-Type": "application/json",
          "X-AUTH-TOKEN": userStore.token,
        },
      }
    );
    console.log(response.data);
    return response.data;
  } catch (error) {
    console.error(`axios postTask error: ${error}`);
  }
};

export const getTaskList = async () => {
  try {
    const response = await axios.get("http://localhost:8080/todo/task-list", {
      headers: {
        "X-AUTH-TOKEN": userStore.token,
      },
    });
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

export const patchTask = async (
  taskId: number,
  taskText: string,
  taskDone: boolean
) => {
  try {
    const response = await axios.patch(
      `http://localhost:8080/todo/${taskId}`,
      {
        text: taskText,
        done: taskDone,
      },
      {
        headers: {
          "X-AUTH-TOKEN": userStore.token,
          "Content-Type": "application/json",
        },
      }
    );
    console.log(response.data);
    return response.data;
  } catch (error) {
    console.error(`axios patchTask error: ${error}`);
  }
};

export const deleteTask = async (taskId: number) => {
  try {
    const response = await axios.delete(
      `http://localhost:8080/todo/${taskId}`,
      {
        headers: {
          "X-AUTH-TOKEN": userStore.token,
        },
      }
    );
    console.log(response.data);
    return response.data;
  } catch (error) {
    console.error(`axios deleteTask error: ${error}`);
  }
};
