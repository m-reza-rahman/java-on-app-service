package com.azure.samples.todo;

import java.io.Serializable;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.inject.Inject;
import jakarta.validation.Valid;

@RestController
@ResponseBody
@RequestMapping("/todo")
public class ToDoResource implements Serializable {
    private static final long serialVersionUID = 1L;

    @Inject
    private ToDoService service;

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ToDoItem create(@Valid @RequestBody ToDoItem item) {
        return service.addToDoItem(item);
    }

    @PutMapping(value = "/{id}", consumes = "application/json", produces = "application/json")
    public void edit(@Valid @RequestBody ToDoItem item) {
        service.updateToDoItem(item);
    }

    @DeleteMapping(value = "/{id}", produces = "application/json")
    public void remove(@PathVariable("id") Long id) {
        service.removeToDoItem(id);
    }

    @GetMapping(produces = "application/json")
    public List<ToDoItem> getAll() {
        return service.findAllToDoItems();
    }
}
