package com.vatit.todo.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vatit.todo.entities.ToDos;
import com.vatit.todo.services.ToDoServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ToDosController {

    private final ToDoServices toDoServices;

    @Autowired
    public ToDosController(ToDoServices toDoServices){
        this.toDoServices = toDoServices;
    }

    @GetMapping("/todos")
    public ResponseEntity<?> getAllToDos() throws JsonProcessingException {

        List<ToDos> allToDos= toDoServices.getAllToDos();

        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ObjectMapper().writeValueAsString(allToDos));
    }

    @PostMapping("/todos")
    public ResponseEntity<String> addToDos(@RequestBody ToDos toDoItem) throws JsonProcessingException {

        ToDos toDoResult = toDoServices.addToDo(toDoItem.getName());
        return ResponseEntity.status(HttpStatus.OK).body(new ObjectMapper().writeValueAsString(toDoResult));
    }
    @PutMapping("/todos/{id}")
    public ResponseEntity<String> updateToDos(@PathVariable int id,@RequestBody ToDos toDoItem) throws JsonProcessingException {

        ToDos toDoResult = toDoServices.updateToDo(id,toDoItem);
        return ResponseEntity.status(HttpStatus.OK).body(new ObjectMapper().writeValueAsString(toDoResult));
    }
    @DeleteMapping("/todos/{id}")
    public HttpStatus deleteToDo(@PathVariable int id){
        toDoServices.deleteToDo(id);
        return HttpStatus.OK;
    }

}
