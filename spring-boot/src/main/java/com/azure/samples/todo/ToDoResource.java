package com.azure.samples.todo;

import org.springframework.web.bind.annotation.*;
import jakarta.inject.Inject;

import java.io.Serializable;
import java.util.List;

@RestController
@RequestMapping("resources/todo")
public class ToDoResource implements Serializable {
    private static final long serialVersionUID = 1L;

    @Inject
    private ToDoService service;

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ToDoItem create(@RequestBody ToDoItem item) {
        return service.addToDoItem(item);
    }

    @PutMapping(value = "/{id}", consumes = "application/json", produces = "application/json")
    public void edit(
            @PathVariable Long id,
            @RequestBody ToDoItem item) {
        service.updateToDoItem(item);
    }

    @DeleteMapping(value = "/{id}", produces = "application/json")
    public void remove(@PathVariable Long id) {
        service.removeToDoItem(id);
    }

    @GetMapping(produces = "application/json")
    public List<ToDoItem> getAll() {
        return service.findAllToDoItems();
    }
}
