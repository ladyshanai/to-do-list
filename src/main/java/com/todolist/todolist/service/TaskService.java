package com.todolist.todolist.service;

import com.todolist.todolist.dto.request.TaskDTORequest;
import com.todolist.todolist.dto.request.UpdateTaskDTORequest;
import com.todolist.todolist.dto.response.TaskDTOResponse;
import com.todolist.todolist.dto.response.TasksDTOResponse;

import java.util.List;

public interface TaskService {

    TaskDTOResponse createTask(TaskDTORequest taskDTORequest) throws Exception;

    List<TasksDTOResponse> findTasks() throws Exception;

    void updateTask(UpdateTaskDTORequest taskDTORequest, Long id) throws Exception;

    void deleteTask(Long id) throws Exception;

    List<TasksDTOResponse> findTasksByTitle(String title) throws Exception;
}
