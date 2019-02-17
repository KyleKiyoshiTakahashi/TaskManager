package com.oscarportillo.taskmanager.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.oscarportillo.taskmanager.models.Task;

@Repository
public interface TaskRepository extends CrudRepository<Task, Long> {
    // when you are finding all the tasks using the built in findAll() from the Crud repo, it returns an Iterable and we need it as a list so we need to write our own method
    // List<Task> findAll();
    // this will return a list of all your tasks.
}
