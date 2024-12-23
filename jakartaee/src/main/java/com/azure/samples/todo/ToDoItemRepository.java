package com.azure.samples.todo;

import java.util.List;

public interface ToDoItemRepository {
    ToDoItem create(ToDoItem item);

    void delete(ToDoItem item);

    ToDoItem find(Long id);

    List<ToDoItem> findAll();

    void update(ToDoItem item);
}
