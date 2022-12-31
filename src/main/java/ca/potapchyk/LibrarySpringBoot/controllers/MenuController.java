package ca.potapchyk.LibrarySpringBoot.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/main")
public class MenuController {

    @GetMapping()
    public String main() {
        return "/main";
    }
}