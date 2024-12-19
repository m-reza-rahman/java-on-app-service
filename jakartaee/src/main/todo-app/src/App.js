import React, { useState, useEffect } from 'react';
import { ToDoService } from './ToDoService';  // Import the ToDoService
import './App.css';

const ToDoApp = () => {
  const [items, setItems] = useState([]);
  const [newToDoDescription, setNewToDoDescription] = useState('');
  const [itemToEdit, setItemToEdit] = useState(null);
  const [userId] = useState('Galia'); // The user ID (could be dynamic or fetched)

  useEffect(() => {
    // Fetch items for the user when the component mounts
    ToDoService.getItems(userId)
      .then(response => {
        setItems(response.data);
      })
      .catch(error => {
        console.error('Error fetching items:', error);
      });
  }, [userId]);

  // Show a notification when an item is added or edited
  const addItem = (event) => {
    event.preventDefault();
    const newItem = { description: newToDoDescription, completed: false };
    ToDoService.addItem(userId, newItem)
      .then(response => {
        setItems([...items, response.data]);
        setNewToDoDescription('');
      })
      .catch(error => {
        console.error('Error adding item:', error);
      });    
  };

  const editItem = (item) => {
    setItemToEdit(item);
  };

  // Show error message on failure (replace with appropriate error handling)
  const commitEditItem = (item) => {
    ToDoService.updateItem(userId, item.id, item)
      .then(() => {
        setItemToEdit(null);
      })
      .catch(error => {
        console.error('Error updating item:', error);
      });
  };

  const revertEditing = (item) => {
    setItemToEdit(null);
  };

  const removeItem = (item) => {
    ToDoService.removeItem(userId, item.id)
      .then(() => {
        setItems(items.filter(i => i !== item));
      })
      .catch(error => {
        console.error('Error removing item:', error);
      });
  };

  return (
    <div className="center">
      <div id="todo-panel">
        <label className="todo-label" htmlFor="add-todo">Galia's To Do List</label>
        <form onSubmit={addItem}>
          <input
            id="add-todo"
            className="textbox"
            placeholder="Buy milk"
            value={newToDoDescription}
            onChange={(e) => setNewToDoDescription(e.target.value)}
            required
          />
        </form>
        {items.length > 0 && (
          <div>
            <ul id="todo-list">
              {items.map((item, index) => (
                <ToDoItem
                  key={index}
                  item={item}
                  itemToEdit={itemToEdit}
                  editItem={editItem}
                  commitEditItem={commitEditItem}
                  revertEditing={revertEditing}
                  removeItem={removeItem}
                />
              ))}
            </ul>
            <span className="item-count-label">
              <strong>{items.length}</strong> {items.length === 1 ? 'item' : 'items'} on your list
            </span>
          </div>
        )}
      </div>
    </div>
  );
};

const ToDoItem = ({ item, itemToEdit, editItem, commitEditItem, revertEditing, removeItem }) => {
  return (
    <li>
      <div className={item === itemToEdit ? 'hidden' : ''}>
        <input
          type="checkbox"
          checked={item.completed}
          onChange={() => {
            item.completed = !item.completed;
            commitEditItem(item);
          }}
        />
        <span
          className={item.completed ? 'completed' : ''}
          onDoubleClick={() => editItem(item)}
        >
          {item.description}
        </span>
        <button 
          className="todo-item-remove-button"
          title="Remove this item"
          onClick={() => removeItem(item)}
        >
          <span className="todo-item-remove-icon"></span>
        </button>
      </div>
      <div className={item !== itemToEdit ? 'hidden' : ''}>
        <form
          onSubmit={() => commitEditItem(item)}
        >
          <input
            type="text"
            className="textbox"
            value={item.description}
            onBlur={() => commitEditItem(item)}
            onChange={(e) => (item.description = e.target.value)}
            required
          />
        </form>
      </div>
    </li>
  );
};

export default ToDoApp;
