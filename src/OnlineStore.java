import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Scanner;

public class OnlineStore {
    private static ArrayList<Item> inventory;
    private static ArrayList<Order> Cart;

    public static void main(String[] args) {
        Scanner mon = new Scanner(System.in);
        int n = 1;
        while (n != 0) {
            printMainMenu();
            n = mon.nextInt();
            mon.nextLine();
            switch (n) {
                case 1:
                    loadInventory();
                    break;
                case 2:
                    Cart();
                    break;
                case 3:
                    break;
                case 4:
                    break;
            }
        }

    }

    public static void printMainMenu() {
        System.out.println("1. View Items");
        System.out.println("2. Cart");
        System.out.println("3. Account");
        System.out.println("4. ....");


    }

    public void Cart(){
        try {
            FileInputStream fis = new FileInputStream("file");
            ObjectInputStream ois = new ObjectInputStream(fis);
            Cart = (ArrayList<Order>) ois.readObject();
            System.out.println("Cart: " + Cart.size() + " Items loaded");
            ois.close();
        } catch (Exception e) {
            System.out.println("Nothing in cart");
            Cart = new ArrayList<>();
        }

    }

    public static void loadInventory() {
        try {
            FileInputStream fis = new FileInputStream("file");
            ObjectInputStream ois = new ObjectInputStream(fis);
            inventory = (ArrayList<Item>) ois.readObject();
            System.out.println("Inventory: " + inventory.size() + " Items loaded");
            ois.close();
        } catch (Exception e) {
            System.out.println("No Inventory Found! New Inventory Created");
            inventory = new ArrayList<>();
        }
    }
}