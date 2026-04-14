package com.solvd.bankingapp.models;

public class Reward {

    private Long    rewardId;
    private Integer points;
    private String  loyaltyLevel;
    private Long    accountId;

    public Reward() {}

    public Reward(Long rewardId, Integer points, String loyaltyLevel,Long accountId) {
        this.rewardId     = rewardId;
        this.points       = points;
        this.loyaltyLevel = loyaltyLevel;
        this.accountId    = accountId;
    }

    public Long    getRewardId()                      { return rewardId; }
    public void    setRewardId(Long rewardId)            { this.rewardId = rewardId; }
    public Integer getPoints()                        { return points; }
    public void    setPoints(Integer points)               { this.points = points; }
    public String  getLoyaltyLevel()                    { return loyaltyLevel; }
    public void    setLoyaltyLevel(String loyaltyLevel)     { this.loyaltyLevel = loyaltyLevel; }
    public Long    getAccountId()                      { return accountId; }
    public void    setAccountId(Long accountId)            { this.accountId = accountId; }
}