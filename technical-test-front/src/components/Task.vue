<template>
  <td>
    <input
      type="checkbox"
      id="{{'checkbox-' + task.id}}"
      :checked="task.done ? true : false"
      @change="updateTask({...task, done: !task.done})" />
  </td>
  <td>
    <span v-if="isEditable">
      <input id="{{'title-' + task.id}}"
             :value="updatedTask.title"
             @change="updateTaskField({...updatedTask, title: $event.target.value})" />
    </span>
    <span v-else>{{ task.title }}</span>
  </td>
  <td>
    <span v-if="isEditable">
      <input id="{{'description-' + task.id}}"
             :value="updatedTask.description"
             @change="updateTaskField({...updatedTask, description: $event.target.value})">
    </span>
    <span v-else>{{ task.description }}</span>
  </td>
  <td>
    <div v-if="!isEditable">
      <button class="actionBtn" @click="handleEdit(true)">Edit</button>
      <button class="actionBtn" @click="deleteTask(task.id)" >Delete</button>
    </div>
    <div v-else >
      <button class="actionBtn" @click="updateTask(updatedTask)">Submit</button>
      <button class="actionBtn" @click="handleEdit(false)">Cancel</button>
    </div>
  </td>
</template>

<script>
import TaskService from '../services/TaskService';

export default {
  name: 'TaskComponent',
  props: {
    task: Object
  },
  data(){
    return {
      isEditable: false,
      updatedTask: {}
    }
  },
  methods: {
    updateTaskField(newUpdatedTask){
      this.updatedTask = newUpdatedTask;
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
    handleEdit(value){
      this.isEditable = value;
    }
  },
  mounted() {
    this.updatedTask = this.task;
  }
}

</script>

<style>
  .actionBtn {
    margin-right: 10px;
    cursor: pointer;
  }
</style>