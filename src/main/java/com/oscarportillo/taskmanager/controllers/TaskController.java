package com.oscarportillo.taskmanager.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.oscarportillo.taskmanager.models.Task;
import com.oscarportillo.taskmanager.models.User;
import com.oscarportillo.taskmanager.services.TaskService;
import com.oscarportillo.taskmanager.services.UserService;

@Controller

public class TaskController {
	 private final UserService userService;
	 private final TaskService ts;
	 public TaskController(UserService userService, TaskService ts) {
	     this.userService = userService;
	     this.ts = ts;
	 }
	@GetMapping("tasks")
	 public String home(HttpSession session, Model model) {
	     // get user from session, save them in the model and return the home page
		 Long userId = (Long) session.getAttribute("userId");
		 User u = userService.findUserById(userId);
		 model.addAttribute("user", u);
		// $$$ here you will need to get your list of all tasks from the service.  
		// List<Task> tasks = taskService.allTasks();
		// then append them to the model 
		// model.addAttribute("tasks", tasks);
		 return "home.jsp";
	}
	@GetMapping("tasks/new")
	// you will need to add BindingResult result. and HttpSession session
	// public String newTaskPage(@ModelAttribute("task") Task task, BindingResult result, HttpSession session, Model model) 
	public String newTaskPage(@ModelAttribute("task") Task task, Model model) {
		// first you want to prevent someone from directly going to the url without signing in.  To do this we verify they are in session
		// if(session.getAttribute("userId")==null){
			// return "redirect:/"
		// }
		// then we have to find the user by id using the user service and then add the user info that we got from the session and add it to a model
		// User user = userService.findUserById((Long) session.getAttribute("userId"));
		// model.addAttribute("user", user);
		model.addAttribute("allUsers",  userService.findAllUsers());  
		return "newTask.jsp";
	}
	@PostMapping("/tasks")
	public String create(@Valid @ModelAttribute("task") Task task, BindingResult result) {
		if(result.hasErrors()) {
			// pretty sure you need a / infront of tasks
			return "redirect:tasks/new";
		} else {
			// you don't need to create a new variable 
			// ts.createTask(task);
			Task createdTask= ts.createTask(task);
			return "redirect:/tasks";
		}
	}
	// create a GetMapping for showing the details of a single task
	// @GetMapping("/tasks/{id}")
	// public String viewTask(@PathVariable("id") Long id,  Model model, HttpSession session){
		// first check if the user is in session
		// if(session.getAttribute("userId") == null) {
		// 	return "redirect:/";
		// }
		// you will need to use the task service to find a task by id and store it in a variable then add it to a model to send to the page.
		// Task task = taskService.findTaskById(id);
		// model.addAttribute("task", task);
		// create a new jsp page to show the details of a specific task
		// in the jsp page, if you want to see the user who created the task, it would be <p>Created by: <c:out value="${task.user.name}"/></p>
		// return "show.jsp";
	// }

	// create a GetMapping for editing
	// @GetMapping("/tasks/{id}/edit")
	// public String editTask(@PathVariable("id") Long id, @ModelAttribute("idea") Idea idea, Model model, HttpSession session){
	// 	if(session.getAttribute("userId") == null) {
	// 		return "redirect:/";
	// 	}
	// 	User user = userService.findUserById((Long)session.getAttribute("userId"));
	// 	Need to check if the task that is selected was created by the user that is in session.  If it is, then find the task by ID and add it to a model.
	// 	if(taskService.findTaskById(id).getUser().getId() == user.getId()) {
	// 		model.addAttribute("idea", taskService.findTaskById(id));
	// 		return "edit.jsp";
	// 	}
	// 	else {
	// 		return "redirect:/ideas";
	// 	}
	// }

	// Create a PutMapping route
	// I added an example of how you should do your edit.jsp page using hidden inputs
	// @PutMapping("/ideas/{id}/edit")
	// public String edit(@PathVariable("id") Long id, @Valid @ModelAttribute("idea") Idea idea, BindingResult result, Model model, HttpSession session){
	// 	first we need to get the users info from session since what we are editing has a direct connection to the user who created it
	// 	User user = userService.findUserById((Long)session.getAttribute("userId"));
	// 	then we check to see if there are any errors
	// 	if(result.hasErrors()) {
	// 		model.addAttribute("errors", result);
	// 		return "edit.jsp";
	// 	}
	// 	else {
	// 		we find the task by ID
	// 		Task taskEdit = taskService.findTaskById(id);
	// 		we send the task info to the page	
	// 		model.addAttribute("task", taskEdit);
	// 		we get the user from session from up above and 	send it to the page. we need to connect the user to the task that is being updated.  this is done via hidden input on the form.
	// 		model.addAttribute("user", user);
	// 		we then set the user to the task	
	// 		task.setUser(user);
	// 		we then update the task
	// 		taskService.updateTask(task);
	// 		return "redirect:/ideas";
	// 	}
	// }
	// create a request mapping route for delete
	// @RequestMapping("/tasks/{id}/delete")
	// public String delete(@PathVariable("id") Long id){
	// 	taskService.deleteTask(id);
	// 	return "redirect:/tasks";
	// }

}




// @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
// example of how the edit.jsp page should look
// <h3>Edit <c:out value="${task.name}"/></h3>
// <form:form method="post" action="/tasks/${id}/edit" modelAttribute="idea">
		
// 				<input type="hidden" name="_method" value="put">
// 				<h4>
// 					<form:label path="name">Name:</form:label>
// 					<form:input  type="text" path="name"/>
					
// 				</h4>
// 				<form:hidden path="user" value="${user.id}"/>
// 				<input type="submit" value="Edit"/>
			
// </form:form>
// <form:errors  path="task.*"/>
// @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
