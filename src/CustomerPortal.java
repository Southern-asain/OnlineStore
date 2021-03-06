import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Arrays;

public class CustomerPortal {
    private static Statement stmt,stmt2;
    private static Scanner  sc;
    private static Customer customer;

    public static void main(String[] args) {
        stmt = new DBInterface("database.db").getStatement();
        stmt2 = new DBInterface("datebase.db").getStatement();
        sc = new Scanner(System.in);

        int n = -1;

        System.out.println("#### Welcome to Awemazon #####\n");
        while (n != 3) {
            printMenu();
            n = sc.nextInt();
            sc.nextLine();
            if (n == 1) {
                createUser();
            } else if (n == 2) {
                signIn();
                if(customer != null){
                    portal();
                }
            } else {
                System.out.println("Thank you for shopping with us");
            }
        }
    }

    private static void portal(){
        int n =-1;
        while(n != 4){
            printMenu2();

            n = sc.nextInt();
            sc.nextLine();
            switch(n){
                case 1:
                    changeprofile();
                    break;
                case 2:
                    Order();
                    break;
                case 3:

                    StringBuilder sql = new StringBuilder("SELECt rowid, * FROM Orders WHERE Customer =\""+customer.getUsername()+"\"");

                    String out=" ";
                    try{
                        ResultSet set = stmt.executeQuery(sql.toString());
                        while(set.next()){
                            int invoice = set.getInt(1);
                            String date= set.getString(2);
                            String total = set.getString(5);
                            String items = set.getString(4);
                            double tax = set.getDouble(6);
                            double shipping = set.getDouble(7);
                            items = getItems(items);
                            System.out.println("Invoice"+invoice+" Date:"+ date+"\n\nItems:\n"+items+"\n\nTotal:"+total+"\nTax:"+tax+"\nShipping:"+shipping);

                        }
                    }catch(SQLException e){
                        e.printStackTrace();

                }

                    break;
                case 4:
                    System.out.println("   ");
                    updateCustomer();
                    customer = null;
                    break;
            }

        }

    }


    public static void Order() {
        ArrayList<Item> items = new ArrayList<>();
        System.out.println("Enter search(type exit to exit):");
        String ans = sc.nextLine();
        while(!ans.equalsIgnoreCase("f off")){
            try{
                ResultSet set = stmt.executeQuery("SELECT * FROM Inventory WHERE Name LIKE\"%"+ans+"%\"");
                if(!set.next()) {
                    System.out.println("Not found");
                }else{
                    int i = 1;
                    do{
                        System.out.println(i + "QTY "+ set.getInt(2) + "Name" + set.getString(3)+ "Price" + set.getDouble(7));
                        i++;
                    } while (set.next());
                    System.out.println("Select to buy");
                    int n = sc.nextInt();
                    set = stmt.executeQuery("SELECT * FROM Inventory WHERE Name LIKE \"%"+ans +"%\"");
                    for( int p = 0; p < n; p++ ){
                        set.next();
                    }
                    Item it = new Item(set.getString(3), set.getDouble(5), set.getDouble(6), set.getDouble(7), set.getInt(1));
                    items.add(it);
                    stmt.execute("UPDATE Inventory SET STY ="+ (set.getInt(2)-1)+ "WHERE SKU=" + set.getInt(1));


                    }
                }catch(SQLException e){
                    e.printStackTrace();
            }

            System.out.println("Enter search(type f off to exit):");
            ans = sc.nextLine();
        }

        if(items.size()>0){
            Order o = new Order(customer,items.toArray(new Item[0]), Order.Carrier.UPS);
        }

    }


    private static void addOrderToDB(Order o){
        StringBuilder sql = new StringBuilder("INSERT INTO Orders VALUES(\""+o.getOrderDate()+"\",\""+o.getCustomer().getUsername()+"\",\"");
        Item[] it = o.getItems();
        for(Item i:it){
            sql.append(i.getsku());
            sql.append(",");
        }
        if(it.length > 0)
            sql.delete(sql.length()-1,sql.length());
        sql.append("\",");
        sql.append(o.getTotal()+",");
        sql.append(o.getTax()+",");
        sql.append(o.getShippingCost()+",");
        sql.append("\"");
        Order.Box[] boxes = o.getBoxes();
        for(Order.Box b:boxes){
            sql.append(b.name());
            sql.append(",");
        }
        if(boxes.length > 0)
            sql.delete(sql.length()-1,sql.length());
        sql.append("\",");
        sql.append("\""+o.getCarrier().name()+"\")");
        try{

            stmt.execute(sql.toString());
        }catch(SQLException e){
            System.out.print(e);
        }
    }

    public static String getItems(String items) {
        StringBuilder ans= new StringBuilder();
        try{
            ArrayList<String> itList = new ArrayList<String>(Arrays.asList(items.split(",")));
            ResultSet set = stmt.executeQuery("SELECT * FROM Inventory WHERE SKU IN("+items+")");
            while(set.next()){
                int sku = set.getInt(1);
                int qty = 0;
                while(itList.remove(sku+"")) qty++;
                ans.append("\t QTY:"+qty);
                ans.append("Name");
                ans.append(set.getString(3));
                ans.append("Price");
                ans.append(set.getDouble(7));
                ans.append("\n");
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return ans.toString();

    }


    private static void changeprofile(){
        int n=  -1;
        while(n != 4){
            System.out.print("1. who you is?");System.out.print("2. tell me your password");System.out.print("3.Change location");System.out.print("4. F off");
            n = sc.nextInt();sc.nextLine();


            switch(n){case 1: System.out.print("Name: ");customer.setName(sc.nextLine());break;
                case 2: String pass1 ="";String pass2="";while(!pass1.equals(pass2)){
                System.out.print(" New Password Now : "); pass1 = sc.nextLine();System.out.print("Type it agian: ");pass2 = sc.nextLine();
                if(!pass1.equals(pass2)){
                System.out.println("You fed up!");}
                }
                    customer.setPassword(pass2);
                    break;
                case 3:
                    System.out.println("Enter your house address ;P");
                    customer.setAddress(sc.nextLine());
                    break;
            }


        }
    }

    private static void signIn() {
        String user="";
        boolean loggedIn = false;
        while(true) {
            System.out.print("Enter username: ");
            user =  sc.nextLine();
            try {
                ResultSet set = stmt.executeQuery("SELECT Password FROM Customers WHERE Username=\"" + user + "\"");
                if(set.next()){
                    System.out.print("Enter password:");
                    String pass = sc.nextLine();
                    if(pass.equals(set.getString(1))){
                        loggedIn =true;
                        break;
                    }
                    else{
                        System.out.println("Invalid password");
                    }
                }else{
                    System.out.println("User not found");
                }
            }catch(SQLException e){
                System.out.println(e);
            }
        }
        if(loggedIn){

            setCustomer(user);
            System.out.println("\n "+user+" successfully logged in:");
        }else{
            System.out.print("Login Failed");
        }
    }


    private static void createUser() {
        String usr ="";
        while(true) {
            System.out.print("Enter a username:");
            usr = sc.nextLine();
            try {
                ResultSet set = stmt.executeQuery("SELECT * FROM Customers WHERE Username=\"" + usr + "\"");

                if (set.next())
                    System.out.println("Username already taken! please choose another");
                else
                    break;
            } catch (SQLException e) {
                System.out.println(e);
            }
        }
        System.out.println("HERE");
        System.out.print("Enter password: ");
        String pass  = sc.nextLine();
        System.out.print("Enter name: ");
        String name = sc.nextLine();
        System.out.print("Enter address: ");
        String addr = sc.nextLine();
        String dob ="";
        while(true) {
            System.out.print("Enter date of birth (yyyy\\mm\\dd): ");
            dob = sc.nextLine();
            if(true)
                break;
        }
        LocalDate DOB = LocalDate.parse(dob);
        Customer c = new Customer(usr, pass,name, addr,DOB);
        int dist = c.getDistance();
        try{
            stmt.execute("INSERT INTO Customers VALUES (\""+usr+"\",+\""+pass+"\",\""+name+"\",\""+addr+"\",\""+dob+"\",\"\","+dist+")");
        }catch(SQLException e){
            System.out.println(e);
        }
    }


    private static void printMenu() {
        System.out.println("1. Sign up");
        System.out.println("2. Sign in");
        System.out.println("3. Exit");
    }


    private static void printMenu2(){
        System.out.println("1. Change profile");
        System.out.println("2. Order");
        System.out.println("3. View past history");
        System.out.println("4. F off");

    }



    public static void setCustomer(String username){
        Customer c;
        try{
            ResultSet set = stmt.executeQuery("SELECT * FROM Customers WHERE Username=\""+username+"\"");
            String pass = set.getString(2);
            String name = set.getString(3);
            String address = set.getString(4);
            LocalDate dob = LocalDate.parse(set.getString(5));
            ArrayList<Integer> past = new ArrayList<>();
            if(set.getString(6).length() > 0) {
                String[] pastOrdersString = set.getString(6).split(",");
                for (String s : pastOrdersString) {
                    past.add(Integer.valueOf(s));
                }
            }
            int dist = set.getInt(7);
            customer = new Customer(username,pass,name,address,dob,dist);
        }catch(SQLException e){
            System.out.println(e);
        }

    }

    public Order getOrder(){
        return null;
    }

    public static void updateCustomer() {
        String name = customer.getName();
        String pass = customer.getPassword();
        String address = customer.getAddress();
        int distance = customer.getDistance();

        try{
            stmt.execute("UPDATE Customers Set (Name,Password,Address,Distance)= (\""+name+"\",\""+pass+"\","+distance+")");
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

}