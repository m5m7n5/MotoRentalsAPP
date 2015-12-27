package Model;

public class Admonish {
    private String description;
    private Price price;
    
    public Admonish(String description, int price){
        this.description = description;
        this.price = new Price(price);
    }
    
    /**
     * Return the price of the admonish
     * @return price
     */
    public int getPrice(){
        return price.getAmount();
    }
}
