package com.example.webdemo.controller.webpage;

import com.example.webdemo.domain.User;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class UserController {

  @GetMapping
  public String getUsers(Model model) {
    User user1 = new User("Mikey", "Mouse");
    User user2 = new User("Minnie", "Mouse");
    model.addAttribute("users", List.of(user1, user2));
    return "users";
  }
}
