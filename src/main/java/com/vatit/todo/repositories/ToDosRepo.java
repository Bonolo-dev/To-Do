package com.vatit.todo.repositories;

import com.vatit.todo.entities.ToDos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ToDosRepo extends JpaRepository<ToDos, Integer> {

    @Override
    List<ToDos> findAll();
}
