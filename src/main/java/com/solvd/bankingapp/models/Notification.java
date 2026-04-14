package com.solvd.bankingapp.models;

import java.time.LocalDateTime;

public class Notification {

    private Long          notificationId;
    private String        type;
    private String        message;
    private LocalDateTime sentAt;
    private boolean       isRead;
    private Long          customerId;

    public Notification() {}

    public Notification(Long notificationId, String type, String message,
                        LocalDateTime sentAt, Long customerId) {
        this.notificationId = notificationId;
        this.type           = type;
        this.message        = message;
        this.sentAt         = sentAt;
        this.isRead         = false;
        this.customerId     = customerId;
    }

    public Long          getNotificationId()                     { return notificationId; }
    public void          setNotificationId(Long notificationId)        { this.notificationId = notificationId; }
    public String        getType()                           { return type; }
    public void          setType(String type)                  { this.type = type; }
    public String        getMessage()                        { return message; }
    public void          setMessage(String message)            { this.message = message; }
    public LocalDateTime getSentAt()                         { return sentAt; }
    public void          setSentAt(LocalDateTime sentAt)       { this.sentAt = sentAt; }
    public boolean       isRead()                            { return isRead; }
    public void          setRead(boolean isRead)               { this.isRead = isRead; }
    public Long          getCustomerId()                     { return customerId; }
    public void          setCustomerId(Long customerId)        { this.customerId = customerId; }
}
