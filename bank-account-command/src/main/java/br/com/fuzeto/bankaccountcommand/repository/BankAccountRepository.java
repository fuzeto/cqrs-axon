package br.com.fuzeto.bankaccountcommand.repository;

import br.com.fuzeto.bankaccountcommand.model.BankAccount;
import org.springframework.data.repository.CrudRepository;

public interface BankAccountRepository extends CrudRepository<BankAccount, String> {
}
