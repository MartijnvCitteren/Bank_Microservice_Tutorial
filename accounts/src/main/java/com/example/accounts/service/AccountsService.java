package com.example.accounts.service;

import com.example.accounts.dto.CustomerDto;

public interface AccountsService {

    /**
     * @param customerDto - CustomerDto Object
     */
    void createAccount(CustomerDto customerDto);

    /**
     * @param mobileNumber input
     * @returns Accounts details based on mobile number
     */
    CustomerDto fetchAccountByMobileNumber(String mobileNumber);

    /**
     *
     * @param customerDto
     * @return
     */
    boolean updateAccount(CustomerDto customerDto);

    /**
     *
     * @param mobileNumber
     * @return boolean value
     */
    boolean deleteAccount(String mobileNumber);


}
