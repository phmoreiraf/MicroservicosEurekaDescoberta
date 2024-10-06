package com.example.user_service;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private List<User> users = new ArrayList<>();
    private RestTemplate restTemplate = new RestTemplate(); // Para chamadas entre serviços

    @GetMapping("/")
    public List<User> getUsers() {
        return users;
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable int id) {
        return users.stream().filter(user -> user.getId() == id).findFirst().orElse(null);
    }

    @GetMapping("/{id}/tasks")
    public List<?> getUserTasks(@PathVariable int id) {
        // Consultar tarefas do usuário via Task Service
        String taskServiceUrl = "http://localhost:8082/tasks/user/" + id;
        return restTemplate.getForObject(taskServiceUrl, List.class);
    }

    @GetMapping("/{id}/notifications")
    public List<?> getUserNotifications(@PathVariable int id) {
        // Consultar notificações do usuário via Notification Service
        String notificationServiceUrl = "http://localhost:8083/notifications/user/" + id;
        return restTemplate.getForObject(notificationServiceUrl, List.class);
    }

    @PostMapping("/")
    public User createUser(@RequestBody User user) {
        users.add(user);
        return user;
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable int id) {
        users.removeIf(user -> user.getId() == id);
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable int id, @RequestBody User newUser) {
        for (User user : users) {
            if (user.getId() == id) {
                user.setName(newUser.getName());
                return user;
            }
        }
        return null;
    }
}
