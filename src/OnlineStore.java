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
                case 2:
                    AccessCart();
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

    public static void AccessCart(){



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