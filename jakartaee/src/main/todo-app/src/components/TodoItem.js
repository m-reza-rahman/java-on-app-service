import React, { useState, useEffect } from 'react';

function TodoItem({ item, itemToEdit, editItem, commitEditItem, revertEditing, removeItem }) {
  const [description, setDescription] = useState(item.description);
  const [completed, setCompleted] = useState(item.completed);

  useEffect(() => {
    setDescription(item.description);
    setCompleted(item.completed);
  }, [item]);

  const handleInputChange = (e) => {
    setDescription(e.target.value);
  };

  const handleCheckboxChange = (e) => {
    setCompleted(e.target.checked);
    item.completed = e.target.checked;
    commitEditItem(item);
  };

  const handleBlur = () => {
    item.description = description;
    commitEditItem(item);
  };

  const handleKeyDown = (e) => {
    if (e.key === 'Escape') {
      e.stopPropagation(); // Stop the event from propagating
      e.preventDefault();
      revertEditing();
    }
    else if (e.key === 'Enter') {
      item.description = description;
      commitEditItem(item);
    }
  };
  
  return (
    <li>
      <div className="todo-item-container">
        <input
          type="checkbox"
          checked={completed}
          onChange={handleCheckboxChange}
          className="todo-checkbox"
        />
        <span
          className={`todo-description ${completed ? 'completed' : ''}`}
          onDoubleClick={() => editItem(item)}
        >
          {description}
        </span>
        <button 
          className="todo-item-remove-button todo-item-remove-icon"
          title="Remove this item"
          onClick={() => removeItem(item)}
        >
        </button>
      </div>
      <div className={item !== itemToEdit ? 'hidden' : ''}>
        <form
          onSubmit={() => commitEditItem(item)}
        >
          <input
            type="text"
            className="textbox"
            value={description}
            onBlur={handleBlur}
            onChange={handleInputChange}
            onKeyDown={handleKeyDown}
            required
          />
        </form>
      </div>
    </li>
  );
}

export default TodoItem;