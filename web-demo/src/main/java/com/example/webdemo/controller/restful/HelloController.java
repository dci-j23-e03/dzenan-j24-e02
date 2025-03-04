package com.example.webdemo.controller.restful;

import com.example.webdemo.domain.User;
import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// @Controller and @ResponseBody can be replaced by @RestController
//@Controller
//@ResponseBody
@RestController
@RequestMapping(value = "/hello")
public class HelloController {

  private List<String> userNames = new ArrayList<>(List.of("Mickey", "Minnie", "Donald", "Goofy"));
  private List<User> users = new ArrayList<>();

  //  @RequestMapping(method = RequestMethod.GET)
  @GetMapping
  public String helloWorld() {
    return "Hello World";
  }

  @GetMapping(value = "/from-spring")
  public String helloFromSpring() {
    return "Hello from Spring MVC application!";
  }

  @GetMapping({"/random-number", "/random-number/{times}"})
  public List<Integer> randomNumber(
      @PathVariable(required = false) Integer times,
      @RequestParam(required = false, defaultValue = "true") boolean multiple)
  {
    if (times == null) {
      times = 1;
    }
    int randomNumber = (int) (Math.random() * 100);
    List<Integer> randomNumberList = new ArrayList<>();
    if (multiple) {
      for (int i = 0; i < times; i++) {
        randomNumberList.add(randomNumber);
      }
    } else {
      randomNumberList.add(randomNumber);
    }

    return randomNumberList;
  }

  @GetMapping("/users/names/{userName}/{times}")
  public List<String> getUserNames(
      @PathVariable String userName,
      @PathVariable(value = "times") Integer numberOfTimes
  ) {
    List<String> userNameMultipleTimes = new ArrayList<>();
    if (userNames.contains(userName)) {
      for (int i = 0; i < numberOfTimes; i++) {
        userNameMultipleTimes.add(userName);
      }
    } else {
      throw new RuntimeException(String.format("User with name %s not found", userName));
    }

    return userNameMultipleTimes;
  }

  @PostMapping("/users/names/{userName}")
  public void createUserName(@PathVariable String userName) {
    userNames.add(userName);
  }

  @GetMapping("/users/names")
  public List<String> getUserNames() {
    return userNames;
  }

  @GetMapping("/users")
  public List<User> getUsers() {
    return users;
  }

  @PostMapping("/users")
  public User createUser(@RequestBody User newUser) {
    users.add(newUser);
    return newUser;
  }

}





