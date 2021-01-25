package moneycalculator.modelo;

import java.time.LocalDate;

public class ExchangeRate {
    private Currency currencyFrom;
    private Currency currencyTo;
    private LocalDate date;
    private double rate;
    
    public ExchangeRate(Currency from, Currency to, double rate){
        this.currencyFrom=from;
        this.currencyTo=to;
        this.date=LocalDate.now();
        this.rate=rate;
    }
    
    public Currency getFrom(){
        return this.currencyFrom;
    }
    public Currency getTo(){
        return this.currencyTo;
    }
    public LocalDate getDate(){
        return this.date;
    }
    public Double getRate(){
        return this.rate;
    }
    
}
