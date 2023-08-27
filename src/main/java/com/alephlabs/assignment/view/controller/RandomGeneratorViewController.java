package com.alephlabs.assignment.view.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class RandomGeneratorViewController {
    @RequestMapping("/dashboard")
    public String viewGenerator(){
        return "view_generator";
    }
}
