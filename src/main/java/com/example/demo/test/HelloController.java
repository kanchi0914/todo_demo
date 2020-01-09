package com.example.demo.test;

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
        model.addAttribute("emplist", todoList);
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
        Employee e = new Employee();
        e.setId(empRepository.count()+1);
        e.setEmpname(str);
        empRepository.save(e);
        return "redirect:/hello";
    }


//
//    @PostMapping("/add")
//    public String addNewUser(@RequestParam("text1")String str, Model model){
//        Employee e = new Employee();
//        e.setId(empRepository.count()+1);
//        e.setEmpname(str);
//        empRepository.save(e);
//        return "helloResponse";
//    }

    @PostMapping("/search")
    public String postRequest(@RequestParam("text1")String str, Model model){
        model.addAttribute("sample", str);
        return "helloResponse";
    }

}
