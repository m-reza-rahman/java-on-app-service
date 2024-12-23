package com.azure.samples.todo;

import java.io.Serializable;
import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;

@Path("/todo")
@ApplicationScoped
public class ToDoResource implements Serializable {
    private static final long serialVersionUID = 1L;

    @Inject
    private ToDoService service;

    @POST
    @Consumes({"application/json"})
    @Produces({"application/json"})
    public ToDoItem create(@Valid ToDoItem item) {
        return service.addToDoItem(item);
    }

    @PUT
    @Path("{id}")
    @Consumes({"application/json"})
    public void edit(
            @PathParam("id") Long id,
            @Valid ToDoItem item) {
        item.setId(id);
        service.updateToDoItem(item);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Long id) {
        service.removeToDoItem(id);
    }

    @GET
    @Produces({"application/json"})
    public List<ToDoItem> getAll() {
        return service.findAllToDoItems();
    }
}
