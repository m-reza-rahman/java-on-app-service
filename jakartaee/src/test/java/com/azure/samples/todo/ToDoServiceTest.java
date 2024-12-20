package com.azure.samples.todo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ToDoServiceTest {

    @Mock
    private ToDoService toDoService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddToDoItem() {
        String username = "testUser";
        ToDoItem item = new ToDoItem();
        item.setDescription("Test ToDo Item");

        when(toDoService.addToDoItem(username, item)).thenReturn(item);

        ToDoItem result = toDoService.addToDoItem(username, item);

        assertNotNull(result);
        assertEquals("Test ToDo Item", result.getDescription());
        verify(toDoService, times(1)).addToDoItem(username, item);
    }

    @Test
    public void testFindToDoItemsByUsername() {
        String username = "testUser";
        ToDoItem item1 = new ToDoItem();
        item1.setDescription("Test ToDo Item 1");
        ToDoItem item2 = new ToDoItem();
        item2.setDescription("Test ToDo Item 2");

        List<ToDoItem> items = Arrays.asList(item1, item2);

        when(toDoService.findToDoItemsByUsername(username)).thenReturn(items);

        List<ToDoItem> result = toDoService.findToDoItemsByUsername(username);

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Test ToDo Item 1", result.get(0).getDescription());
        assertEquals("Test ToDo Item 2", result.get(1).getDescription());
        verify(toDoService, times(1)).findToDoItemsByUsername(username);
    }

    @Test
    public void testRemoveToDoItem() {
        String username = "testUser";
        Long id = 1L;

        doNothing().when(toDoService).removeToDoItem(username, id);

        toDoService.removeToDoItem(username, id);

        verify(toDoService, times(1)).removeToDoItem(username, id);
    }

    @Test
    public void testUpdateToDoItem() {
        String username = "testUser";
        ToDoItem item = new ToDoItem();
        item.setDescription("Updated ToDo Item");

        doNothing().when(toDoService).updateToDoItem(username, item);

        toDoService.updateToDoItem(username, item);

        verify(toDoService, times(1)).updateToDoItem(username, item);
    }
}