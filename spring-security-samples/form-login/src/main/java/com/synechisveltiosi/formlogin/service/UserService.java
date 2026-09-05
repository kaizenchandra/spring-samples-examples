package com.synechisveltiosi.formlogin.service;

import com.synechisveltiosi.formlogin.modal.PasswordChangeRequest;
import jakarta.annotation.security.RolesAllowed;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsPasswordService;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserDetailsService userDetailsService;
    private final UserDetailsPasswordService userDetailsPasswordService;
    private final PasswordEncoder passwordEncoder;

    public String passwordChange(PasswordChangeRequest passwordChangeRequest) {
        UserDetails userWithOldPassword = userDetailsService.loadUserByUsername(passwordChangeRequest.username());
        userDetailsPasswordService.updatePassword(userWithOldPassword, passwordEncoder.encode(passwordChangeRequest.newPassword()));
        String newPassword = userDetailsService.loadUserByUsername(passwordChangeRequest.username()).getPassword();
        if(passwordEncoder.matches(passwordChangeRequest.newPassword(), newPassword)){
            return "password updated successfully";
        }else {
            return "password change failed";
        }
    }

}
