package com.solvd.bankingapp.dao.interfaces;

import com.solvd.bankingapp.models.Notification;

import java.util.List;

public interface INotificationDAO extends IBaseDAO<Notification> {

    List<Notification> findByCustomerId(Long customerId);

    List<Notification> findUnreadByCustomerId(Long customerId);
}