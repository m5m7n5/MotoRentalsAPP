package Model;

public class Delay {
    private int days;
    private int hours;
    private Price price;

    public Delay(int hours, int days){
        this.hours = hours;
        this.days = days;
        this.price = new Price(2*hours + 48*days);
    }
    
    /**
     * Gives the amount of its price
     * @return int, amount of price.
     */
    public int getPrice(){
        return price.getAmount();
    }
}
