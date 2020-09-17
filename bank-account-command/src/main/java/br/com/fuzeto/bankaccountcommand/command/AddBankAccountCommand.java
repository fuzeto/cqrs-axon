package br.com.fuzeto.bankaccountcommand.command;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Getter
@ToString
@AllArgsConstructor
public class AddBankAccountCommand {

    @TargetAggregateIdentifier
    public String id;
    public String name;
}
