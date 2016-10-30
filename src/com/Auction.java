package com;

/**
 * Created by bilal on 10/15/2016.
 */
public class Auction {
    private int id;
    private String title;
    private String itemdescription;
    private String winner;
    private float minprice;
    private float topbid;
    private float topbidNo;
    private String deadline;
    private String createddate;
    private String status;
    private int category;
    private String categoryname;
    private String Owner;
    private int Owner_id;
    public Auction(int id,String title,String itemdescription, float minprice,float topbid,float topbidNo,String deadline,String createddate,String winner,int category)
    {
        this.id=id;
        this.title=title;
        this.itemdescription=itemdescription;
        this.minprice=minprice;
        this.topbid=topbid;
        this.topbidNo=topbidNo;
        this.deadline=deadline;
        this.createddate=createddate;
        this.winner=winner;
        this.category=category;
    }
    public void setOwner(String Owner) {
       this.Owner=Owner;
    }
    public String getOwner() { return Owner; }

    public void setOwner_id(int owner_id) {
        Owner_id = owner_id;
    }

    public int getOwner_id() {
        return Owner_id;
    }

    public void setStatus(int s)
    {
        if(s==1)
        {
            this.status="Active";
        }
        else if(s==2)
        {
            this.status="Resolved";
        }
        else if(s==3)
        {
            this.status="Banned";
        }
    }
    public void setCategoryName(int c)
    {
        if(c==1)
        {
            this.categoryname="Mobiles & Tablets";
        }
        else if(c==2)
        {
            this.categoryname="Electronics & Computers";
        }
        else if(c==3)
        {
            this.categoryname="Vehicles";
        }
        if(c==4)
        {
            this.categoryname="Home & Furniture";
        }
        else if(c==5)
        {
            this.categoryname="Animals";
        }
        else if(c==6)
        {
            this.categoryname="Books, Sports & Hobbies";
        }
        if(c==7)
        {
            this.categoryname="Fashion & Beauty";
        }
        else if(c==8)
        {
            this.categoryname="Kids & Baby Products";
        }
        else if(c==9)
        {
            this.categoryname="Services";
        }
        if(c==10)
        {
            this.categoryname="Jobs";
        }
        else if(c==11)
        {
            this.categoryname="Real Estate";
        }
        else if(c==12)
        {
            this.categoryname="Other";
        }
    }

    public int getId() { return id; }
    public String getStatus() { return status; }
    public String getTitle() { return title; }
    public String getDescription() { return itemdescription; }
    public float getminPrice() { return minprice; }
    public float gettopBid() { return topbid; }
    public float gettopBidNo() { return topbidNo; }
    public String getDeadline() { return deadline; }
    public String getcreatedDate() { return createddate; }
    public String getWinner() { return winner; }
    public int getCategoryNO() { return category; }
    public String getCategoryName() { return categoryname; }


}
