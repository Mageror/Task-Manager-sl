import models.Task;
import models.TaskPriority;
import models.TaskStatus;
import services.TaskService;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TaskService service = new TaskService();

        while (true) {
            System.out.println("\n=== Solo Leveling Task System ===");
            System.out.println("1. Add Task");
            System.out.println("2. View All Tasks");
            System.out.println("3. Delete Task");
            System.out.println("4. Mark Task as Completed");
            System.out.println("5. Filter by Priority");
            System.out.println("6. Filter by Status");
            System.out.println("7. Filter by Deadline");
            System.out.println("8. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter description: ");
                    String desc = scanner.nextLine();
                    System.out.print("Enter priority (LOW, MEDIUM, HIGH): ");
                    TaskPriority priority = TaskPriority.valueOf(scanner.nextLine().toUpperCase());
                    System.out.print("Enter deadline (YYYY-MM-DD): ");
                    LocalDate deadline = LocalDate.parse(scanner.nextLine());
                    Task task = new Task(title, desc, priority, deadline);
                    service.addTask(task);
                }
                case 2 -> {
                    List<Task> tasks = service.getAllTasks();
                    if (tasks.isEmpty()) {
                        System.out.println("[!] No tasks found.");
                    } else {
                        for (Task t : tasks) {
                            System.out.println(t);
                        }
                    }
                }
               case 3 -> {
                    System.out.print("Enter task ID to delete: ");
                    int id = scanner.nextInt();
                    scanner.nextLine(); // <== consume the leftover newline
                        service.deleteTaskById(id);
                }
                  case 4 -> {
                        System.out.print("Enter task ID to mark as completed: ");
                        int id = scanner.nextInt();
                        scanner.nextLine(); // <== consume the leftover newline
                        service.markTaskAsCompleted(id);
                }

                case 5 -> {
                    System.out.print("Enter priority to filter (LOW, MEDIUM, HIGH): ");
                    TaskPriority p = TaskPriority.valueOf(scanner.nextLine().toUpperCase());
                    service.viewTasksByPriority(p);
                }
                case 6 -> {
                    System.out.print("Enter status to filter (PENDING, COMPLETED): ");
                    TaskStatus s = TaskStatus.valueOf(scanner.nextLine().toUpperCase());
                    service.viewTasksByStatus(s);
                }
                case 7 -> {
                    System.out.print("Enter date to filter (YYYY-MM-DD): ");
                    LocalDate date = LocalDate.parse(scanner.nextLine());
                    service.viewTasksByDate(date);
                }
                case 8 -> {
                    System.out.println("Exiting... Goodbye!");
                    scanner.close();
                    return;
                }
                default -> System.out.println("Invalid choice. Try again.");
            }
            
        }
       // scanner.close();
    }
}
