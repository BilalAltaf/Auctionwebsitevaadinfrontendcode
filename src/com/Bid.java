package com;

/**
 * Created by bilal on 10/20/2016.
 */
public class Bid {
    private int userid;
    private String userName;
    private int bidNumber;
    private String comment;
    private String bidDate;
    private float amount;
    public Bid(int userid,int bidNumber, String comment,String bidDate,float amount) {
        this.userid = userid;
        this.bidNumber = bidNumber;
        this.comment = comment;
        this.bidDate = bidDate;
        this.amount=amount;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public int getUserid(){return userid;}
    public int getBidNumber(){return bidNumber;}
    public String getComment(){return comment;}
    public String getBidDate(){return bidDate;}
    public float getAmount(){return amount;}


}
