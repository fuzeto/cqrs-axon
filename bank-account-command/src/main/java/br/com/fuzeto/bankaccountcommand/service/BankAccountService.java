package br.com.fuzeto.bankaccountcommand.service;

import br.com.fuzeto.bankaccountcommand.model.BankAccount;
import br.com.fuzeto.bankaccountcommand.query.FindBankAccountQuery;
import lombok.AllArgsConstructor;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@AllArgsConstructor
public class BankAccountService {

    private final QueryGateway queryGateway;

    public CompletableFuture<BankAccount> findById(String id) {
        return queryGateway.query(
                new FindBankAccountQuery(id), ResponseTypes.instanceOf(BankAccount.class)
        );
    }
}
