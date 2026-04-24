package com.solvd.bankingapp.models;

import com.solvd.bankingapp.util.LocalDateTimeAdapter;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDateTime;

@XmlRootElement(name = "notification")
@XmlAccessorType(XmlAccessType.FIELD)
public class Notification {

    @XmlElement(name = "id")
    private Long id;

    @XmlElement(name = "type")
    private String type;

    @XmlElement(name = "message")
    private String message;

    @XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
    @XmlElement(name = "sentAt")
    private LocalDateTime sentAt;

    @XmlElement(name = "isRead")
    private boolean isRead;

    @XmlElement(name = "customerId")
    private Long customerId;

    public Notification() {}

    public Notification(Long id, String type, String message,
                        LocalDateTime sentAt, Long customerId) {
        this.id = id;
        this.type           = type;
        this.message        = message;
        this.sentAt         = sentAt;
        this.isRead         = false;
        this.customerId     = customerId;
    }

    public Long          getNotificationId()                     { return id; }
    public void          setNotificationId(Long id)        { this.id = id; }
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
