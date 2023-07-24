import axios from 'axios';

const TODO_BASE_PATH = "http://localhost:8080/v1/todos";

class TaskService {
    getTasks() {
        return axios.get(TODO_BASE_PATH);
    }

    getTaskById(id) {
        return axios.get(TODO_BASE_PATH + "/" + id);
    }

    updateTask(id, newTask) {
        return axios.put(TODO_BASE_PATH + "/" + id, newTask);
    }

    deleteTask(id) {
        return axios.delete(TODO_BASE_PATH + "/" + id);
    }

    createTask(newTask) {
        return axios.post(TODO_BASE_PATH, newTask);
    }
}

export default new TaskService();