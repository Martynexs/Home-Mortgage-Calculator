package sample;

import java.text.DecimalFormat;

public class TableElement {

    private String date;
    private double payment;
    private double credit;
    private double interest;
    private double left;
    DecimalFormat df1 = new DecimalFormat("0.00; 0.00");

    public TableElement(String date1, double payment1, double credit1, double interest1, double left1)
    {
        this.date = date1;
        this.payment = payment1;
        this. credit = credit1;
        this.interest  = interest1;
        this.left = left1;
    }

    TableElement()
    {
        super();
    }

    public String getDate()
    {
        return date;
    }

    public void setDate(String date)
    {
        this.date = date;
    }

    public String getPayment()
    {
        return df1.format(payment) + " €";
    }

    public void  setPayment(double payment)
    {
        this.payment = payment;
    }

    public String getCredit()
    {
        return df1.format(credit) + " €";
    }

    public void setCredit(double credit)
    {
        this.credit = credit;
    }

    public  String getInterest()
    {
        return df1.format(interest) + " €";
    }

    public void setInterest(double interest)
    {
        this.interest = interest;
    }

    public String getLeft()
    {
        return df1.format(left)+" €";
    }

    public void  setLeft(double left)
    {
        this.left = left;
    }

}
