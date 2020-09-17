package br.com.fuzeto.bankaccountcommand.query;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FindBankAccountQuery {
    private String accountId;
}