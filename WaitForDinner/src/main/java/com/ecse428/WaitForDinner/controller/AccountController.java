package com.ecse428.WaitForDinner.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecse428.WaitForDinner.service.AccountService;
import com.ecse428.WaitForDinner.service.AuthService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/account")
public class AccountController {
    private final AccountService accountService;
    private final AuthService authService;

    public AccountController(AccountService accountService, AuthService authService) {
        this.accountService = accountService;
        this.authService = authService;
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteAccount(HttpSession session) {
        int userID = authService.requireLoggedInUserId(session);
        accountService.deleteAccount(userID);
        return ResponseEntity.noContent().build();
    }
}
