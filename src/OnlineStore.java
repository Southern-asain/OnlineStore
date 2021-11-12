public class OnlineStore {

    public static void main(String[] args) {

        int n = 1;
        while(n != 4) {
            printMenu();

            switch (n) {
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                default:
                    System.out.println("You don't know how to count!");
            }
        }


    }

        public static void printMenu() {
        /*  1. Print inventory
            2. Modify inventory
            3. Start a purchase
            4. Nuke the store
         */
            System.out.println("1. To look at Inventory");
            System.out.println("2. Change Inventory");
            System.out.println("3. Buy");
            System.out.println("4. NUUUUUUUKE");

        }
}
