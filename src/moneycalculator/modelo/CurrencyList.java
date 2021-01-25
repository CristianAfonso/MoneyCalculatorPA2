package moneycalculator.modelo;

import java.util.HashMap;

public class CurrencyList {
    private HashMap<String, Currency> currencies = new HashMap<>();
    
    public CurrencyList(){}
    public void put(String id, Currency currency) {
        currencies.put(id, currency);
    }
    
    public boolean contains(Currency currency){
        return currencies.containsKey(currency.getName());
    }
    
    public Currency getCurrency(String key){
        return currencies.get(key.toUpperCase());
    }
}
