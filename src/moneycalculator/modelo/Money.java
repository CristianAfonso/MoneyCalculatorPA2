package moneycalculator.modelo;

public class Money {
    private Currency currency;
    private double amount;
    
    public Money(Currency currency, double amount){
        this.currency   =   currency;
        this.amount     =   amount;
    }
    
    public Currency getCurrency(){
        return this.currency;
    }
    
    public double getAmount(){
        return this.amount;
    }
}
