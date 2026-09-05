package com.synechisveltiosi.formlogin.modal;

public record PasswordChangeRequest(String username, String newPassword, String confirmPassword) {}
