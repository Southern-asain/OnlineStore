import java.time.LocalDate;

public class Order {

    private LocalDate orderDate;
    private Customer customer;
    private Item[] items;
    private int invoice;
    private double total, tax, shippingCost;
    private Shipment shipment;

    public Order(LocalDate orderDate, Customer customer, Item[] items, int invoice, double total, double tax, double shippingCost, Shipment shipment) {
        this.orderDate = orderDate;
        this.customer = customer;
        this.items = items;
        this.invoice = invoice;
        this.total = total;
        this.tax = tax;
        this.shippingCost = shippingCost;
        this.shipment = shipment;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Item[] getItems() {
        return items;
    }

    public void setItems(Item[] items) {
        this.items = items;
    }

    public int getInvoice() {
        return invoice;
    }

    public void setInvoice(int invoice) {
        this.invoice = invoice;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getTax() {
        return tax;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }

    public double getShippingCost() {
        return shippingCost;
    }

    public void setShippingCost(double shippingCost) {
        this.shippingCost = shippingCost;
    }

    public Shipment getShipment() {
        return shipment;
    }

    public void setShipment(Shipment shipment) {
        this.shipment = shipment;
    }
}
