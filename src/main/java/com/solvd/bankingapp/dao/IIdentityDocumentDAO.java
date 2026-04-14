package com.solvd.bankingapp.dao;

import com.solvd.bankingapp.models.IdentityDocument;

import java.util.List;
import java.util.Optional;

public interface IIdentityDocumentDAO extends IBaseDAO<IdentityDocument> {

    Optional<IdentityDocument> findByDocumentNumber(String documentNumber);

    List<IdentityDocument> findByCustomerId(Long customerId);
}