package com.todolist.todolist.dto.response;

import lombok.Data;

@Data
public class TasksDTOResponse {
    private Long id;
    private String title;
    private String description;
    private Boolean completed;
}
