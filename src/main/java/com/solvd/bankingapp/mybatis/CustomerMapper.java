package com.solvd.bankingapp.mybatis;

import com.solvd.bankingapp.models.Customer;
import java.util.List;

public interface CustomerMapper {

    Customer findById(Long id);

    List<Customer> findAll();

    void save(Customer customer);

    int update(Customer customer);

    int deleteById(Long id);

    Customer findByEmail(String email);

    Customer findByNationalId(String nationalId);

    Customer findByPhone(String phone);
}