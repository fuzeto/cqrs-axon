package br.com.fuzeto.bankaccountquery.controller;

import br.com.fuzeto.bankaccountquery.model.BankAccount;
import br.com.fuzeto.bankaccountquery.repository.BankAccountRepository;
import br.com.fuzeto.bankaccountquery.service.BankAccountService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/bank-accounts")
public class BankAccountController {

    private BankAccountService bankAccountService;

    @GetMapping("/{id}")
    public CompletableFuture<BankAccount> findAccountById(@PathVariable String id) {
        return bankAccountService.findById(id);
    }
}
