package br.com.fuzeto.bankaccountquery.repository;

import br.com.fuzeto.bankaccountquery.model.BankAccount;
import org.springframework.data.repository.CrudRepository;

public interface BankAccountRepository extends CrudRepository<BankAccount, String> {
}
