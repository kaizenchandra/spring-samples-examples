package com.synechisveltiosi.formlogin.controller;

import com.synechisveltiosi.formlogin.modal.AdminView;
import com.synechisveltiosi.formlogin.modal.UserView;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class HomeController {

    @GetMapping
    public String index() {
        return "Hello World";
    }

    @GetMapping("/unsecured")
    public String unsecured() {
        return "Unsecured";
    }

    @GetMapping("/secured")
    public String secured() {
        return "Secured";
    }

    @GetMapping("/view-user")
    @UserView
    public String userView() {
        return "View USER";
    }

    @GetMapping("/view-admin")
    @AdminView
    public String adminvIEW() {
        return "View ADMIN";
    }

    @GetMapping("/sec-user")
    @Secured("ROLE_USER")
    public String user() {
        return "Secured USER";
    }

    @GetMapping("/sec-admin")
    @Secured("ROLE_ADMIN")
    public String admin() {
        return "Secured ADMIN";
    }

    @GetMapping("/role-user")
    @RolesAllowed("ROLE_USER")
    public String allowedRoleUser() {
        return "Allowed Role USER";
    }

    @GetMapping("/role-admin")
    @RolesAllowed("ROLE_ADMIN")
    public String allowedRoleAdmin() {
        return "Allowed Role ADMIN";
    }


    @GetMapping("/auth-user")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String preAuthorizedUser() {
        return "Authorized USER";
    }

    @GetMapping("/auth-admin")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String preAuthorizedAdmin() {
        return "Authorized ADMIN";
    }



}
