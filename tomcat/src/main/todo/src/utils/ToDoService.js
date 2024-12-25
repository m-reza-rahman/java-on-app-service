import axios from 'axios';

const REST_PATH = 'resources/todo';

const ToDoService = {
  getItems: () => {
    return axios.get(`${REST_PATH}`);
  },
  
  addItem: (newItem) => {
    return axios.post(`${REST_PATH}`, newItem);
  },
  
  updateItem: (itemId, updatedItem) => {
    return axios.put(`${REST_PATH}/${itemId}`, updatedItem);
  },
  
  removeItem: (itemId) => {
    return axios.delete(`${REST_PATH}/${itemId}`);
  }
};

export default ToDoService;