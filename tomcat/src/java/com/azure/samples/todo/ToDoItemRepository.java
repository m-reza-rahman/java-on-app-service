package com.azure.samples.todo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface ToDoItemRepository extends JpaRepository<ToDoItem, Long> {
}
