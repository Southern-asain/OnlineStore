import java.time.LocalDate;
import java.util.ArrayList;

public class Customer {

    private String user, password, email, name, address;
    private int Ccard;
    private ArrayList<Integer> pastOrders;
    private LocalDate dob;
    private double distance;

    public Customer(String user, String password, String email, String name, String address, int ccard, ArrayList<Integer> pastOrders, LocalDate dob, double distance) {
        this.user = user;
        this.password = password;
        this.email = email;
        this.name = name;
        this.address = address;
        Ccard = ccard;
        this.pastOrders = pastOrders;
        this.dob = dob;
        this.distance = distance;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getCcard() {
        return Ccard;
    }

    public void setCcard(int ccard) {
        Ccard = ccard;
    }

    public ArrayList<Integer> getPastOrders() {
        return pastOrders;
    }

    public void setPastOrders(ArrayList<Integer> pastOrders) {
        this.pastOrders = pastOrders;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }
}
