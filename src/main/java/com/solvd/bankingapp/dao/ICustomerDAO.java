package com.solvd.bankingapp.dao;

import com.solvd.bankingapp.models.Customer;

import java.util.Optional;

public interface ICustomerDAO extends IBaseDAO<Customer> {

    Optional<Customer> findByEmail(String email);

    Optional<Customer> findByNationalId(String nationalId);

    Optional<Customer> findByPhone(String phone);
}