package models;

import java.time.LocalDate;

public class Task {
    private int id;
    private String title;
    private String description;
    private TaskPriority priority;
    private LocalDate deadline;
    private TaskStatus status;

    // Constructor for adding (no ID)
    public Task(String title, String description, TaskPriority priority, LocalDate deadline) {
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.deadline = deadline;
        this.status = TaskStatus.PENDING;
    }

    // Constructor with ID for database tasks
    public Task(int id, String title, String description, TaskPriority priority, LocalDate deadline, TaskStatus status) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.deadline = deadline;
        this.status = status;
    }

    // Getters
    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public TaskPriority getPriority() { return priority; }
    public LocalDate getDeadline() { return deadline; }
    public TaskStatus getStatus() { return status; }

    @Override
    public String toString() {
        return String.format("ID: %d | %s | %s | Priority: %s | Due: %s | Status: %s",
                id, title, description, priority, deadline, status);
    }
}
