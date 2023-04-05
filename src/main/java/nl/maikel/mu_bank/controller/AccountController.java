package nl.maikel.mu_bank.controller;

import jakarta.validation.Valid;
import nl.maikel.mu_bank.mapper.AccountDTO;
import nl.maikel.mu_bank.model.Account;
import nl.maikel.mu_bank.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {

    private final AccountService accountService;

    public AccountController(final AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping(value = "/accounts", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Account> createAccount(@RequestBody @Valid final AccountDTO accountDTO) {;
       return new ResponseEntity<>(accountService.createAccount(accountDTO), HttpStatus.CREATED);
    }
}
