package com.zavialov.secureMethods.controller;


import com.zavialov.secureMethods.exeption.EmptyRequestExeption;
import jdk.jshell.spi.ExecutionControl;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;

@RestController
public class Controller {

    @Secured({"ROLE_READ"})
    @GetMapping("/read")
    public String read() {
        return "read mode";
    }

    @RolesAllowed({"ROLE_WRITE"})
    @GetMapping("/write")
    public String write() {
        return "write mode";
    }

    @PreAuthorize("hasAnyRole('WRITE', 'DELETE')")
    @GetMapping("/redactor")
    public String redactor() {
        return "redactor mode";
    }

    @GetMapping("/data")
    public String getData(@RequestParam(name = "username", required = false) String username) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String login = ((UserDetails)principal).getUsername();
        if (login.equals(username)) {
            return "user data";
        } else {
            throw new EmptyRequestExeption("Access denied");
        }
    }
}
