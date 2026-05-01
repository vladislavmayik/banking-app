package com.solvd.bankingapp.dao.interfaces;

import com.solvd.bankingapp.models.LoanPayment;

import java.util.List;

public interface ILoanPaymentDAO extends IBaseDAO<LoanPayment> {

    List<LoanPayment> findByLoanId(Long loanId);

    List<LoanPayment> findByStatus(String status);
}