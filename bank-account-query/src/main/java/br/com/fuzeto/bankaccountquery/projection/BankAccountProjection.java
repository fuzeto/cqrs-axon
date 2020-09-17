package br.com.fuzeto.bankaccountquery.projection;

import br.com.fuzeto.bankaccountquery.event.BankAccountListedEvent;
import br.com.fuzeto.bankaccountquery.exception.BankAccountNotFoundException;
import br.com.fuzeto.bankaccountquery.model.BankAccount;
import br.com.fuzeto.bankaccountquery.query.FindBankAccountQuery;
import br.com.fuzeto.bankaccountquery.repository.BankAccountRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class BankAccountProjection {

    private final BankAccountRepository repository;

    @EventHandler
    public void on(BankAccountListedEvent event) {
        log.info("A bank account was listed! {}", event.getId());
    }

    @QueryHandler
    public BankAccount handle(FindBankAccountQuery query) {
        log.debug("Handling FindBankAccountQuery query: {}", query);
        return this.repository.findById(query.getAccountId()).orElseThrow(BankAccountNotFoundException::new);
    }
}
