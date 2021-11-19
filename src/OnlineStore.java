import java.sql.SQLException;
import java.sql.Statement;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class OnlineStore {
    public static void main(String[] args) {
        DBInterface data = new DBInterface("database.org");
        Statement stmt = data.getStatement();
        Scanner pb = new Scanner(System.in);
        int n = -1;
        while (n != 4) {
            printMainMenu();
            n = pb.nextInt();
            pb.nextLine();
            switch (n) {
                case 1:
                    AddtoCart();
                    break;
                case 2:
                    RemovefromCart();
                    break;
                case 3:
                    ChangeQuant();
                    break;
            }

        }
    }

    public static void printMainMenu(){
        System.out.println("1.I'm gonna get it");
        System.out.println("2.I change my mind");
        System.out.println("3.I got the wrong amount like an idiot");
        System.out.println("4.F off");


    }

    public static void AddtoCart(){
        DBInterface data = new DBInterface("database.org");
        Statement stmt = data.getStatement();
        Scanner pb = new Scanner(System.in);
        System.out.println("Item name:");
        String name = pb.nextLine();
        System.out.println("Item height:");
        double height = pb.nextDouble();pb.nextLine();
        System.out.println("Item width:");
        double width = pb.nextDouble();
        System.out.println("Item length:");
        double length = pb.nextDouble();
        System.out.println("Item price:");
        double price = pb.nextDouble();pb.nextLine();
        System.out.println("SKU:");
        int sku = pb.nextInt();pb.nextLine();
        System.out.println("QTY:");
        int qty = pb.nextInt();pb.nextLine();
        try{
            stmt.execute("INSERT INTO Inventory VALUES ("+sku+","+qty+",\""+name+"\","+height+","+length+","
                    + width+","+price+")");
        }catch(SQLException e){}

    }

    public static void RemovefromCart(){
        DBInterface data = new DBInterface("database.org");
        Statement stmt = data.getStatement();
        Scanner pb = new Scanner(System.in);
        System.out.println("Enter SKU");
        int sku = pb.nextInt();pb.nextLine();
            try{
                stmt.execute("DELETE FROM Inventory WHERE SKU="+sku);
            }catch(SQLException e){System.out.println(e);}

    }


    public static void ChangeQuant(){
        DBInterface data = new DBInterface("database.org");
        Statement stmt = data.getStatement();
        Scanner pb = new Scanner(System.in);
        System.out.println("Enter SKU");
        int sku = pb.nextInt();pb.nextLine();
        System.out.println("Enter new QTY:");
        int qty = pb.nextInt();
        try {
            stmt.execute("UPDATE Inventory SET QTY=" + qty + " WHERE SKU=" + sku);
        }catch(SQLException e) {System.out.println(e);}
        System.out.println("Quantity updated");
        System.out.println("");

    }


}
