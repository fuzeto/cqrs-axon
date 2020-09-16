package br.com.fuzeto.bankaccountcommand.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public class BankAccountRemovedEvent {

    public String id;
}
