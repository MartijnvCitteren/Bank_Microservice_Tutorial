package com.example.accounts.service;

import com.example.accounts.constants.AccountsConstants;
import com.example.accounts.dto.AccountsDto;
import com.example.accounts.dto.CustomerDto;
import com.example.accounts.entity.Accounts;
import com.example.accounts.entity.Customer;
import com.example.accounts.exception.custom.CustomerAlreadyExists;
import com.example.accounts.exception.custom.ResourceNotFoundException;
import com.example.accounts.mapper.AccountsMapper;
import com.example.accounts.mapper.CustomerMapper;
import com.example.accounts.repository.AccountsRepository;
import com.example.accounts.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class AccountsServiceImp implements AccountsService {
    private final AccountsRepository accountsRepository;
    private final CustomerRepository customerRepository;


    @Override
    public void createAccount(CustomerDto customerDto) {
        Optional<Customer> optionalCustomer = customerRepository.findByMobileNumber(customerDto.mobileNumber());
        if (optionalCustomer.isPresent()) {
            throw new CustomerAlreadyExists("Customer already exists with phonenumber: " + customerDto.mobileNumber());
        }
        Customer customer = CustomerMapper.toEntity(customerDto);
        Customer savedCustomer = customerRepository.save(customer);
        accountsRepository.save(createNewAccount(savedCustomer));
    }

    @Override
    public CustomerDto fetchAccountByMobileNumber(String mobileNumber) {
        Customer customer = findCustomerByMobileNumber(mobileNumber);
        Accounts accounts = findAccountByCustomerId(customer.getCustomerId());
        return CustomerMapper.toDto(customer, AccountsMapper.toDto(accounts));
    }

    @Override
    public boolean updateAccount(CustomerDto customerDto) {
        boolean isUpdated = false;
        AccountsDto accountsDto = customerDto.accountsDto();

        if(accountsDto !=null ) {
            Accounts accounts = accountsRepository.findById(accountsDto.accountNumber()).orElseThrow(
                    () -> new ResourceNotFoundException("Account", "AccountNumber", accountsDto.accountNumber().toString())
            );

            Customer customer = customerRepository.findById(accounts.getCustomerId()).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "CustomerID",
                        accounts.getCustomerId().toString()));

            accountsRepository.save(setAccountDetails(accounts, accountsDto));
            customerRepository.save(setCustomerDetails(customer, customerDto));
            isUpdated = true;
        }
        return  isUpdated;
    }

    @Override
    public boolean deleteAccount(String mobileNumber) {
        Customer customer = findCustomerByMobileNumber(mobileNumber);
        Accounts accounts = findAccountByCustomerId(customer.getCustomerId());
        accountsRepository.delete(accounts);
        customerRepository.delete(customer);
        return true;
    }

    private Accounts createNewAccount(Customer customer) {
        long generatedAccountNumber = 1_000_000_000L + new Random().nextInt(900_000_000);
        Accounts accounts = new Accounts();
        accounts.setAccountNumber(generatedAccountNumber);
        accounts.setAccountType(AccountsConstants.SAVINGS);
        accounts.setBranchAddress(AccountsConstants.ADDRESS);
        accounts.setCustomerId(customer.getCustomerId());
        return accounts;

    }

    private Customer findCustomerByMobileNumber(String mobileNumber) {
        return customerRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber));
    }

    private Accounts findAccountByCustomerId(Long customerId) {
        return accountsRepository.findByCustomerId(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Accounts", "customerId", customerId.toString()));
    }

    private Accounts setAccountDetails(Accounts accounts, AccountsDto accountsDto) {
        accounts.setAccountType(accountsDto.accountType());
        accounts.setBranchAddress(accountsDto.branchAddress());
        return accounts;
    }

    private Customer setCustomerDetails(Customer customer, CustomerDto customerDto) {
        customer.setName(customerDto.name());
        customer.setEmail(customerDto.email());
        customer.setMobileNumber(customerDto.mobileNumber());
        return customer;
    }
}
