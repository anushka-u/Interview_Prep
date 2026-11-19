package PracticeProblem.medium.ObserverPattern;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Implement interface PriceObserver with onPrice(String symbol, double price)
interface PriceObserver{
    void onPrice(String symbol, double price);
}
// Implement class PriceDisplay: keeps the latest price and reports "AAPL: 152.50",
// or "NONE" before any price arrives
class PriceDisplay implements PriceObserver{

    private  String symbol;
    private  double price;

    private boolean hasPrice = false;

    @Override
    public void onPrice(String symbol, double price){
        this.symbol = symbol;
        this.price = price;
        this.hasPrice = true;
    }

    public String report(){
        if(!hasPrice){
            return "NONE";
        }

        return String.format("%s: %.2f", symbol, price);
    }

}

// Implement class PriceAlert(limit): every time a price at or above its limit arrives it records
// "AAPL crossed 150.00 at 152.50"; lines() returns everything recorded so far, oldest first

class PriceAlert implements PriceObserver{
    private double limit;

    public PriceAlert(double limit){
        this.limit = limit;
    }

    private List<String> lines = new ArrayList<>();


    @Override
    public void onPrice(String symbol, double price){
        if(price>=limit){
            lines.add(String.format(
                    "%s crossed %.2f at %.2f", symbol, limit, price
            ));
        }
    }

    public String[] lines(){
        return lines.toArray(new String[0]);
    }
}

class StockTicker {
    // Subject: knows its symbol and pushes every price to the registered observers.
    private final String symbol;
    private final List<PriceObserver> observers = new ArrayList<>();

    public StockTicker(String symbol) {
        this.symbol = symbol;
    }

    public boolean addObserver(PriceObserver observer) {
        // TODO: Add the observer once, in registration order. Return false for null or a duplicate.
        if(observer == null){
            return false;
        }
        if(!observers.contains(observer)){
            return observers.add(observer);
        }
        return false;
    }

    public boolean removeObserver(PriceObserver observer) {
        // TODO: Remove that same observer object. Return whether it was registered.
        if(observers.contains(observer)){
            return observers.remove(observer);
        }
        return false;
    }

    public int setPrice(double price) {
        // TODO: Call onPrice(symbol, price) on every registered observer in order and return how many were notified.
        // Do not keep prices here; each observer keeps what it needs.
        int count =0;
        for(PriceObserver obs : observers){
            obs.onPrice(symbol,price);
            count++;
        }
        return count;
    }

    public int observerCount() {
        // TODO: Return how many observers are registered right now.
        return observers.size();
    }
}

// Provided judge-facing facade. Do not modify.
class StockTickerFacade {
    private final StockTicker ticker;
    private PriceDisplay display = null;
    private final Map<String, PriceAlert> alertsByName = new HashMap<>();

    public StockTickerFacade(String symbol) {
        ticker = new StockTicker(symbol);
    }

    public boolean addDisplay() {
        if (display != null) {
            return false;
        }
        display = new PriceDisplay();
        return ticker.addObserver(display);
    }

    public boolean addAlert(String name, double limit) {
        if (alertsByName.containsKey(name)) {
            return false;
        }
        PriceAlert alert = new PriceAlert(limit);
        alertsByName.put(name, alert);
        return ticker.addObserver(alert);
    }

    public boolean removeAlert(String name) {
        PriceAlert alert = alertsByName.remove(name);
        return alert != null && ticker.removeObserver(alert);
    }

    public int setPrice(double price) {
        return ticker.setPrice(price);
    }

    public String display() {
        return display == null ? "NONE" : display.report();
    }

    public String[] alertLines(String name) {
        PriceAlert alert = alertsByName.get(name);
        return alert == null ? new String[0] : alert.lines();
    }

    public int observerCount() {
        return ticker.observerCount();
    }
}
