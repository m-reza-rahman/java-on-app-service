package com.azure.samples.todo;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

@Stateless
public class DefaultToDoService implements ToDoService {
    private static final Logger logger = Logger
            .getLogger(DefaultToDoService.class.getName());

    @Inject
    private ToDoItemRepository repository;

    @Override
    public ToDoItem addToDoItem(ToDoItem item) {
        logger.log(Level.INFO, "Adding item: {0}", item);

        return repository.create(item);
    }

    @Override
    public void updateToDoItem(ToDoItem item) {
        logger.log(Level.INFO, "Updating item: {0}", item);

        repository.update(item);
    }

    @Override
    public void removeToDoItem(Long id) {
        ToDoItem item = repository.find(id);

        logger.log(Level.INFO, "Removing item: {0}", item);

        repository.delete(item);
    }

    @Override
    public List<ToDoItem> findAllToDoItems() {
        logger.log(Level.INFO, "Getting all items");

        return repository.findAll();
    }
}
