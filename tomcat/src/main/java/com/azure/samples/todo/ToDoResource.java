package com.azure.samples.todo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;

@RestController
@RequestMapping("/resources/todo")
public class ToDoResource implements Serializable {
    private static final long serialVersionUID = 1L;

    @Autowired
    private ToDoService service;

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ToDoItem create(@RequestBody ToDoItem item) {
        return service.addToDoItem(item);
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ToDoItem find(@PathVariable Long id) {
        return service.find(id);
    }

    @GetMapping(produces = "application/json")
    public List<ToDoItem> findAll() {
        return service.findAllToDoItems();
    }

    @PutMapping(value = "/{id}", consumes = "application/json", produces = "application/json")
    public void update(@RequestBody ToDoItem item) {
        service.updateToDoItem(item);
    }

    @DeleteMapping(value = "/{id}", produces = "application/json")
    public void delete(@PathVariable Long id) {
        service.removeToDoItem(id);
    }
}