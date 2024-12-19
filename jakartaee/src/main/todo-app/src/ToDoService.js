import axios from 'axios';

const API_URL = 'http://localhost:8080/resources/todo';

export const ToDoService = {
  getItems: (userId) => {
    return axios.get(`${API_URL}/${userId}`);
  },
  
  addItem: (userId, newItem) => {
    return axios.post(`${API_URL}/${userId}`, newItem);
  },
  
  updateItem: (userId, itemId, updatedItem) => {
    return axios.put(`${API_URL}/${userId}/${itemId}`, updatedItem);
  },
  
  removeItem: (userId, itemId) => {
    return axios.delete(`${API_URL}/${userId}/${itemId}`);
  }
};
