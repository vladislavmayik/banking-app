package com.solvd.bankingapp.models;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "reward")
@XmlAccessorType(XmlAccessType.FIELD)
public class Reward {


    @XmlElement(name = "id")
    private Long id;

    @XmlElement(name = "points")
    private Integer points;

    @XmlElement(name = "loyaltyLevel")
    private String loyaltyLevel;

    @XmlElement(name = "accountId")
    private Long accountId;
    public Reward() {}

    public Reward(Long id, Integer points, String loyaltyLevel,Long accountId) {
        this.id     = id;
        this.points       = points;
        this.loyaltyLevel = loyaltyLevel;
        this.accountId    = accountId;
    }

    public Long    getRewardId()                      { return id; }
    public void    setRewardId(Long id)            { this.id = id; }
    public Integer getPoints()                        { return points; }
    public void    setPoints(Integer points)               { this.points = points; }
    public String  getLoyaltyLevel()                    { return loyaltyLevel; }
    public void    setLoyaltyLevel(String loyaltyLevel)     { this.loyaltyLevel = loyaltyLevel; }
    public Long    getAccountId()                      { return accountId; }
    public void    setAccountId(Long accountId)            { this.accountId = accountId; }
}