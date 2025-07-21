package com.hackathon.finclusion.services;

import com.hackathon.finclusion.repos.AccountRepo;
import com.hackathon.finclusion.repos.UserRepo;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("!test")
public class ReposStartup {

    private final AccountRepo accountRepo;
    private final UserRepo userRepo;

    public ReposStartup(
            AccountRepo accountRepo,
            UserRepo userRepo
    ) {
        this.accountRepo = accountRepo;
        this.userRepo = userRepo;
    }

    @PostConstruct
    public void init() {
        System.out.println("Accounts: " + accountRepo.findAll());
        System.out.println("Users: " + userRepo.findAll());
    }

}
