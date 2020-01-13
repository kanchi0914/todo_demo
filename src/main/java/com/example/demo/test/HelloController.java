package com.example.demo.test;

import com.sun.org.apache.xpath.internal.operations.Mod;
import com.sun.xml.bind.v2.TODO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class HelloController {

    @Autowired
    TodoRepository todoRepository;

    List<Todo> searchedResults;

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping(value="/edit", method = RequestMethod.GET)
    public ModelAndView taskIndex(Todo taskFormData) {
        ModelAndView modelAndView = new ModelAndView();
        List<Todo> todoList = todoRepository.findAll();
        TaskListForm taskListForm = new TaskListForm();
        taskListForm.setTaskList(todoList);
        modelAndView.addObject("taskListForm", taskListForm);
        return modelAndView;
    }

    @RequestMapping("/removeAll")
    public String removeAll() {
        todoRepository.deleteAll();
        return "redirect:/top";
    }

    @RequestMapping(value="/top", method = RequestMethod.GET)
    public String getTop(Model model) {
        List<Todo> todoList=todoRepository.findAll();
        model.addAttribute("todoList", todoList);
        return "top";
    }

    @RequestMapping("/add")
    public String addNewUser(@RequestParam("text1")String str, Model model){
        Todo t = new Todo();
        t.setContent(str);
        t.setDone(0);
        todoRepository.save(t);
        return "redirect:/top";
    }

    @RequestMapping("/switch_done/{id}")
    public String switchDone(Todo todo, Model model){
        Todo t = todo;
        Long id = t.getId();
        System.out.println(id);
        Todo tss = todoRepository.getOne(id);
        if (tss.getDone()) tss.setDone(0);
        else tss.setDone(1);
        todoRepository.save(tss);
        return "redirect:/top";
    }

    @RequestMapping(value = "/save_all", method = RequestMethod.POST)
    public String saveAll (@ModelAttribute TaskListForm taskListForm){
        List<Todo> taskList = taskListForm.getTaskList();
        for (Todo t: taskList){
            //System.out.println(t.getId());
            Long id = t.getId();
            Todo tss = todoRepository.getOne(id);
            tss.setContent(t.getContent());
            todoRepository.save(tss);
        }
        return "redirect:/top";
    }

    @GetMapping("/search/{queryText}")
    public String getSearch(@PathVariable("queryText") String queryText, Model model) {
        model.addAttribute("resultsList", searchedResults);
        return "searchedResults";
    }

    @PostMapping("/search")
    public String postRequest(@RequestParam("text1") String queryText, Model model){
        //List<Todo> results = todoRepository.findByLikeName(str);
        searchedResults = todoRepository.findByLikeName(queryText);
        model.addAttribute( "resultsList", searchedResults);
        model.addAttribute("queryText", queryText);
        return "searchedResults";
    }

}
