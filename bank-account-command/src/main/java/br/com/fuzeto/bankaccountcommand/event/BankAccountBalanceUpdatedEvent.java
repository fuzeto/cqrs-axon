package br.com.fuzeto.bankaccountcommand.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@ToString
@AllArgsConstructor
public class BankAccountBalanceUpdatedEvent {

    public String bankId;
    public BigDecimal balance;
}
