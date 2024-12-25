package com.azure.samples.todo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class DefaultToDoService implements ToDoService {
    private static final Logger logger = Logger.getLogger(DefaultToDoService.class.getName());

    @Autowired
    private ToDoItemRepository repository;

    @Override
    public ToDoItem addToDoItem(ToDoItem item) {
        logger.log(Level.INFO, "Creating ToDoItem: {0}", item);
        return repository.save(item);
    }

    @Override
    public ToDoItem find(Long id) {
        logger.log(Level.INFO, "Finding ToDoItem with id: {0}", id);
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<ToDoItem> findAllToDoItems() {
        logger.log(Level.INFO, "Finding all ToDoItems");
        return repository.findAll();
    }

    @Override
    public void removeToDoItem(Long id) {
        logger.log(Level.INFO, "Deleting ToDoItem with id: {0}", id);
        repository.deleteById(id);
    }

    @Override
    public void updateToDoItem(ToDoItem item) {
        logger.log(Level.INFO, "Updating ToDoItem: {0}", item);
        repository.save(item);
    };
}