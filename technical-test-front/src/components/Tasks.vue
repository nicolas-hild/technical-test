<template>
  <div class = "container">
    <h1>Task management</h1>

    <div id="addTask">
      <button id="addTaskBtn" @click="toggleAddTask()" >
        Add task
      </button>
      <div v-if="showAddTask" id="addTaskForm" >
        <form @submit="createTask()">
          <input
              class="formPart"
              type="text"
              placeholder="Type your title here..."
              @change="updateNewTask({...newTask, title: $event.target.value})">
          <input
              class="formPart"
              type="text"
              placeholder="Type your description here..."
              @change="updateNewTask({...newTask, description: $event.target.value})">
          <button
              class="formPart"
              type="submit">
            Submit task
          </button>
        </form>
      </div>
    </div>
    <div v-if="tasks.length > 0">
      <div id="tasks">
        <table class="table-custom">
          <thead>
          <th>Completed ?</th>
          <th>Title</th>
          <th>Description</th>
          <th>Actions</th>
          </thead>
          <tbody>
          <tr v-for="task in tasks" v-bind:key="task.id">
            <TaskComponent :task="task" ></TaskComponent>
          </tr>
          </tbody>
        </table>
      </div>
    </div>
    <div v-else>
      <h4>No tasks available, you can add one to start</h4>
    </div>
  </div>
</template>

<script>
import TaskService from '../services/TaskService';
import TaskComponent from "@/components/Task";

export default {
  name: 'TasksComponent',
  components: {TaskComponent},
  data(){
    return {
      tasks: [],
      showAddTask: false,
      newTask: {
        title: "",
        description: "",
        done: false
      },
      editFor: ""
    }
  },
  methods: {
    toggleAddTask(){
      this.showAddTask = !this.showAddTask;
    },
    getTasks(){
      TaskService.getTasks().then((response) => {
        this.tasks = response.data;
        console.log(this.tasks);
      });
    },
    updateTask(newTask){
      TaskService.updateTask(newTask.id, newTask).then(() => {
        location.reload();
      });
    },
    deleteTask(id){
      TaskService.deleteTask(id).then(() => {
        location.reload();
      });
    },
    createTask(){
      TaskService.createTask(this.newTask).then(() => {
        location.reload();
      })
    },
    updateNewTask(newTask){
      this.newTask = newTask;
    }
  },
  mounted() {
    this.getTasks();
  }
}

</script>

<style>
  h1 {
    background-color: #2c3e50;
    color: white;
    border-radius: 10px;
    width: 25%;
    margin: auto;
  }
  form{
    justify-content: space-between;
    display: flex;
    margin-top: 10px;
  }
  #addTask{
    width: 70%;
    margin: 10px auto;
    text-align: right;
  }
 .table-custom {
   margin: auto;
   width: 70%;
   border-collapse: collapse;
 }
 .formPart {
   width: 30%;
 }
 th {
   background-color: #2c3e50;
   color: white;
   padding: 10px;
 }
</style>