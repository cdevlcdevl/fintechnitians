package com.hackathon.finclusion.controllers;

import java.util.List;
import com.hackathon.finclusion.api.UsersApi;
import com.hackathon.finclusion.model.UserDto;
import com.hackathon.finclusion.repos.UserRepo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

@Controller
public class UsersController implements UsersApi {

    private final UserRepo userRepo;

    public UsersController(
            UserRepo userRepo
    ) {
        this.userRepo = userRepo;
    }

    public ResponseEntity<List<UserDto>> getAllUsers() {
        return new ResponseEntity<>(
                userRepo.findAll().stream().map(
                        user -> new UserDto(
                                user.name(),
                                user.email()
                        )
                )
                .toList(),
                HttpStatus.OK
        );
    }
}
