package com.example.notification_service;

public class Notification {
    private int id;
    private String message;
    private int userId; // Relacionado ao usuário

    public Notification() {}

    public Notification(int id, String message, int userId) {
        this.id = id;
        this.message = message;
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
