package br.com.fuzeto.bankaccountquery.query;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FindBankAccountQuery {
    private String accountId;
}