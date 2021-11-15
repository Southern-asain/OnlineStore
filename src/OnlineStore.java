import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class OnlineStore {
    private static ArrayList<Item> inventory;

    public static void main(String[] args) {
        Scanner mon = new Scanner(System.in);
        int n = 1;
        while (n != 0) {
            printMainMenu();
            n = mon.nextInt();
            mon.nextLine();
            switch (n) {
                case 1:
                    break;
            }
        }

    }

    public static void printMainMenu() {
        System.out.println("1. View Items");
        System.out.println("2. Add Items to cart");
        System.out.println("3. View cart");
        System.out.println("4. Sign in");
        System.out.println("5. Order");
        System.out.println("6. View Past orders");
        System.out.println("7. Account");

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