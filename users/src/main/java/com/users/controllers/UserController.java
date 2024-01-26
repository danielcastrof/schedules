package com.users.controllers;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.users.dtos.UserRecordDTO;
import com.users.models.UserModel;
import com.users.services.UserService;

import jakarta.validation.Valid;

@RestController
public class UserController {

    final UserService userService;

    UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/users")
    public ResponseEntity<UserModel> saveUser(@RequestBody @Valid UserRecordDTO dto) {
        var userModel = new UserModel();
        BeanUtils.copyProperties(dto, userModel);

        return ResponseEntity.status(HttpStatus.CREATED).body(userService.saveUser(userModel));
    }

}
