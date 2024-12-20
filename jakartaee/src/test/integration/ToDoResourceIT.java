package com.azure.samples.todo;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class ToDoResourceIT {

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 8080;
    }

    @Test
    public void testCreateToDoItem() {
        ToDoItem item = new ToDoItem();
        item.setDescription("Test ToDo Item");

        given()
            .contentType(ContentType.JSON)
            .body(item)
        .when()
            .post("/resources/todos/testUser")
        .then()
            .statusCode(200)
            .body("description", equalTo("Test ToDo Item"));
    }

    @Test
    public void testGetToDoItems() {
        given()
            .contentType(ContentType.JSON)
        .when()
            .get("/resources/todos/testUser")
        .then()
            .statusCode(200)
            .body("size()", greaterThan(0));
    }

    @Test
    public void testUpdateToDoItem() {
        ToDoItem item = new ToDoItem();
        item.setDescription("Updated ToDo Item");

        given()
            .contentType(ContentType.JSON)
            .body(item)
        .when()
            .put("/resources/todos/testUser/1")
        .then()
            .statusCode(200)
            .body("description", equalTo("Updated ToDo Item"));
    }

    @Test
    public void testDeleteToDoItem() {
        given()
            .contentType(ContentType.JSON)
        .when()
            .delete("/resources/todos/testUser/1")
        .then()
            .statusCode(200);
    }
}