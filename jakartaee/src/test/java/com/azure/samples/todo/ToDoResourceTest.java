package com.azure.samples.todo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ToDoResourceTest {

    @Mock
    private ToDoService toDoService;

    @InjectMocks
    private ToDoResource toDoResource;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreate() {
        String username = "testUser";
        ToDoItem item = new ToDoItem();
        item.setDescription("Test ToDo Item");

        when(toDoService.addToDoItem(username, item)).thenReturn(item);

        ToDoItem result = toDoResource.create(username, item);

        assertNotNull(result);
        assertEquals("Test ToDo Item", result.getDescription());
        verify(toDoService, times(1)).addToDoItem(username, item);
    }

    @Test
    public void testEdit() {
        String username = "testUser";
        Long id = 1L;
        ToDoItem item = new ToDoItem();
        item.setDescription("Updated ToDo Item");

        doNothing().when(toDoService).updateToDoItem(username, item);

        toDoResource.edit(username, id, item);

        assertEquals(id, item.getId());
        verify(toDoService, times(1)).updateToDoItem(username, item);
    }

    @Test
    public void testRemove() {
        String username = "testUser";
        Long id = 1L;

        doNothing().when(toDoService).removeToDoItem(username, id);

        toDoResource.remove(username, id);

        verify(toDoService, times(1)).removeToDoItem(username, id);
    }

    @Test
    public void testGetAll() {
        String username = "testUser";
        ToDoItem item1 = new ToDoItem();
        item1.setDescription("Test ToDo Item 1");
        ToDoItem item2 = new ToDoItem();
        item2.setDescription("Test ToDo Item 2");

        List<ToDoItem> items = Arrays.asList(item1, item2);

        when(toDoService.findToDoItemsByUsername(username)).thenReturn(items);

        List<ToDoItem> result = toDoResource.getAll(username);

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Test ToDo Item 1", result.get(0).getDescription());
        assertEquals("Test ToDo Item 2", result.get(1).getDescription());
        verify(toDoService, times(1)).findToDoItemsByUsername(username);
    }
}