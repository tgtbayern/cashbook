package entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Record {
    public enum Type {
        income, payment
    }
    public enum Category {
        medical, education, dining, daily, entertainment, other
    }
    private java.sql.Date date;
    private double amount;
    private Type type;
    private Category category;

    public Record(java.sql.Date date,double amount,Type type,Category category){
        this.amount=amount;
        this.category=category;
        this.type=type;
        this.date=date;
    }
    public Record(java.sql.Date date,double amount,String type,String category){
        this.amount=amount;
        this.category= Category.valueOf(category);
        this.type= Type.valueOf(type);
        this.date=date;
    }


    public Category getCategory() {
        return category;
    }

    public java.sql.Date getDate() {
        return date;
    }

    public double getAmount() {
        return amount;
    }

    public Type getType() {
        return type;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setDate(Date date) {
        this.date = (java.sql.Date) date;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String toString(){
        String str=("date:"+ date+" amount:"+amount+" category:"+category+" type:"+type);
        return str;
    }
}
