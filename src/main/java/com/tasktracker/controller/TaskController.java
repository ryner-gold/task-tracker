package com.tasktracker.controller;

import com.tasktracker.common.Scene;
import com.tasktracker.error.TaskServiceException;
import com.tasktracker.model.Task;
import com.tasktracker.model.request.CreateTaskRequest;
import com.tasktracker.model.request.UpdateTaskRequest;
import com.tasktracker.model.response.CreateTaskResponse;
import com.tasktracker.model.response.DeleteTaskResponse;
import com.tasktracker.model.response.UpdateTaskResponse;
import com.tasktracker.model.response.ViewTaskResponse;
import com.tasktracker.service.TaskService;

import java.util.Objects;


public class TaskController {

    private final TaskService taskService = new TaskService ();

    public Scene processInput (int option) {
        if (option == 6) {
            return Scene.QUIT;
        }
        if (option < 1 || option > 5) {
            System.out.println ("Option out of range.");
            return Scene.MAIN_MENU;
        }
        return renderScene (option);
    }

    private Scene renderScene (int option) {
        switch (option) {
            case 1:
                return Scene.CREATE;
            case 2:
                return Scene.VIEW;
            case 3:
                return Scene.VIEW_TASK;
            case 4:
                return Scene.UPDATE;
            case 5:
                return Scene.DELETE;
            case 6:
                return Scene.QUIT;
            default:
                return Scene.MAIN_MENU;
        }
    }


    public CreateTaskResponse createTask (CreateTaskRequest request) {
        boolean isTaskCreated = false;
        if (!checkIsRequestValid (request)) {
            return new CreateTaskResponse.Builder ().statusCode ("400").message (
                    "Bad request body, check title has no special characters").build ();
        }

        try {
            isTaskCreated = taskService.createTask (request.title (), request.description ());
        } catch (TaskServiceException e) {
            System.out.println ("Internal Server Error: " + e.getMessage ());
        }


        if (isTaskCreated) {
            return new CreateTaskResponse.Builder ().statusCode ("200").message ("Task added successfully").build ();
        } else {
            return new CreateTaskResponse.Builder ().statusCode ("500").message ("Something went wrong internally")
                                                    .build ();
        }
    }

    public CreateTaskResponse viewAllTasks () {
        return new CreateTaskResponse.Builder ().statusCode ("200").taskList (taskService.viewAllTasks ()).message (
                "All current tasks.").build ();
    }

    public ViewTaskResponse viewTaskById (String id) {
        return new ViewTaskResponse.Builder ().statusCode ("200").task (taskService.viewTaskById (id)).build ();
    }

    public UpdateTaskResponse updateTask (String id, UpdateTaskRequest request) {
        Task task = new Task (id, request.title (), request.description ());
        taskService.updateTask (id, task);
        return new UpdateTaskResponse.Builder ().statusCode ("200").taskList (taskService.viewAllTasks ()).message (
                "All current tasks.").build ();
    }

    public DeleteTaskResponse deleteTask (String id) {
        boolean isTaskDeleted = taskService.deleteTask (id);
        if (isTaskDeleted) {
            return new DeleteTaskResponse.Builder ().statusCode ("200").message ("Task deleted successfully.").build ();
        } else {
            return new DeleteTaskResponse.Builder ().statusCode ("400").message ("Task failed to delete.").build ();
        }
    }

    private boolean checkIsRequestValid (CreateTaskRequest request) {
        return !(Objects.isNull (request) || request.title ().isEmpty ());
    }


}
