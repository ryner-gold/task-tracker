package com.src.tasktracker;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {
    public static StringBuilder getUserInputForTaskTitle() {
        StringBuilder taskTitle = new StringBuilder();
        String input;

        try (Scanner scan = new Scanner(System.in)) {
            System.out.println("Enter Task Title");
            System.out.println("No special characters are allowed.");
            input = scan.nextLine();
        }  // Scanner automatically closes here for PMD warning

        return taskTitle.append(input);
    }

    public static StringBuilder formatTitle(StringBuilder title) {
        // Remove all leading white space
        for (int i = 0; i < title.length(); i++) {
            if (!Character.isWhitespace(title.charAt(i))) {
                title.delete(0,
                             i);
                break;
            }
        }

        title.setCharAt(0,
                        Character.toUpperCase(title.charAt(0)));

        // Remove all ending white space
        for (int c = title.length() - 1; c > 0; c--) {
            if (!Character.isWhitespace(title.charAt(c))) {
                title.delete(c + 1,
                             title.length());
                break;
            }
        }


        return title;
    }

    public static boolean validateTaskTitle(StringBuilder title) {
        String success = "Task title set.";
        String failureContainsSpecialChars = "Task contains special characters.";
        String failureOnlyWhitespace = "Task is empty. . . did you forget to type?";

        boolean result = Pattern.compile("[^a-z0-9 ]",
                                         Pattern.CASE_INSENSITIVE)
                                .matcher(title)
                                .find();

        if (title.chars()
                 .allMatch(Character::isWhitespace)) {
            System.out.println(failureOnlyWhitespace);
            return false;
        }

        if (result) {
            System.out.println(failureContainsSpecialChars);
            return false;
        } else {
            System.out.println(success);
            return true;
        }
    }

    public static void main(String[] args) {

        ArrayList<Task> listOfTasks = new ArrayList<Task>();
        Task task = new Task();

        while (true) {
            StringBuilder userInput = formatTitle(getUserInputForTaskTitle());
            if (validateTaskTitle(userInput)) {
                task.taskTitle = userInput.toString();
                break;
            }
        }

        System.out.println("Task Title: " + task.taskTitle);
    }
}
