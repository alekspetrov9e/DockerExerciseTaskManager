package com.example.taskmanager;

import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;

import java.util.Scanner;

public class TaskManagerConsole {

    private static final String BASE_URL = "http://task-manager-server:8080/tasks";
    private final RestTemplate restTemplate = new RestTemplate();
    private final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        TaskManagerConsole app = new TaskManagerConsole();
        app.run();
    }

    private void run() {
        boolean running = true;
        while (running) {
            System.out.println("\nTask Manager Console");
            System.out.println("1. View All Tasks");
            System.out.println("2. Create a New Task");
            System.out.println("3. Update a Task");
            System.out.println("4. Delete a Task");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> viewAllTasks();
                case 2 -> createTask();
                case 3 -> updateTask();
                case 4 -> deleteTask();
                case 5 -> running = false;
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void viewAllTasks() {
        ResponseEntity<String> response = restTemplate.getForEntity(BASE_URL, String.class);
        System.out.println("Tasks: ");
        System.out.println(response.getBody());
    }

    private void createTask() {
        System.out.print("Enter task title: ");
        String title = scanner.nextLine();
        System.out.print("Enter task description: ");
        String description = scanner.nextLine();

        String requestBody = String.format("{\"title\": \"%s\", \"description\": \"%s\", \"completed\": false}", title, description);
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        HttpEntity<String> request = new HttpEntity<>(requestBody, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(BASE_URL, request, String.class);
        System.out.println("Task created: " + response.getBody());
    }

    private void updateTask() {
        System.out.print("Enter task ID to update: ");
        Long id = scanner.nextLong();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter new task title: ");
        String title = scanner.nextLine();
        System.out.print("Enter new task description: ");
        String description = scanner.nextLine();
        System.out.print("Is the task completed? (true/false): ");
        boolean completed = scanner.nextBoolean();
        scanner.nextLine(); // Consume newline

        String requestBody = String.format("{\"id\": %d, \"title\": \"%s\", \"description\": \"%s\", \"completed\": %b}", id, title, description, completed);
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        HttpEntity<String> request = new HttpEntity<>(requestBody, headers);

        restTemplate.exchange(BASE_URL + "/" + id, HttpMethod.PUT, request, String.class);
        System.out.println("Task updated.");
    }

    private void deleteTask() {
        System.out.print("Enter task ID to delete: ");
        Long id = scanner.nextLong();
        scanner.nextLine();
        restTemplate.delete(BASE_URL + "/" + id);
        System.out.println("Task deleted.");
    }
}

