package ua.goit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import ua.goit.model.Role;
import ua.goit.service.RoleService;

import java.util.List;

@Controller
@RequestMapping(value = "/roles")
public class RoleController extends BaseController {

        private RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String getAllRoles() {
        List<Role> roleList = this.roleService.getAll();

        if (roleList.isEmpty()) {
            return ERROR_JSP;
        }

        return ROLES_JSP;
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String getRole(@PathVariable("roleId") Long roleId) {
        if (roleId == null) {
            return ERROR_JSP;
        }

        Role role = this.roleService.getById(roleId);

        if (role == null) {
            return ERROR_JSP;
        }

        return ROLES_JSP;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String saveRole(@RequestBody Role role, UriComponentsBuilder builder) {
        HttpHeaders headers = new HttpHeaders();

        if (role == null) {
            return ERROR_JSP;
        }

        this.roleService.save(role);
        return ROLES_JSP;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String deleteRole(@PathVariable("roleId") Long roleId) {
        Role role = this.roleService.getById(roleId);

        if (role == null) {
            return ERROR_JSP;
        }

        this.roleService.delete(roleId);

        return ROLES_JSP;
    }

}
