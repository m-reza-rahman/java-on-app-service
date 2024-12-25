import axios from 'axios';

const ToDoService = {
  getItems: () => {
    return axios.get(`resources/todo`);
  },
  
  addItem: (newItem) => {
    return axios.post(`resources/todo`, newItem);
  },
  
  updateItem: (itemId, updatedItem) => {
    return axios.put(`resources/todo/${itemId}`, updatedItem);
  },
  
  removeItem: (itemId) => {
    return axios.delete(`resources/todo/${itemId}`);
  }
};

export default ToDoService;