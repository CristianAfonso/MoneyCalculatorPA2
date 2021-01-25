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
    
    private Currency getFrom(){
        return this.currencyFrom;
    }
    private Currency getTo(){
        return this.currencyTo;
    }
    private LocalDate getDate(){
        return this.date;
    }
    private Double getRate(){
        return this.rate;
    }
    
}
