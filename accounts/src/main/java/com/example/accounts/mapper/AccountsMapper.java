package com.example.accounts.mapper;

import com.example.accounts.dto.AccountsDto;
import com.example.accounts.entity.Accounts;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AccountsMapper {

    public static AccountsDto toDto(Accounts accounts) {
        return AccountsDto.builder()
                .accountNumber(accounts.getAccountNumber())
                .accountType(accounts.getAccountType())
                .branchAddress(accounts.getBranchAddress())
                .build();
    }

    public static Accounts toEntity(AccountsDto accountsDto) {
        return Accounts.builder()
                .accountNumber(accountsDto.accountNumber())
                .accountType(accountsDto.accountType())
                .branchAddress(accountsDto.branchAddress())
                .build();
    }
}
