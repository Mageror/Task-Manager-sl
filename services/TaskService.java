package services;

import db.DatabaseUtil;
import models.Task;
import models.TaskPriority;
import models.TaskStatus;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TaskService {
    public void addTask(Task task) {
        String sql = "INSERT INTO tasks (title, description, priority, deadline, status) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, task.getTitle());
            stmt.setString(2, task.getDescription());
            stmt.setString(3, task.getPriority().name());
            stmt.setDate(4, Date.valueOf(task.getDeadline()));
            stmt.setString(5, task.getStatus().name());
            stmt.executeUpdate();
            System.out.println("[+] Task added successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Task> getAllTasks() {
        List<Task> taskList = new ArrayList<>();
        String sql = "SELECT * FROM tasks";

        try (Connection conn = DatabaseUtil.getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Task task = new Task(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("description"),
                        TaskPriority.valueOf(rs.getString("priority")),
                        rs.getDate("deadline").toLocalDate(),
                        TaskStatus.valueOf(rs.getString("status"))
                );
                taskList.add(task);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return taskList;
    }

    public void deleteTaskById(int id) {
        String sql = "DELETE FROM tasks WHERE id = ?";

        try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("[+] Task deleted!");
            } else {
                System.out.println("[-] Task not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void markTaskAsCompleted(int id) {
        String sql = "UPDATE tasks SET status = 'COMPLETED' WHERE id = ?";

        try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("[✓] Task marked as completed!");
            } else {
                System.out.println("[-] Task not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void viewTasksByPriority(TaskPriority priority) {
        String sql = "SELECT * FROM tasks WHERE priority = ?";
        boolean found = false;

        try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, priority.name());
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Task task = new Task(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("description"),
                        TaskPriority.valueOf(rs.getString("priority")),
                        rs.getDate("deadline").toLocalDate(),
                        TaskStatus.valueOf(rs.getString("status"))
                );
                System.out.println(task);
                found = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (!found) System.out.println("[!] No tasks with priority: " + priority);
    }

    public void viewTasksByStatus(TaskStatus status) {
        String sql = "SELECT * FROM tasks WHERE status = ?";
        boolean found = false;

        try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, status.name());
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Task task = new Task(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("description"),
                        TaskPriority.valueOf(rs.getString("priority")),
                        rs.getDate("deadline").toLocalDate(),
                        TaskStatus.valueOf(rs.getString("status"))
                );
                System.out.println(task);
                found = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (!found) System.out.println("[!] No tasks with status: " + status);
    }

    public void viewTasksByDate(LocalDate date) {
        String sql = "SELECT * FROM tasks WHERE deadline = ?";
        boolean found = false;

        try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDate(1, Date.valueOf(date));
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Task task = new Task(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("description"),
                        TaskPriority.valueOf(rs.getString("priority")),
                        rs.getDate("deadline").toLocalDate(),
                        TaskStatus.valueOf(rs.getString("status"))
                );
                System.out.println(task);
                found = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (!found) System.out.println("[!] No tasks due on: " + date);
    }
}
