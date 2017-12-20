package com.bp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AjaxController {
    @RequestMapping("/ajax.htm")
    public String ajx(){
        return "ajax";
    }


    @RequestMapping("ajax")
    @ResponseBody
    public String cal(){
        return "<h1>ti working! yeeeeh!!!</h1>";
    }
}
