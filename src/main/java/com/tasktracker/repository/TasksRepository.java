package com.tasktracker.repository;

import com.tasktracker.error.TaskNotFoundException;
import com.tasktracker.model.Task;
import com.tasktracker.model.TaskList;

import java.util.ArrayList;

public class TasksRepository {
    private final TaskList taskList = new TaskList (new ArrayList<> ());

    public boolean addCreatedTask (com.tasktracker.model.Task task) {
        try {
            this.taskList.tasks ().add (task);
        } catch (Exception e) {
            System.out.println ("Something went wrong adding the task to the database.");
        }
        return true;
    }

    public int findIndexOfTask (Task task) {
        return taskList.tasks ().indexOf (task);
    }

    public TaskList getTaskList () {
        return this.taskList;
    }

    public Task getTaskById (String id) {
        return taskList.tasks ().stream ().filter (task -> task.id ().contains (id)).toList ().get (0);
    }

    public TaskList updateTask (String id, Task updatedTask) {
        for (int i = 0; i < taskList.tasks ().size (); i++) {
            Task task = taskList.tasks ().get (i);
            if (task.id ().contains (id)) {
                taskList.tasks ().set (i, updatedTask);
                break;
            }
        }
        return getTaskList ();
    }

    public void deleteTask (String taskToDeleteId) throws TaskNotFoundException {
        for (int i = 0; i < taskList.tasks ().size (); i++) {
            Task task = taskList.tasks ().get (i);
            if (task.id ().contains (taskToDeleteId)) {
                taskList.tasks ().remove (i);
                return;
            }

        }
    }
}