package com.bp.controllers;

import com.bp.dao.UserDao;
import com.bp.model.*;
import com.bp.service.GenerateInvoice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Controller
public class IndexController {
    @Autowired
    UserDao userDao;
    //Security security = new Security();
    //redirect to home page
    @RequestMapping("/")
    public String index(){
        return "home";
    }

    @RequestMapping("/register.htm")
    public String viewReg(){
        return "register";
    }
    @RequestMapping("reg")
    public String regster(@ModelAttribute User user){
        userDao.regUser(user);
        return "register";
    }

    @RequestMapping("/login.htm")
    public String log(){
        return "login";
    }
    @RequestMapping("login")
    public String login(@ModelAttribute User user , HttpServletRequest request){
        User u1 = userDao.checkUser(user);
        if (u1 != null){
            request.getSession().setAttribute("user" , u1);
            return "welcome";
        }
        else{
            return "login";
        }

    }

    @RequestMapping("/chat.htm")
    public String chat(){
        return "chat";
    }

    @RequestMapping("/bill")//good to use ajax
    @ResponseBody
    public String printBill(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        boolean res = GenerateInvoice.printInvoice("GreenMart\n" +
                "#######################################\n" +
                dtf.format(now)+"\n"+
                "WELCOME\n" +
                "#######################################\n" +
                "toy           1*150=150\n" +
                "cock          2*200=400\n");
        if (res){
            return "ok";
        }else {
            return "error";
        }
    }


}
