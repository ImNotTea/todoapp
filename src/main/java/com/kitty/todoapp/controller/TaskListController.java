package com.kitty.todoapp.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kitty.todoapp.model.TaskListModel;
import com.kitty.todoapp.service.TaskListService;




@RestController
@RequestMapping(path = "todoapp")
public class TaskListController {

    private TaskListService taskListService;


    public TaskListController(TaskListService taskListService) {
        this.taskListService = taskListService;
    }


    @GetMapping()
    public List<TaskListModel> getTaskList() {
        return this.taskListService.getTaskList();
    }


    @GetMapping(path = "{name}")
    public ResponseEntity<Optional> getTask(@PathVariable("name") String taskName) {
        try {
            Optional<TaskListModel> task = this.taskListService.findTaskByName(taskName);
            return ResponseEntity.status(HttpStatus.OK).body(task);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.OK).body(Optional.of(e.getMessage()));
        }
        
    }
    

    @PostMapping()
    public ResponseEntity<String> addNewTask(@RequestBody TaskListModel task) {
        try {
            this.taskListService.addNewTask(task);
            return ResponseEntity.status(HttpStatus.OK).body("Adding Succeed");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    
    @DeleteMapping(path = "{name}")
    public ResponseEntity<String> deleteTask(@PathVariable("name") String taskName) {
        try {
            this.taskListService.deleteTask(taskName);
            return ResponseEntity.status(HttpStatus.OK).body("Deleting Succeed"); 
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }


    @PutMapping(path = "{name}")
    public ResponseEntity<String> updateTask(
        @PathVariable("name") String taskName, 
        @RequestParam("description") Optional<String> taskDescription,
        @RequestParam("status") Optional<String> taskStatus) {
        
        try {
            this.taskListService.updateTask(taskName, taskDescription, taskStatus);
            return ResponseEntity.status(HttpStatus.OK).body("Updating Succeed"); 
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
