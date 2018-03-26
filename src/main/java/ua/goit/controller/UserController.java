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
public class UserController extends BaseController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String getAllUsers() {
        List<User> userList = this.userService.getAll();

        if (userList.isEmpty()) {
            return ERROR_JSP;
        }

        return USERS_JSP;
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String getUser(@PathVariable("userId") Long userId) {
        if (userId == null) {
            return ERROR_JSP;
        }

        User user = this.userService.getById(userId);

        if (user == null) {
            return ERROR_JSP;
        }

        return USERS_JSP;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String saveUser(@RequestBody User user, UriComponentsBuilder builder) {
        HttpHeaders headers = new HttpHeaders();

        if (user == null) {
            return ERROR_JSP;
        }

        this.userService.save(user);
        return USERS_JSP;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String deleteUser(@PathVariable("userId") Long userId) {
        User user = this.userService.getById(userId);

        if (user == null) {
            return ERROR_JSP;
        }

        this.userService.delete(userId);

        return USERS_JSP;
    }

}
