package com.kitty.todoapp.repository;

import org.aspectj.weaver.ast.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import java.util.Optional;
import com.kitty.todoapp.model.TaskListModel;

@Repository
public interface TaskListRepository extends JpaRepository<TaskListModel, Long> {
    @Query("SELECT t FROM TaskListModel t WHERE t.name = :name")
    Optional<TaskListModel> findTaskByName(@Param("name") String taskName);

}
