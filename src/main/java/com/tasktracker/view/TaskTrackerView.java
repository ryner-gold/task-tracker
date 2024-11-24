package com.tasktracker.view;

import com.tasktracker.common.Scene;
import com.tasktracker.controller.TaskController;
import com.tasktracker.error.TaskServiceException;
import com.tasktracker.model.request.CreateTaskRequest;
import com.tasktracker.model.request.UpdateTaskRequest;
import com.tasktracker.model.response.CreateTaskResponse;
import com.tasktracker.model.response.DeleteTaskResponse;
import com.tasktracker.model.response.UpdateTaskResponse;
import com.tasktracker.model.response.ViewTaskResponse;

import java.util.Objects;
import java.util.Scanner;

public class TaskTrackerView {

    private final TaskController taskController = new TaskController ();

    public void render (Scene scene) {
        switch (scene) {
            case MAIN_MENU:
                mainMenu ();
                break;
            case CREATE:
                createTaskView ();
                break;
            case VIEW:
                getAllTasksView ();
                break;
            case VIEW_TASK:
                getTaskByIdView ();
                break;
            case UPDATE:
                updateTaskView ();
                break;
            case DELETE:
                deleteTaskView ();
                break;
        }
    }


    public void mainMenu () {

        System.out.println ("What would you like to do?");
        System.out.println ("1. Create Task");
        System.out.println ("2. View Tasks");
        System.out.println ("3. View Task by ID ");
        System.out.println ("4. Update Task");
        System.out.println ("5. Delete Task");
        System.out.println ("6. Quit");
        System.out.println ("Select an option by number: ");

        Scanner scan = new Scanner (System.in);
        Scene currentScene;

        while (true) {
            try {
                int option = Integer.parseInt (scan.nextLine ());
                currentScene = taskController.processInput (option);
                break;
            } catch (NumberFormatException e) {
                System.out.println ("You have to use the numbers 1-5!");
            }
        }

        render (currentScene);
    }


    private void createTaskView () {
        Scanner scan = new Scanner (System.in);
        System.out.println ("__Task Creation View__");

        System.out.println ("Enter Title: ");
        String inputForTitle = scan.nextLine ();
        System.out.println ("Enter Desc: ");
        String inputForDesc = scan.nextLine ();
        CreateTaskRequest request = new CreateTaskRequest (inputForTitle, inputForDesc);
        try {
            CreateTaskResponse response = taskController.createTask (request);
            if (response.getStatusCode ().contains ("200")) {
                printTaskListFromResponse (response);
                render (Scene.MAIN_MENU);
            }

        } catch (TaskServiceException e) {
            System.out.println ("Server error occurred: " + e.getMessage ());
            render (Scene.MAIN_MENU);
        }
    }

    private void getAllTasksView () {
        System.out.println ("Current Tasks:");
        System.out.println ("===========");
        try {
            CreateTaskResponse response = taskController.viewAllTasks ();
            if (response.getStatusCode ().contains ("200")) {
                printTaskListFromResponse (response);
            }
            render (Scene.MAIN_MENU);
        } catch (TaskServiceException e) {
            System.out.println ("Server error occurred: " + e.getMessage ());
            render (Scene.MAIN_MENU);
        }
    }

    private void getTaskByIdView () {
        Scanner scan = new Scanner (System.in);
        System.out.println ("Enter ID to view task");
        String id = scan.nextLine ();
        ViewTaskResponse response = taskController.viewTaskById (id);
        response.getTask ().ifPresent (task -> {
            System.out.println ("Task found: " + task);
        });
        render (Scene.MAIN_MENU);
    }

    private void updateTaskView () {
        Scanner scan = new Scanner (System.in);
        System.out.println ("First, Enter the ID of the task you want to update: ");
        String id = scan.nextLine ();
        System.out.println ("Provide a new title, leave empty (press ENTER) to remain unchanged: ");
        String title = scan.nextLine ();
        System.out.println ("Provide a new description, leave empty (press ENTER) to remain unchanged: ");
        String desc = scan.nextLine ();
        UpdateTaskRequest request = new UpdateTaskRequest (title, desc);
        UpdateTaskResponse response = taskController.updateTask (id, request);
        if (!Objects.isNull (response)) {
            System.out.println (response.getTaskList ());
        }
    }

    private void deleteTaskView () {
        Scanner scan = new Scanner (System.in);
        System.out.println ("First, Enter the ID of the task you want to delete: ");
        String id = scan.nextLine ();
        try {
            DeleteTaskResponse response = taskController.deleteTask (id);
            if (response.getStatusCode ().contains ("200")) {
                System.out.println ("Task with ID " + id + " deleted successfully.");
                getAllTasksView ();
            } else {
                System.out.println ("Task failed to delete");
            }
        } catch (TaskServiceException e) {
            System.out.println ("Something in the Task Service went wrong." + e.getMessage ());
        }
    }


    private void printTaskListFromResponse (CreateTaskResponse response) {
        response.getTaskList ().ifPresent (taskList -> {
            System.out.println ("Task List:");
            System.out.println ("==============");
            taskList.tasks ().forEach (task -> {
                System.out.println ("ID: " + task.id ());
                System.out.println ("Title: " + task.title ());
                System.out.println ("Description: " + task.description ());
                System.out.println ("-------------------");
            });
        });
    }
}
