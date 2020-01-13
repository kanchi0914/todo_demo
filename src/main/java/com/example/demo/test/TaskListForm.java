package com.example.demo.test;

import java.util.ArrayList;
import java.util.List;

public class TaskListForm {

    private List<Todo> taskList;

    public TaskListForm(){
        this.taskList = new ArrayList<Todo>();
    }

    public List<Todo> getTaskList(){
        return taskList;
    }

    public void setTaskList(List<Todo> taskList){
        this.taskList = taskList;
    }

}
