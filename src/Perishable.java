import java.io.Serializable;

public class Perishable extends Item implements Serializable {
    private int daysLeft;
    private boolean donatable;

    public Perishable(String name, double height, double width, double length, double weight, double price, int daysLeft){
        super(name, height, width, length, weight, price);
        donatable =true;
        this.daysLeft = daysLeft;
    }

    @Override
    public String toString() {
        return "Perishable{" +
                "daysLeft=" + daysLeft +
                ", donatable=" + donatable +
                '}';
    }
}