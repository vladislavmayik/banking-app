package com.solvd.bankingapp.dao;

import com.solvd.bankingapp.models.Address;

import java.util.List;

public interface IAddressDAO extends IBaseDAO<Address> {

    List<Address> findByCustomerId(Long customerId);

    List<Address> findByCity(String city);

    List<Address> findByCountry(String country);
}