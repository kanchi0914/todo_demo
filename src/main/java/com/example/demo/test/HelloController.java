package com.example.demo.test;

import com.sun.xml.bind.v2.TODO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class HelloController {

    @Autowired
    EmployeeRepository empRepository;

    @Autowired
    TodoRepository todoRepository;

    List<Todo> searchedResults;

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping("/edit")
    public String getEdit() {
        return "edit";
    }

    @RequestMapping("/removeAll")
    public String removeAll() {
        empRepository.deleteAll();
        return "redirect:/hello";
    }

    @RequestMapping(value="/hello", method = RequestMethod.GET)
    public String getHello(Model model) {
        List<Employee> emplist=empRepository.findAll();
        model.addAttribute("emplist", emplist);
        return "top2";
    }

    @RequestMapping(value="/top", method = RequestMethod.GET)
    public String getTop(Model model) {
        List<Todo> todoList=todoRepository.findAll();
        model.addAttribute("todoList", todoList);
        return "top2";
    }

//    @RequestMapping(value="/add", method = RequestMethod.GET)
//    public String getTop(Model model) {
//        List<Employee> emplist=empRepository.findAll();
//        model.addAttribute("emplist", emplist);
//        return "top2";
//    }

    @RequestMapping("/add")
    public String addNewUser(@RequestParam("text1")String str, Model model){
        Todo t = new Todo();
        t.setContent(str);
        t.setDone(0);
        todoRepository.save(t);
        return "redirect:/top";
    }

//    @RequestMapping("/switch_done/{id}")
//    public String switchDone(@RequestParam("todo_checkbox")boolean isOn,
//                             Model model){
//        Todo t = new Todo();
//        //t.setId(todoRepository.count()+1);
//        if (isOn) t.setContent("TRUE");
//        else t.setContent("FALSE");
//        t.setDone(0);
//        todoRepository.save(t);
//        return "redirect:/top";
//    }

    @RequestMapping("/switch_done/{id}")
    public String switchDone(Todo todo, Model model){
        Todo t = todo;
        Long id = t.getId();
        Todo tss = todoRepository.getOne(id);
        if (tss.getDone()) tss.setDone(0);
        else tss.setDone(1);

        todoRepository.save(tss);
        return "redirect:/top";
    }

    @RequestMapping("/switch_done2/{id}")
    public String switchDone2(Todo todo, Model model){
        Todo t = todo;
        Long id = t.getId();
        Todo tss = todoRepository.getOne(id);

        if (tss.getDone()) tss.setDone(0);
        else tss.setDone(1);

        todoRepository.save(tss);
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
