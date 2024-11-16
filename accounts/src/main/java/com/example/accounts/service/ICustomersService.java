package com.example.accounts.service;

import com.example.accounts.dto.CustomerDetailsDto;

public interface ICustomersService {
    /**
     * Fetches the customer details based on the provided mobile number.
     *
     * @param mobileNumber the mobile number of the customer, should be a 10-digit number
     * @return CustomerDetailsDto containing the details of the customer
     */
    CustomerDetailsDto fetchCustomerDetails(String mobileNumber);
}
