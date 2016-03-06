package com.qtt.jinrong.bean.loan;

/**
 * Created by yanxin on 16/2/24.
 */
public class LoanModel {

    private String id;

    private String productId;

    private String thumpImg;

    /** 名称*/
    private String title;

    private String ownedCompany;

    /** 总利息*/
    private int rate;

    /** 月供*/
    private int money;

    private float score;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getThumpImg() {
        return thumpImg;
    }

    public void setThumpImg(String thumpImg) {
        this.thumpImg = thumpImg;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOwnedCompany() {
        return ownedCompany;
    }

    public void setOwnedCompany(String ownedCompany) {
        this.ownedCompany = ownedCompany;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }
}
