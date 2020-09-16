package br.com.fuzeto.bankaccountcommand.aggregate;

import br.com.fuzeto.bankaccountcommand.command.AddBankAccountCommand;
import br.com.fuzeto.bankaccountcommand.command.RemoveBankAccountCommand;
import br.com.fuzeto.bankaccountcommand.command.UpdateBalanceBankAccountCommand;
import br.com.fuzeto.bankaccountcommand.event.BankAccountAddedEvent;
import br.com.fuzeto.bankaccountcommand.event.BankAccountBalanceUpdatedEvent;
import br.com.fuzeto.bankaccountcommand.event.BankAccountRemovedEvent;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateLifecycle;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.util.Assert;

import java.math.BigDecimal;

@Slf4j
@Getter
@Aggregate
@NoArgsConstructor
public class BankAccountAggregate {

    @AggregateIdentifier
    public String id;
    public String name;
    public BigDecimal balance;

    @CommandHandler
    public BankAccountAggregate(AddBankAccountCommand command) {
        log.info("Handling {} command: {}", command.getClass().getSimpleName(), command);

        Assert.hasLength(command.getId(), "Id should not be empty or null.");
        Assert.hasLength(command.getName(), "Name should not be empty or null.");

        AggregateLifecycle.apply(
                new BankAccountAddedEvent(
                        command.getId(), command.getName(), BigDecimal.ZERO
                )
        );

        log.info("Done handling {} command: {}", command.getClass().getSimpleName(), command);
    }

    @CommandHandler
    public void handle(UpdateBalanceBankAccountCommand command) {
        log.info("Handling {} command: {}", command.getClass().getSimpleName(), command);

        Assert.hasLength(command.getBankId(), "Bank Id should not be empty or null.");
        Assert.notNull(command.getBalance(), "Balance should not be empty or null.");

        AggregateLifecycle.apply(
                new BankAccountBalanceUpdatedEvent(
                        command.getBankId(), command.getBalance()
                )
        );

        log.info("Done handling {} command: {}", command.getClass().getSimpleName(), command);
    }

    @CommandHandler
    public void handle(RemoveBankAccountCommand command) {
        log.info("Handling {} command: {}", command.getClass().getSimpleName(), command);

        Assert.hasLength(command.getId(), "Id should not be empty or null.");

        AggregateLifecycle.apply(
                new BankAccountRemovedEvent(command.getId())
        );

        log.info("Done handling {} command: {}", command.getClass().getSimpleName(), command);
    }

    @EventSourcingHandler
    public void on(BankAccountAddedEvent event) {
        log.info("Handling {} event: {}", event.getClass().getSimpleName(), event);

        this.id = event.getId();
        this.name = event.getName();
        this.balance = event.getBalance();

        log.info("Done handling {} event: {}", event.getClass().getSimpleName(), event);
    }

    @EventSourcingHandler
    public void on(BankAccountBalanceUpdatedEvent event) {
        log.info("Handling {} event: {}", event.getClass().getSimpleName(), event);

        this.balance = event.getBalance();

        log.info("Done handling {} event: {}", event.getClass().getSimpleName(), event);
    }

    @EventSourcingHandler
    public void on(BankAccountRemovedEvent event) {
        log.info("Done handling {} event: {}", event.getClass().getSimpleName(), event);
    }
}
