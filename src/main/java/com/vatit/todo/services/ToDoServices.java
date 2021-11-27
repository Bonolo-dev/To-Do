package com.vatit.todo.services;

import com.vatit.todo.entities.ToDos;
import com.vatit.todo.repositories.ToDosRepo;
import com.vatit.todo.util.IllegalToDoNameException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ToDoServices {

    private ToDosRepo toDosRepo;
    private final String ILLEGAL_NAME = "I'm lazy";

    @Autowired
    public ToDoServices(ToDosRepo toDosRepo){
        this.toDosRepo = toDosRepo;
    }

    public List<ToDos> getAllToDos(){
        return toDosRepo.findAll();
    }

    public ToDos addToDo(String toDoItem){

        if(toDoItem.equalsIgnoreCase(ILLEGAL_NAME)){
            throw new IllegalToDoNameException();
        }
        ToDos toDo = new ToDos();
        toDo.setName(toDoItem);
        toDo.setCompleted(false);

        toDosRepo.save(toDo);

        return toDo;
    }

    public ToDos updateToDo(int id, ToDos toDoItem) throws IllegalToDoNameException {

        Optional<ToDos> optionalExistingToDo = toDosRepo.findById(id);
        ToDos existingTodo = optionalExistingToDo.isPresent()
                ? optionalExistingToDo.get() : optionalExistingToDo.orElseThrow(RuntimeException::new);

        existingTodo.setCompleted(toDoItem.getCompleted());
        existingTodo.setName(toDoItem.getName());

        toDosRepo.save(existingTodo);
        return existingTodo;
    }

    public void deleteToDo(int id) {
        toDosRepo.deleteById(id);
    }
}
