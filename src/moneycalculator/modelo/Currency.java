package moneycalculator.modelo;

public class Currency {
    private String ISO;
    private String name;
    private String symbol;
    
    public Currency(String ISO, String name, String symbol){
        this.ISO        = ISO;
        this.name       = name;
        this.symbol     = symbol;
    }
    
    public String getISO(){
        return this.ISO;
    }
    public String getName(){
        return this.name;
    }
    public String getSymbol(){
        return this.symbol;
    }
}
