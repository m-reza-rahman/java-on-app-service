import axios from 'axios';

const API_URL = '/resources/todo';

const ToDoService = {
  getItems: () => {
    return axios.get(`${API_URL}`);
  },
  
  addItem: (newItem) => {
    return axios.post(`${API_URL}`, newItem);
  },
  
  updateItem: (itemId, updatedItem) => {
    return axios.put(`${API_URL}/${itemId}`, updatedItem);
  },
  
  removeItem: (itemId) => {
    return axios.delete(`${API_URL}/${itemId}`);
  }
};

export default ToDoService;