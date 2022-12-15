package entity;

import java.util.Date;

public class Record {
    public enum Type {
        income, payment
    }
    public enum Category {
        medical, education, dining, daily, entertainment, other
    }
    private Date date;
    private double amount;
    private Type type;
    private Category category;

    public Record(Date date,double amount,Type type,Category category){
        this.amount=amount;
        this.category=category;
        this.type=type;
        this.date=date;
    }

    public Category getCategory() {
        return category;
    }

    public Date getDate() {
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
        this.date = date;
    }

    public void setType(Type type) {
        this.type = type;
    }
}
