package com.example.notification_service;

import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/notifications")
public class NotificationController {
    private List<Notification> notifications = new ArrayList<>();

    @GetMapping("/")
    public List<Notification> getNotifications() {
        return notifications;
    }

    @GetMapping("/user/{userId}")
    public List<Notification> getNotificationsByUserId(@PathVariable int userId) {
        List<Notification> userNotifications = new ArrayList<>();
        for (Notification notification : notifications) {
            if (notification.getUserId() == userId) {
                userNotifications.add(notification);
            }
        }
        return userNotifications;
    }

    @PostMapping("/")
    public Notification createNotification(@RequestBody Notification notification) {
        notifications.add(notification);
        return notification;
    }

    @DeleteMapping("/{id}")
    public void deleteNotification(@PathVariable int id) {
        notifications.removeIf(notification -> notification.getId() == id);
    }

    @PutMapping("/{id}")
    public Notification updateNotification(@PathVariable int id, @RequestBody Notification newNotification) {
        for (Notification notification : notifications) {
            if (notification.getId() == id) {
                notification.setMessage(newNotification.getMessage());
                notification.setUserId(newNotification.getUserId());
                return notification;
            }
        }
        return null;
    }
}
