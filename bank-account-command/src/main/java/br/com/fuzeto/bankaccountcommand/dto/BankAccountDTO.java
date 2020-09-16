package br.com.fuzeto.bankaccountcommand.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BankAccountDTO {

    private String name;
    private BigDecimal balance;
}
