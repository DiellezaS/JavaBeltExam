package com.codingdojo.dielleza.beltexam.controllers;

import com.codingdojo.dielleza.beltexam.models.LoginUser;
import com.codingdojo.dielleza.beltexam.models.TableMaster;
import com.codingdojo.dielleza.beltexam.services.TableMasterService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.ui.Model;
import com.codingdojo.dielleza.beltexam.models.User;
import com.codingdojo.dielleza.beltexam.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class MainController {

    @Autowired
    private UserService userService;
    @Autowired
    private TableMasterService tableMasterService;

    @GetMapping("/")
    public String index(Model model, @ModelAttribute("newUser") User newUser,
                        @ModelAttribute("newLogin") User newLogin, HttpSession session) {
        if (session.getAttribute("userId") != null) {
            return "redirect:/home";}

        model.addAttribute("newUser", new User());
        model.addAttribute("newLogin", new LoginUser());

        return "index";
    }


    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("newUser") User newUser,
                           BindingResult result, Model model, HttpSession session) {

        User user = userService.register(newUser, result);

        if(result.hasErrors()) {
            model.addAttribute("newLogin", new LoginUser());
            return "index";
        }
        session.setAttribute("userId", user.getId());

        return "redirect:/home";
    }

    @PostMapping("/login")
    public String login(@Valid @ModelAttribute("newLogin") LoginUser newLogin,
                        BindingResult result, Model model, HttpSession session) {

        User user = userService.login(newLogin, result);

        if(result.hasErrors() || user==null) {
            model.addAttribute("newUser", new User());
            return "index";
        }

        session.setAttribute("userId", user.getId());

        return "redirect:/home";
    }


    @GetMapping("/home")
    public String dashboard(HttpSession session, Model model) {
        if(session.getAttribute("userId") == null) {
            return "redirect:/logout";
        }
        Long userId = (Long) session.getAttribute("userId");
        User user = userService.findById(userId);

        model.addAttribute("user", user);
        model.addAttribute("assigned", tableMasterService.getAssigned(user));
        model.addAttribute("getByTime", tableMasterService.getByTime());
        return "dashboard";

    }

    @GetMapping("/tables/new")
    public String newTable(@ModelAttribute("tableMaster") TableMaster tableMaster, HttpSession session, Model model) {
        if(session.getAttribute("userId") == null) {
            return "redirect:/logout";
        }
        Long userId = (Long) session.getAttribute("userId");

        model.addAttribute("userId", userId);
        return "newTable";
    }

    @PostMapping("/tables/new")
    public String addNewTable(@Valid @ModelAttribute("tableMaster") TableMaster tableMaster, BindingResult result, HttpSession session) {

        if(session.getAttribute("userId") == null) {
            return "redirect:/logout";
        }

        if(result.hasErrors()) {
            return "newTable";
        }else {
           tableMasterService.add(tableMaster);

            Long userId = (Long) session.getAttribute("userId");
            User user = userService.findById(userId);
            user.getTables().add(tableMaster);
            userService.updateUser(user);

            return "redirect:/home";
        }
    }
    @GetMapping("/tables")
    public String tables(HttpSession session, Model model) {


        if(session.getAttribute("userId") == null) {
            return "redirect:/logout";
        }
        Long userId = (Long) session.getAttribute("userId");
        User user = userService.findById(userId);

        model.addAttribute("user", user);
        model.addAttribute("unassigned", tableMasterService.getUnassigned(user));
        model.addAttribute("getByTime", tableMasterService.getByTime());
        return "tables";

    }


    @GetMapping("/tables/edit/{id}")
    public String openEditTable(@PathVariable("id") Long id, HttpSession session, Model model) {

        if(session.getAttribute("userId") == null) {
            return "redirect:/logout";
        }

        TableMaster tableMaster = tableMasterService.findById(id);
        model.addAttribute("tableMaster", tableMaster);
        return "editTable";
    }

    @PostMapping("/tables/edit/{id}")
    public String editTable(
            @PathVariable("id") Long id,
            @Valid @ModelAttribute("tableMaster") TableMaster tableMaster,
            BindingResult result,
            HttpSession session) {

        if(session.getAttribute("userId") == null) {
            return "redirect:/logout";
        }
        Long userId = (Long) session.getAttribute("userId");

        User user = userService.findById(userId);

        if(result.hasErrors()) {
            return "editTable";
        }else {
            TableMaster thisTableMaster=tableMasterService.findById(id);
            tableMaster.setUsers(thisTableMaster.getUsers());
            tableMaster.setLead(user);
            tableMasterService.add(tableMaster);
            return "redirect:/home";
        }
    }

    @RequestMapping("/tables/delete/{id}")
    public String deleteProject(@PathVariable("id") Long id, HttpSession session) {

        if(session.getAttribute("userId") == null) {
            return "redirect:/logout";
        }

        TableMaster tableMaster =tableMasterService.findById(id);
        tableMasterService.delete(tableMaster);

        return "redirect:/home";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    @GetMapping("/home/pickup/{id}")
    public String pickUp(@PathVariable("id")Long id,HttpSession session,Model model){

        if(session.getAttribute("userId") == null) {
            return "redirect:/logout";
        }
        Long userId = (Long) session.getAttribute("userId");

        User user = userService.findById(userId);

        TableMaster tableMaster=tableMasterService.findById(id);
        user.getTables().add(tableMaster);

        userService.updateUser(user);
        model.addAttribute("user",user);
        model.addAttribute("unassigned",tableMasterService.getUnassigned(user));
        model.addAttribute("assigned",tableMasterService.getAssigned(user));

        return "redirect:/home";
    }


    @GetMapping("/home/giveup/{id}")
    public String giveUp(@PathVariable("id")Long id,HttpSession session,Model model){

        if(session.getAttribute("userId") == null) {
            return "redirect:/logout";
        }
        Long userId = (Long) session.getAttribute("userId");

        User user = userService.findById(userId);

        TableMaster tableMaster=tableMasterService.findById(id);
        user.getTables().remove(tableMaster);

        userService.updateUser(user);
        model.addAttribute("user",user);
        model.addAttribute("unassigned",tableMasterService.getUnassigned(user));
        model.addAttribute("assigned",tableMasterService.getAssigned(user));

        return "redirect:/home";
    }

}
