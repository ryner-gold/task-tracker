package com.tasktracker.service;

import com.tasktracker.error.TaskNotFoundException;
import com.tasktracker.model.Task;
import com.tasktracker.model.TaskList;
import com.tasktracker.repository.TasksRepository;

import java.util.UUID;

public class TaskService {
    private final TasksRepository tasksRepository = new TasksRepository ();

    public boolean createTask (String title, String description) {

        String taskTitle = formatTitle (title);
        Task task = new Task (UUID.randomUUID ().toString (), taskTitle, description);


        return tasksRepository.addCreatedTask (task);
    }


    public TaskList viewAllTasks () {
        return tasksRepository.getTaskList ();
    }

    public Task viewTaskById (String id) {
        return tasksRepository.getTaskById (id);
    }

    public TaskList updateTask (String id, Task task) {
        Task existingTask = viewTaskById (id);
        String title = existingTask.title ();
        String desc = existingTask.description ();

        if (task.title ().isEmpty () && task.description ().isEmpty ()) {

            return tasksRepository.updateTask (id, existingTask);

        }


        if (!task.title ().isEmpty ()) {
            title = task.title ();
        }


        if (!task.description ().isEmpty ()) {
            desc = task.description ();
        }

        Task taskToUpdate = new Task (existingTask.id (), title, desc);


        return tasksRepository.updateTask (id, taskToUpdate);

    }

    public boolean deleteTask (String id) {
        try {
            tasksRepository.deleteTask (id);
            return true;
        } catch (TaskNotFoundException e) {
            System.out.println ("Task not found" + e.getMessage ());
            return false;
        }
    }

    private String formatTitle (String title) {
        return title;
    }


}


