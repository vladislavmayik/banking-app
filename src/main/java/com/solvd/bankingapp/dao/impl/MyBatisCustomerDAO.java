package com.solvd.bankingapp.dao.impl;

import com.solvd.bankingapp.dao.interfaces.ICustomerDAO;
import com.solvd.bankingapp.models.Customer;
import com.solvd.bankingapp.util.ConnectionFactory;

import java.util.List;
import java.util.Optional;

public class MyBatisCustomerDAO implements ICustomerDAO {

    @Override
    public Customer save(Customer customer) {
        ConnectionFactory.getCustomerMapper().save(customer);
        return customer;
    }

    @Override
    public Optional<Customer> findById(Long id) {
        return Optional.ofNullable(ConnectionFactory.getCustomerMapper().findById(id));
    }

    @Override
    public List<Customer> findAll() {
        return ConnectionFactory.getCustomerMapper().findAll();
    }

    @Override
    public boolean update(Customer customer) {
        return ConnectionFactory.getCustomerMapper().update(customer) > 0;
    }

    @Override
    public boolean deleteById(Long id) {
        return ConnectionFactory.getCustomerMapper().deleteById(id) > 0;
    }

    @Override
    public Optional<Customer> findByEmail(String email) {
        return Optional.ofNullable(ConnectionFactory.getCustomerMapper().findByEmail(email));
    }

    @Override
    public Optional<Customer> findByNationalId(String nationalId) {
        return Optional.ofNullable(ConnectionFactory.getCustomerMapper().findByNationalId(nationalId));
    }

    @Override
    public Optional<Customer> findByPhone(String phone) {
        return Optional.ofNullable(ConnectionFactory.getCustomerMapper().findByPhone(phone));
    }
}