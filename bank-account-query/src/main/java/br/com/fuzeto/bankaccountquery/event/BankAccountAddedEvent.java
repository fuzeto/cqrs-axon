package br.com.fuzeto.bankaccountquery.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@ToString
@AllArgsConstructor
public class BankAccountAddedEvent {

    public String id;
    public String name;
    public BigDecimal balance;
}
