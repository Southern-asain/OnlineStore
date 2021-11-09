public class Shipment {
    private Box[] boxes;
    private Item[] item;
    private Order order;
    private boolean perishable;
    private Customer customer;
    private Carrier carrier;

    public enum Carrier{
        USPS,UPS,FEDEX
    }

    public enum Box{
        SMALL,MEDIUM,LARGE,SMALL_NARROW,MEDIUM_NARROW,LARGE_NARROW,PERISHABLE
    }

    public Shipment(Box[] boxes, Item[] item, Order order, boolean perishable, Customer customer, Carrier carrier) {
        this.boxes = boxes;
        this.item = item;
        this.order = order;
        this.perishable = perishable;
        this.customer = customer;
        this.carrier = carrier;
    }

    public Box[] getBoxes() {
        return boxes;
    }

    public void setBoxes(Box[] boxes) {
        this.boxes = boxes;
    }

    public Item[] getItem() {
        return item;
    }

    public void setItem(Item[] item) {
        this.item = item;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public boolean isPerishable() {
        return perishable;
    }

    public void setPerishable(boolean perishable) {
        this.perishable = perishable;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Carrier getCarrier() {
        return carrier;
    }

    public void setCarrier(Carrier carrier) {
        this.carrier = carrier;
    }
}
