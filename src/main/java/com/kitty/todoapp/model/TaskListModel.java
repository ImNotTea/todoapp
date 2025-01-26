package com.kitty.todoapp.model;


import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Tasks", schema = "todoapp")
public class TaskListModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private String description;
    private String status;

    @JsonFormat(pattern = "dd-MM-yyyy", shape = JsonFormat.Shape.STRING)
    private LocalDateTime transferDate;

    // Default constructor
    public TaskListModel() {

    }


    public TaskListModel(String taskName, String description) {
        this.name = taskName;
        this.description = description;
        this.status = "New";
    }


    public TaskListModel(String taskName, String description, LocalDateTime transferDate) {
        this(taskName, description);
        this.transferDate = transferDate;
    }


    public TaskListModel(long id, String taskName, String description, LocalDateTime transferDate) {
        this(taskName, description, transferDate);
        this.id = id;
    }


    public String getName() {
        return this.name;
    }


    public String getDescription() {
        return this.description;
    }


    public String getStatus() {
        return this.status;
    }


    public LocalDateTime getTransferDate() {
        return this.transferDate;
    }


    public void setName(String name) {
        this.name = name;
    }


    public void setDescription(String description) {
        this.description = description;
    }


    public void setStatus(String status) {
        this.status = status;
    }


    public void setTransferDate(LocalDateTime date) {
        this.transferDate = date;
    }


    @Override
    public String toString() {
        return 
        "{" +
        "name: " + this.name +
        "description: " + this.description + 
        "status: " + this.status + 
        "transferdate: " + this.transferDate
        + "}";
    }
}
