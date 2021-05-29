package sample;

import java.time.LocalDate;
import java.time.Period;

public class Calculator {

    static double loanSum;
    static  double paymentSum;
    static LocalDate start;
    static LocalDate end;
    static double yearPercent;
    static double percent;

    static int months;
    static double[] payments;
    static double[] credit;
    static double[] interest;
    static double[] leftToPay;

    //Skaiciuojami mokejimai pagal Anuiteto grafika
    static void anuinity(LocalDate d1, LocalDate d2, double lsum, double prcnt)
    {
        start = d1;
        end = d2;
        months = Period.between(start, end).getYears()*12 + Period.between(start, end).getMonths();
        payments = new double[months];
        credit = new double[months];
        interest = new double[months];
        leftToPay = new double[months];

        paymentSum = 0;
        loanSum = lsum;
        yearPercent = prcnt;
        percent = prcnt/12.0/100.0;
        double monthlyPayment = (percent*lsum)/(1-Math.pow(1+percent, -months));

        double left = lsum;
        for(int i=0; i<months; i++)
        {
            payments[i] = monthlyPayment;
            paymentSum += payments[i];
            interest[i] = percent*left;
            credit[i] = payments[i] - interest[i];
            left -= credit[i];
            leftToPay[i] = left;
        }

    }

    //Skaiciuojami mokejimai pagal linijini grafika
    static void liniar(LocalDate d1, LocalDate d2, double lsum, double prcnt)
    {
        start = d1;
        end = d2;
        months = Period.between(start, end).getYears()*12 + Period.between(start, end).getMonths();
        payments = new double[months];
        credit = new double[months];
        interest = new double[months];
        leftToPay = new double[months];

        loanSum = lsum;
        yearPercent = prcnt;
        percent = prcnt/12.0/100.0;
        double monthlyPayment = lsum/months;

        double left = lsum;
        for(int i=0; i<months; i++)
        {
            interest[i] = percent*left;
            credit[i] = monthlyPayment;
            payments[i] = credit[i]+interest[i];
            paymentSum += payments[i];
            left -= credit[i];
            leftToPay[i] = left;
        }
    }

}
