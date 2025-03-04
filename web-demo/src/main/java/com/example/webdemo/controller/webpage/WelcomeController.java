package com.example.webdemo.controller.webpage;

import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/welcome")
public class WelcomeController {

  @GetMapping
  public String welcome(Model model) {
    model.addAttribute("welcomeText",
        "This is welcome text which is provided to the page dynamically!");
    return "welcome";
  }

  // path needs to be /welcome/everybody
  @GetMapping("/everybody")
  public String welcomeEverybody() {
    return "welcomeEverybody";
  }

  @GetMapping("/multiple")
  public String welcomeMultiple(Model model) {
    Map<String, Object> attributes = new HashMap<>();
    attributes.put("welcomeHeading", "Welcome to our application!");
    attributes.put("welcomeText", "This is some welcome text!");
    model.addAllAttributes(attributes);
//    model.addAttribute("welcomeHeading", "Welcome to our application!");
//    model.addAttribute("welcomeText",
//        "This is welcome text which is provided to the page dynamically!");
    return "welcomeMultiple";
  }
}
