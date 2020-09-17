package br.com.fuzeto.bankaccountquery.components;

import br.com.fuzeto.bankaccountquery.event.BankAccountAddedEvent;
import br.com.fuzeto.bankaccountquery.event.BankAccountBalanceUpdatedEvent;
import br.com.fuzeto.bankaccountquery.event.BankAccountRemovedEvent;
import br.com.fuzeto.bankaccountquery.exception.BankAccountNotFoundException;
import br.com.fuzeto.bankaccountquery.model.BankAccount;
import br.com.fuzeto.bankaccountquery.repository.BankAccountRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class BankAccountEventProcessor {

    private final BankAccountRepository repository;

    @EventHandler
    public void on(BankAccountAddedEvent event) {
        BankAccount bankAccount = repository.save(
                new BankAccount(event.getId(), event.getName(), event.getBalance())
        );

        log.info("A bank account was added! {}", bankAccount);
    }

    @EventHandler
    public void on(BankAccountBalanceUpdatedEvent event) {
        BankAccount bank = repository.findById(event.getBankId()).orElseThrow(BankAccountNotFoundException::new);
        bank.setBalance(event.getBalance());
        repository.save(bank);

        log.info("A bank account balance was updated! {}", bank);
    }

    @EventHandler
    public void on(BankAccountRemovedEvent event) {
        repository.deleteById(event.getId());

        log.info("A bank account was removed! {}", event.getId());
    }
}
