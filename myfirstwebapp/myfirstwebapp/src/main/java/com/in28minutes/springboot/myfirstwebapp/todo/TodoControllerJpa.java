package com.in28minutes.springboot.myfirstwebapp.todo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import jakarta.validation.Valid;
// This is controller for todos to then sent data to jsp via modelmap 
@Controller
@SessionAttributes("name")
public class TodoControllerJpa {
	public TodoControllerJpa(TodoRepository todoRepository) {
		super();
		this.todoRepository = todoRepository;

		
	}
	private TodoRepository todoRepository;

	@RequestMapping("list-todos")
	public String listAllTodos(ModelMap model){
		String username = getLoggedInUsername(model);
		List<Todo> todos=todoRepository.findByUsername(username);
		model.addAttribute("todos",todos);
		return "listTodos";
	}
	private String getLoggedInUsername(ModelMap model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return authentication.getName();
	}
	@RequestMapping(value="add-todo",method = RequestMethod.GET)
	public String showNewTodoPage(ModelMap model){
		String username = getLoggedInUsername(model);
		Todo todo = new Todo(0,username,"",LocalDate.now().plusYears(1),false);
		model.put("todo", todo);
		return "todo";
	}
	@RequestMapping(value="add-todo",method = RequestMethod.POST)
	// this here is Todo todo is concept like form backing object or Command bean 
	// instead of binding it to method through RequestParam, here we are directly binding 
	// to the java bean. To make use of this backing object in jsp, we can make use of spring
	// spring form tag libraries.
	public String addNewTodo(ModelMap model,@Valid Todo todo,BindingResult result){
		if(result.hasErrors()) {
			return "todo";
		}
		String username = getLoggedInUsername(model);
		todo.setUsername(username);
		todoRepository.save(todo);
		//todoService.addTodo(username,todo.getDescription(),todo.getTargetDate(),todo.isDone());
		return "redirect:list-todos";
	}
	@RequestMapping("delete-todo")
	public String deleteTodo(@RequestParam int id){
		todoRepository.deleteById(id);
		//todoService.deleteById(id);
		return "redirect:list-todos";
	}
	@RequestMapping(value="update-todo",method = RequestMethod.GET)
	public String showUpdateTodoPage(@RequestParam int id,ModelMap model){
		Todo todo=todoRepository.findById(id).get();
		model.addAttribute("todo",todo);
		return "todo";
	}
	@RequestMapping(value="update-todo",method = RequestMethod.POST)
	public String updateTodo(ModelMap model,@Valid Todo todo,BindingResult result){
		if(result.hasErrors()) {
			return "todo";
		}
		String username = getLoggedInUsername(model);
		todo.setUsername(username);
		todoRepository.save(todo);
		return "redirect:list-todos";
	}
	
	
}
