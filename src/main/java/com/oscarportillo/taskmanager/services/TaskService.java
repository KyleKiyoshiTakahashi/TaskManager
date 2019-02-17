package com.oscarportillo.taskmanager.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.oscarportillo.taskmanager.models.Task;
import com.oscarportillo.taskmanager.repositories.TaskRepository;

@Service
public class TaskService {
	private final TaskRepository tr;
	
	public TaskService(TaskRepository tr) {
		this.tr = tr;
	}
	
	public Task createTask(Task task) {
		return tr.save(task);
	}


	
	// need a method to return a list of all the tasks
	// public List<Task> allTasks(){
	// 	return taskRepo.findAll();
	// }





	public Task findTask(Long id) {
		Optional<Task> optionalTask = tr.findById(id);
		if(optionalTask.isPresent()) {
			return optionalTask.get();
		} else {
			return null;
		}
	}
	
	// this isnt needed...see below
	public Task updateTask(Long id, String description, String assignee, String priority) {
		Optional<Task> boxWithTask = this.tr.findById(id);
		if(boxWithTask.isPresent()) {
			Task task = boxWithTask.get();
			task.setDescription(description);;
			task.setAssignee(assignee);
			task.setPriority(priority);
			Task updatedTask = this.tr.save(task);
			return updatedTask;
		} else {
			return null;
		}
	}
	// to update a task
	// public void updateTask(Task task) {
    // 	taskRepo.save(task);
    // }
    
    
	

	// this isn't needed. see below
	public void deleteTask(Long id) {
		Optional<Task> boxWithTask = this.tr.findById(id);
		if(boxWithTask.isPresent()) {
			Task task = boxWithTask.get();
			this.tr.delete(task);
		}
	}
	// to delete a task
	// public void deleteTask(Long id) {
    // 	taskRepo.deleteById(id);
    // }
}
