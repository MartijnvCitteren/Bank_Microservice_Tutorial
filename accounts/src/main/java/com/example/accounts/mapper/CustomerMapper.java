package com.example.accounts.mapper;

import com.example.accounts.dto.AccountsDto;
import com.example.accounts.dto.CardsDto;
import com.example.accounts.dto.CustomerDetailsDto;
import com.example.accounts.dto.CustomerDto;
import com.example.accounts.dto.LoansDto;
import com.example.accounts.entity.Customer;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CustomerMapper {
    public static CustomerDto toDto(Customer customer) {
        return CustomerDto.builder()
                .name(customer.getEmail())
                .email(customer.getEmail())
                .mobileNumber(customer.getMobileNumber())
                .build();
    }

    public static CustomerDto toDto(Customer customer, AccountsDto accountsDto) {
        return CustomerDto.builder()
                .name(customer.getName())
                .email(customer.getEmail())
                .mobileNumber(customer.getMobileNumber())
                .accountsDto(accountsDto)
                .build();
    }

    public static Customer toEntity(CustomerDto customerDto) {
        return Customer.builder()
                .name(customerDto.name())
                .email(customerDto.email())
                .mobileNumber(customerDto.mobileNumber())
                .build();
    }

    public static CustomerDetailsDto toCustomerDetailsDto(Customer customer, AccountsDto accountsDto, CardsDto cardsDto, LoansDto loansDto) {
        return CustomerDetailsDto.builder()
                .name(customer.getName())
                .email(customer.getEmail())
                .mobileNumber(customer.getMobileNumber())
                .accountsDto(accountsDto)
                .cardsDto(cardsDto)
                .loansDto(loansDto)
                .build();
    }
}
