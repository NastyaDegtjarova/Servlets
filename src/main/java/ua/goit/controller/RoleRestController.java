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
public class RoleRestController {

    @Autowired
    private RoleService roleService;

    @RequestMapping(value = "/getAll", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String getAllRoles() {
        List<Role> roleList = this.roleService.getAll();

        if (roleList.isEmpty()) {
            return "error";
        }

        return "roles";
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String getRole(@PathVariable("roleId") Long roleId) {
        if (roleId == null) {
            return "error";
        }

        Role role = this.roleService.getById(roleId);

        if (role == null) {
            return "error";
        }

        return "roles";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String saveRole(@RequestBody Role role, UriComponentsBuilder builder) {
        HttpHeaders headers = new HttpHeaders();

        if (role == null) {
            return "error";
        }

        this.roleService.save(role);
//        headers.setLocation(builder.path("/api/roles/{roleId}").buildAndExpand(role.getId()).toUri());
        return "roles";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String deleteRole(@PathVariable("roleId") Long roleId) {
        Role role = this.roleService.getById(roleId);

        if (role == null) {
            return "error";
        }

        this.roleService.delete(roleId);

        return "roles";
    }

}
