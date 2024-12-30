import React from 'react';
import TodoItem from './TodoItem';

function TodoList({ items, itemToEdit, editItem, commitEditItem, revertEditing, removeItem }) {
  return (
    <ul id="todo-list">
      {items.map((item, index) => (
        <TodoItem
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
  );
}

export default TodoList;
