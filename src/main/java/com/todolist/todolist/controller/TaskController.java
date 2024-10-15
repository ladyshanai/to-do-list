package com.todolist.todolist.controller;

import com.todolist.todolist.dto.request.TaskDTORequest;
import com.todolist.todolist.dto.request.UpdateTaskDTORequest;
import com.todolist.todolist.dto.response.TaskDTOResponse;
import com.todolist.todolist.dto.response.TasksDTOResponse;
import com.todolist.todolist.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(path="/tasks")
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping()
    public ResponseEntity<TaskDTOResponse> create(@RequestBody TaskDTORequest createListDTORequest) throws Exception {
        TaskDTOResponse response = taskService.createTask(createListDTORequest);
        return ResponseEntity.ok(response);
    }

    @GetMapping()
    public ResponseEntity<List<TasksDTOResponse>> findTasks() throws Exception {
       List<TasksDTOResponse> response = taskService.findTasks();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateTask(@RequestBody UpdateTaskDTORequest taskDTORequest, @PathVariable Long id)throws Exception {
        taskService.updateTask(taskDTORequest, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable Long id)throws Exception {
        taskService.deleteTask(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/title")
    public ResponseEntity<List<TasksDTOResponse>> findTasksByTitle(@RequestParam String title) throws Exception {
        List<TasksDTOResponse> tasksDTOResponseList = taskService.findTasksByTitle(title);
        return new ResponseEntity<>(tasksDTOResponseList, HttpStatus.OK);
    }
}
