package ua.goit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import ua.goit.model.User;
import ua.goit.service.UserService;

import java.util.List;

@Controller
@RequestMapping(value = "/users")
public class UserRestController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/getAll", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String getAllUsers() {
        List<User> userList = this.userService.getAll();

        if (userList.isEmpty()) {
            return "error";
        }

        return "users";
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String getUser(@PathVariable("userId") Long userId) {
        if (userId == null) {
            return "error";
        }

        User user = this.userService.getById(userId);

        if (user == null) {
            return "error";
        }

        return "users";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String saveUser(@RequestBody User user, UriComponentsBuilder builder) {
        HttpHeaders headers = new HttpHeaders();

        if (user == null) {
            return "error";
        }

        this.userService.save(user);
//        headers.setLocation(builder.path("/api/users/{userId}").buildAndExpand(user.getId()).toUri());
        return "users";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String deleteUser(@PathVariable("userId") Long userId) {
        User user = this.userService.getById(userId);

        if (user == null) {
            return "error";
        }

        this.userService.delete(userId);

        return "users";
    }

}
