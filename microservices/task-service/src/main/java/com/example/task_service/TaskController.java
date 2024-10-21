package com.example.task_service;

import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    private List<Task> tasks = new ArrayList<>();

    @GetMapping("/")
    public List<Task> getTasks() {
        return tasks;
    }

    @GetMapping("/user/{userId}")
    public List<Task> getTasksByUserId(@PathVariable int userId) {
        List<Task> userTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getUserId() == userId) {
                userTasks.add(task);
            }
        }
        return userTasks;
    }

    @PostMapping("/")
    public Task createTask(@RequestBody Task task) {
        tasks.add(task);
        return task;
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable int id) {
        tasks.removeIf(task -> task.getId() == id);
    }

    @PutMapping("/{id}")
    public Task updateTask(@PathVariable int id, @RequestBody Task newTask) {
        for (Task task : tasks) {
            if (task.getId() == id) {
                task.setDescription(newTask.getDescription());
                task.setUserId(newTask.getUserId());
                return task;
            }
        }
        return null;
    }
}