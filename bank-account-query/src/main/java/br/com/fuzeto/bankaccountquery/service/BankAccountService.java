package br.com.fuzeto.bankaccountquery.service;

import br.com.fuzeto.bankaccountquery.model.BankAccount;
import br.com.fuzeto.bankaccountquery.query.FindBankAccountQuery;
import lombok.AllArgsConstructor;
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
