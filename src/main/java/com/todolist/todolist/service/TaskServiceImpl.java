package com.todolist.todolist.service;

import com.todolist.todolist.dto.request.TaskDTORequest;
import com.todolist.todolist.dto.request.UpdateTaskDTORequest;
import com.todolist.todolist.dto.response.TaskDTOResponse;
import com.todolist.todolist.dto.response.TasksDTOResponse;
import com.todolist.todolist.entity.TaskEntity;
import com.todolist.todolist.repository.TaskRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService{
    private static final org.apache.logging.log4j.Logger LOG = org.apache.logging.log4j.LogManager.getLogger(TaskServiceImpl.class);
    private final TaskRepository taskRepository;

    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }


    @Override
    public TaskDTOResponse createTask(TaskDTORequest taskDTORequest) throws Exception {
        TaskEntity taskEntity = new TaskEntity();
        taskEntity.setTitle(taskDTORequest.getTitle());
        taskEntity.setDescription(taskDTORequest.getDescription());
        taskEntity.setCompleted(false);

        TaskEntity savedTaskEntity;
        try {
            savedTaskEntity = taskRepository.save(taskEntity);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Save Task Error", e);
        }
        TaskDTOResponse taskDTOResponse = new TaskDTOResponse();
        taskDTOResponse.setId(savedTaskEntity.getId());
        return taskDTOResponse;
    }

    @Override
    public List<TasksDTOResponse> findTasks() throws Exception {
        try {
            List<TaskEntity> taskEntities =taskRepository.findAll();
            return getTasksDTOResponses(taskEntities);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Finding tasks error", e);
        }
    }

    @Override
    public void updateTask(UpdateTaskDTORequest taskDTORequest, Long id) throws Exception {
        try {
            Optional<TaskEntity> taskEntity = taskRepository.findById(id);
            if (taskEntity.isPresent()) {
                taskEntity.get().setCompleted(taskDTORequest.getCompleted());
            }
            taskRepository.save(taskEntity.get());

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Update task error", e);
        }
    }

    @Override
    public void deleteTask(Long id) throws Exception {
        try {
            taskRepository.deleteById(id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Delete task Error", e);
        }
    }

    @Override
    public List<TasksDTOResponse> findTasksByTitle(String title) throws Exception {
        try {
            List<TaskEntity> taskEntities =taskRepository.findByTitle(title);
            return getTasksDTOResponses(taskEntities);
        } catch (Exception e) {
            LOG.error("Finding task by title error: {}", e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Finding task by title error", e);
        }
    }

    private List<TasksDTOResponse> getTasksDTOResponses(List<TaskEntity> taskEntities) {
        List<TasksDTOResponse> tasksDTOResponseList = new ArrayList<>();
        for (TaskEntity taskEntity : taskEntities){
            TasksDTOResponse tasksDTOResponse = new TasksDTOResponse();
            tasksDTOResponse.setId(taskEntity.getId());
            tasksDTOResponse.setTitle(taskEntity.getTitle());
            tasksDTOResponse.setDescription(taskEntity.getDescription());
            tasksDTOResponse.setCompleted(taskEntity.getCompleted());
            tasksDTOResponseList.add(tasksDTOResponse);
        }
        return tasksDTOResponseList;
    }
}
