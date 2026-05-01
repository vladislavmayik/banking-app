package com.solvd.bankingapp.dao.interfaces;

import java.util.List;
import java.util.Optional;

public interface IBaseDAO<T> {

        T save(T entity);

        Optional<T> findById(Long id);

        List<T> findAll();

        boolean update(T entity);

        boolean deleteById(Long id);
}
