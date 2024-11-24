package com.tasktracker;

import com.tasktracker.view.TaskTrackerView;

public class Main {

    private static final TaskTrackerView taskTrackerView = new TaskTrackerView();

    public static void main(String[] args) {

        taskTrackerView.mainMenu();
        System.out.println("Thanks for trying out Task Tracker!");

    }
}