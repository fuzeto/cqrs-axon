package br.com.fuzeto.bankaccountquery.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public class BankAccountListedEvent {

    public String id;
}