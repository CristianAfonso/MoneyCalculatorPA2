package moneycalculator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;
import moneycalculator.modelo.Currency;
import moneycalculator.modelo.CurrencyList;
import moneycalculator.modelo.ExchangeRate;
import moneycalculator.modelo.Money;

public class MoneyCalculator {
    
    public static void main(String[] args) throws Exception {
        MoneyCalculator moneyCalculator = new MoneyCalculator();
        moneyCalculator.control();
    }   
    
    private String from;
    private String to;
    private double amount;
    private double exchangeRate;
    private Currency currencyFrom;
    private Currency currencyTo;
    private CurrencyList currencies = new CurrencyList();
    private ExchangeRate exchangerate;
    private Money money;
    
    public MoneyCalculator(){}
    
    private void control() throws Exception{
        input();
        process();
        output();
    }
    
    private void input() throws IOException{
        put(currencies);
        
        System.out.println("Introduzca cantidad: ");
        Scanner scanner = new Scanner(System.in);
        amount       = Double.parseDouble(scanner.next());
        System.out.println("Introduzca divisa origen: ");
        from    = scanner.next();
        System.out.println("Introduzca divisa destino: ");
        to      = scanner.next();
        
        currencyFrom = currencies.getCurrency(from);
        money = new Money(currencyFrom, amount);
        currencyTo = currencies.getCurrency(to);
    }
    
    private void process() throws IOException{
        exchangerate = getExchangeRate(money.getCurrency(),currencyTo);
    }
    
    private void output(){
        System.out.println(money.getAmount() + currencyFrom.getSymbol()  + " equivalen a " + money.getAmount()*exchangerate.getRate() + currencyTo.getSymbol() );
    }
    private static ExchangeRate getExchangeRate(Currency from, Currency to) throws IOException{
        URL url = new URL("http://free.currencyconverterapi.com/api/v5/convert?q=" + from.getISO() + "_" + to.getISO() + "&compact=ultra&apiKey=54e783e7998e50874768"); 
        
        URLConnection connection = url.openConnection();
        try (BufferedReader reader =
                    new BufferedReader(new InputStreamReader(connection.getInputStream()))){
            String line     = reader.readLine();
            String line1    = line.substring(line.indexOf(to.getISO())+5, line.indexOf("}"));
            return new ExchangeRate(from, to, Double.parseDouble(line1));
        }
    }
    
    private static void put(CurrencyList currencies) throws IOException{
        URL url = new URL("https://free.currconv.com/api/v7/currencies?ultra&apiKey=54e783e7998e50874768"); 
        URLConnection connection = url.openConnection();
        try (BufferedReader reader =
                    new BufferedReader(new InputStreamReader(connection.getInputStream()))){
            String line = reader.readLine();
            line = line.substring(line.indexOf("{"), line.length());
            String name ;
            String id   ;
            String symbol;
            String last = "";
            Currency currency;
            while(!line.isEmpty()){
                
                name    = line.substring(line.indexOf("currencyName")+15, line.indexOf(",")-1);
                symbol  = line.substring(line.indexOf("currencySymbol")+17, line.indexOf(",",line.indexOf("currencySymbol")+15)-1);
                id      = line.substring(line.indexOf("id")+5, line.indexOf("}")-1);
                currency = new Currency(id, name, symbol);
                currencies.put(id, currency);
                if(last.equals(name)){
                    line = "";
                }
                line = line.substring(line.indexOf("{", line.indexOf(","))+1, line.length());
                last=name;
            }
            
        }
    }
}