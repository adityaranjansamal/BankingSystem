
/**
 * This is the Managers' Portal
 */
import java.util.*;

public class Manager {
    static Scanner stringsc3 = new Scanner(System.in);// String is entered through different scanner object to avoid
                                                      // mismatch
    static Scanner sc3 = new Scanner(System.in);
    static long man_uid = 0L;
    static String man_mpin;
    static Node head = null, n = null;
    static Random random = new Random();
    static int call = 0;

    public static void menu() {// Manager Available options
        Manager.man_data();// only invoked once, to create manager for successful run of the program
        while (true) {
            System.out.println("***Manager Menu***");
            System.out.println("1. Create User Account");
            System.out.println("2. Update Balance of User");
            System.out.println("3. Logout");
            System.out.println("Enter a choice");
            Manager.cases_M(sc3.nextInt());
            if (call == 1) {// Logout Case
                call = 0;
                break;
            }
        }
        System.out.println("Auto Logging Off...");

    }

    public static void cases_M(int choice) {
        switch (choice) {
            case 1:
                Manager.createuser();
                break;
            case 2:
                Manager.update_balance();
                break;
            case 3:
                call = 1;
                break;
            default:
                System.out.println("Invalid Choice! Please ReEnter");
                break;
        }
    }

    public static void man_data() {// This creates the Manager id and mpin & stores it in a variable. As there is
                                   // only one manager account therefore a LinkedList is not required
        if (man_uid == 0L) {
            System.out.println("Create Manager ID");
            man_uid = sc3.nextLong();
            Secrets.generate_mpin();// creates mpin & encrypts the value
            man_mpin = Secrets.hashedpin;// stores the encrypted value
        } else {
            System.out.println("Manager already exists!");
            return;
        }
    }

    public static void createuser() {// Normal LinkedList for customer account creation
        if (head == null) {
            n = new Node();
            n.next = null;
            head = n;
            System.out.println("Enter your Name");
            n.username = stringsc3.nextLine();
            System.out.println("Creating Account Number....");
            n.accno = random.nextInt(9_00_000) + 1_00_000;// generate no b/n 1,00,000-9,99,999
            System.out.println("Create a MPIN");
            Secrets.generate_mpin();
            n.hash_pin = Secrets.hashedpin;
            Manager.print1(n);
        } else {
            Node newnode = new Node();
            newnode.next = null;
            n.next = newnode;
            System.out.println("Enter your Name");
            newnode.username = stringsc3.nextLine();
            System.out.println("Creating Account Number....");
            newnode.accno = random.nextInt(9_00_000) + 1_00_000;// generate no b/n 1,00,000-9,99,999
            System.out.println("Create a MPIN");
            Secrets.generate_mpin();
            newnode.hash_pin = Secrets.hashedpin;
            Manager.print1(newnode);
            n = newnode;
        }
    }

    public static void update_balance() {// Manager has the authority to modify the balances of user accounts
        System.out.println("Enter Ac/No");
        long acc = sc3.nextLong();
        boolean isPresent = Customer.findAcc(acc);// searches if the account is present or not
        if (isPresent) {
            System.out.println("Update Balance");
            Customer.temp.balance = sc3.nextDouble();
            System.out.println("Current Balance of User " + Customer.temp.username + "is:" + Customer.temp.balance);// prints
                                                                                                                    // the
                                                                                                                    // customers
                                                                                                                    // balance
                                                                                                                    // as
                                                                                                                    // a
                                                                                                                    // debug
                                                                                                                    // check
        } else {
            System.out.println("User not Found!");
            return;
        }

    }

    public static void print1(Node k) {
        System.out.println("The Account Created As/is");
        System.out.println("Name:" + k.username);
        System.out.println("Account Number:" + k.accno);
        System.out.println("Balance:" + k.balance);
    }

}