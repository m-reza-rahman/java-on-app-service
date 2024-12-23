package com.azure.samples.todo;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;

@ApplicationScoped
public class DefaultToDoItemRepository implements ToDoItemRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public ToDoItem create(ToDoItem item) {
        entityManager.persist(item);

        return item;
    }

    @Override
    public ToDoItem find(Long id) {
        return entityManager.find(ToDoItem.class, id);
    }

    @Override
    public List<ToDoItem> findAll() {
        return entityManager.createNamedQuery(
                "ToDoItem.findAll", ToDoItem.class)
                .getResultList();
    }

    @Override
    public void update(ToDoItem item) {
        entityManager.merge(item);
    }

    @Override
    public void delete(ToDoItem item) {
        entityManager.remove(item);
    }
}
