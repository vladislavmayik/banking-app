package com.solvd.bankingapp.dao;

import com.solvd.bankingapp.models.Loan;

import java.util.List;

public interface ILoanDAO extends IBaseDAO<Loan> {

    List<Loan> findByCustomerId(Long customerId);

    List<Loan> findByStatus(String status);
}