package com.azure.samples.todo;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.inject.Inject;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
@Transactional
public class DefaultToDoService implements ToDoService {
    private static final Logger logger = Logger.getLogger(DefaultToDoService.class.getName());

    @Inject
    private ToDoItemRepository repository;

    @Override
    public ToDoItem addToDoItem(ToDoItem item) {
        logger.log(Level.INFO, "Adding item: {0}", item);

        return repository.save(item);
    }

    @Override
    public void updateToDoItem(ToDoItem item) {
        logger.log(Level.INFO, "Updating item: {0}", item);

        repository.save(item);
    }

    @Override
    public void removeToDoItem(Long id) {
        logger.log(Level.INFO, "Removing item with ID: {0}", id);

        repository.deleteById(id);
    }

    @Override
    public List<ToDoItem> findAllToDoItems() {
        logger.log(Level.INFO, "Getting all items");

        return repository.findAll();
    }
}
