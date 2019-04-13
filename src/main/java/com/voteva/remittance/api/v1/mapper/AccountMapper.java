package com.voteva.remittance.api.v1.mapper;

import com.voteva.remittance.api.v1.dto.AccountDto;
import com.voteva.remittance.entity.Account;

import java.util.ArrayList;
import java.util.List;

public class AccountMapper {

    public static AccountDto from(Account account) {
        if (account == null) return null;

        AccountDto accountDto = new AccountDto();
        accountDto.setId(account.getId());
        accountDto.setOwnerId(account.getOwnerId());
        accountDto.setBalance(account.getBalance().toString());

        return accountDto;
    }

    public static List<AccountDto> from(List<Account> accountList) {
        List<AccountDto> accountDtoList = new ArrayList<>();
        if (accountList == null) return accountDtoList;

        for (Account account : accountList) {
            accountDtoList.add(from(account));
        }

        return accountDtoList;
    }
}
