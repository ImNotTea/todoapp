package com.kitty.todoapp.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.kitty.todoapp.model.TaskListModel;
import com.kitty.todoapp.repository.TaskListRepository;

import jakarta.transaction.Transactional;

@Service
public class TaskListService {

    private final TaskListRepository taskListRepository;


    public TaskListService(TaskListRepository taskListRepository) {
        this.taskListRepository = taskListRepository;
    }


    public List<TaskListModel> getTaskList() {
        return this.taskListRepository.findAll();
    }

    
    public Optional<TaskListModel> findTaskByName(String taskName) {
        return this.taskListRepository.findTaskByName(taskName);
    }


    public void addNewTask(TaskListModel task) throws Exception {
        Optional<TaskListModel> taskFoundByName = this.taskListRepository.findTaskByName(task.getName());

        if (taskFoundByName.isPresent()) {
            throw new Exception("Task existing");
        }
        task.setTransferDate(LocalDateTime.now());
        task.setStatus("New");
        this.taskListRepository.save(task);
    }


    public void deleteTask(String taskName) throws Exception {
        Optional<TaskListModel> taskFoundByName = this.taskListRepository.findTaskByName(taskName);

        if(!taskFoundByName.isPresent()) {
            throw new Exception("Task not found");
        }

        this.taskListRepository.delete(taskFoundByName.get());
    }


    @Transactional
    public void updateTask(String taskName, Optional<String> description, Optional<String> status) throws Exception {
        Optional<TaskListModel> taskFoundByName = this.taskListRepository.findTaskByName(taskName);

        if(!taskFoundByName.isPresent()) {
            throw new Exception("Task not found");
        }

        TaskListModel task = taskFoundByName.get();
        task.setDescription(description.isPresent() ? description.get() : task.getDescription());
        task.setStatus(status.isPresent() ? status.get() : task.getDescription());
        task.setTransferDate(LocalDateTime.now());
    }
}