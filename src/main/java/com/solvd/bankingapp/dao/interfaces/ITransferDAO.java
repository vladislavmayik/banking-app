package com.solvd.bankingapp.dao.interfaces;

import com.solvd.bankingapp.models.Transfer;

import java.util.List;

public interface ITransferDAO extends IBaseDAO<Transfer> {

    List<Transfer> findByFromAccountId(Long fromAccountId);

    List<Transfer> findByToAccountId(Long toAccountId);

    List<Transfer> findByStatus(String status);
}