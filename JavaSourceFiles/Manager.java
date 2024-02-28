
/**
 * This is the Managers' Portal
 */
import java.util.*;

public class Manager {
    private static Scanner stringsc3 = new Scanner(System.in);// String is entered through different scanner object to
                                                              // avoid
    // mismatch
    private static Scanner sc3 = new Scanner(System.in);
    static long man_uid = 0L;
    static String man_name;
    static String man_mpin;
    static Node head = null, n = null;
    private static int call = 0;
    private static boolean isInSupport = false;

    protected static void menu() {// Manager Available options
        // Manager.man_data();// only invoked once, to create manager for successful run
        // of the program
        while (true) {
            System.out.println("***Manager Menu***");
            System.out.println("1. Create User Account");
            System.out.println("2. Update Balance of User");
            System.out.println("3. Display All Accounts Created in Bank");
            System.out.println("4. Customer's Request Page");
            System.out.println("5. Logout");
            System.out.println("Enter a choice");
            Manager.cases_M(sc3.nextInt());
            if (call == 1) {// Logout Case
                call = 0;
                break;
            }
        }
        System.out.println("Auto Logging Off...");

    }

    private static void cases_M(int choice) {
        switch (choice) {
            case 1:
                Manager.createuser();
                break;
            case 2:
                Manager.update_balance();
                break;
            case 3:
                Manager.display(head);
                break;
            case 4:
                Manager.customer_requests(head);
                break;
            case 5:
                call = 1;
                break;
            default:
                System.out.println("Invalid Choice! Please ReEnter");
                break;
        }
    }

    protected static void man_data() {// This creates the Manager id and mpin & stores it in a variable. As there is
                                      // only one manager account therefore a LinkedList is not required
        if (man_uid == 0L) {
            System.out.println("Create Manager ID");
            man_uid = sc3.nextLong();
            System.out.println("Create Manager Name");
            man_name = stringsc3.nextLine();
            Secrets.generate_mpin();// creates mpin & encrypts the value
            man_mpin = Secrets.hashedpin;// stores the encrypted value
        } else {
            System.out.println("Manager already exists!");
            return;
        }
    }

    protected static void createuser() {// Normal LinkedList for customer account creation
        if (head == null) {
            n = new Node();
            n.next = null;
            head = n;
            System.out.println("Enter your Name");
            n.username = stringsc3.nextLine();
            System.out.println("Creating Account Number....");
            n.accno = RandomIdGenerator.RandomID();// generate no b/n 1,00,000-9,99,999
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
            newnode.accno = RandomIdGenerator.RandomID();// generate no b/n 1,00,000-9,99,999
            // System.out.println("Create a MPIN");
            Secrets.generate_mpin();
            newnode.hash_pin = Secrets.hashedpin;
            Manager.print1(newnode);
            n = newnode;
        }
    }

    private static void update_balance() {// Manager has the authority to modify the balances of user accounts
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

    private static void display(Node k) {
        Node disp = k;
        if (k == null) {
            System.out.println(
                    "No Accounts Present in THALA Bank! Probably First Run\n Create new User Accounts first\n Good Luck!");
            return;
        }
        String str2 = "";
        System.out.printf("%s%8s%s%8s%s%8s%s", "NAME", "", "A/CN", "", "BAL", "", "REQ");
        while (disp != null) {// iterates over the Linked List
            System.out.println();
            str2 = disp.username;// sets str2 as username
            System.out.print(str2);// prints str2
            space_counter.setspaces(str2);// calls the setspaces() to set exact spaces required for formatting
            str2 = String.valueOf(disp.accno);// sets str2 as account number
            System.out.print(str2);// prints str2
            space_counter.setspaces(str2);// calls the setspaces() to set exact spaces required for formatting
            System.out.print(disp.balance);// prints balance & jumps to the next Line!
            str2 = String.valueOf(disp.balance);
            space_counter.setspaces(str2);
            System.out.println(disp.request);
            if (!isInSupport)
                disp = disp.next;
            else
                break;
        }
        System.out.println();
    }

    public static void customer_requests(Node k) {
        System.out.println("Welcome to Customer's Request page!");
        int broker = 0;
        isInSupport = true;
        while (true) {
            System.out.println("***REQUESTS MENU***");
            System.out.println("1. List All Requests");
            System.out.println("2. Address Request");
            System.out.println("3. List Users who attempted MPIN more than 3 times.");
            System.out.println("4. Exit Request page!");
            int reqchoice = sc3.nextInt();
            switch (reqchoice) {
                case 1:
                    Node disp = k;
                    System.out.println("The Customer requested profiles are listed as follows");
                    while (disp != null) {
                        if (disp.request == 1)
                            Manager.display(disp);
                        disp = disp.next;

                    }
                    k = head;
                    break;
                case 2:
                    System.out.println("Enter Account Number of Customer of whom Request will be addressed");
                    while (true) {
                        long customer_accn = sc3.nextLong();
                        if (Customer.findAcc(customer_accn)) {
                            System.out.println("Account Found!");
                            System.out.println("Listing Request of:" + Customer.temp.username);
                            break;
                        } else {
                            System.out.println("Account Not Found! Plaese ReEnter");
                            continue;
                        }
                    }
                    System.out.println("Requesting for changing username to:" + Customer.temp.query);
                    System.out.println("Showing verification ID");
                    System.out.println(Customer.temp.upload_file);// Showing Aadhar for verification
                    while (true) {
                        System.out.println("0. Deny Request");
                        System.out.println("1. Hold Request");
                        System.out.println("2. Accept Request");
                        System.out.println("3. Return to Main Request Menu");
                        int requesting_choice = sc3.nextInt();
                        switch (requesting_choice) {
                            case 0:
                                System.out.println("Enter any message for customer. Leave Blank for none!");
                                String manager_msg = stringsc3.nextLine();
                                Customer.temp.query = manager_msg + "Can't change username to " + Customer.temp.query;
                                Customer.temp.request = 0;// Denied
                                break;
                            case 1:
                                System.out.println("Holding Request to Address Later!");
                                Customer.temp.request = 1;// Hold Request
                                break;
                            case 2:
                                System.out.println("Request Accepted!");
                                Customer.temp.request = 2;// Accept Request
                                Customer.temp.username = Customer.temp.query;
                                break;
                            case 3:
                                System.out.println("Returning to Menu");
                                broker = 1;
                                break;
                        }
                        if (broker == 1) {
                            broker = 0;
                            break;
                        }
                    }
                    break;
                case 3:
                    mpin_validation_reset();
                    break;
                case 4:
                    broker = 1;
                    break;
                default:
                    System.out.println("Wrong Choice! Please ReEnter");
                    break;
            }
            if (broker == 1) {
                broker = 0;
                break;
            }
        }
        isInSupport = false;// reassigns flag
    }

    private static void mpin_validation_reset() {
        System.out.println("Listing Invalid MPIN Attempt Users");
        if (Manager.head != null) {
            Node list = Manager.head;
            System.out.printf("%s%8s%s", "NAME", "", "A/CN");
            System.out.println();
            while (list != null) {
                if (list.number_of_mpin_attempts == 0) {
                    System.out.printf("%s", list.username);
                    space_counter.setspaces(list.username);
                    System.out.println(list.accno);
                }
                list = list.next;

            }
            System.out.println();
        } else {
            System.out.println("No Accounts present in system");
        }
        while (true) {
            System.out.println("Want to Address Request?Y/N");
            char ch = stringsc3.nextLine().charAt(0);
            switch (ch) {
                case 'Y':
                    while (true) {
                        System.out.println("Enter Account Number to Address Request. Enter -9999 to Exit!");
                        long account_number_for_reset = sc3.nextLong();
                        if (account_number_for_reset == -9999) {
                            System.out.println("Exiting Invalidation Request Page!");
                            return;
                        } else if (Customer.findAcc(account_number_for_reset))
                            break;
                        else {
                            System.out.println("Account Not Found!");
                            continue;
                        }
                    }
                    System.out.println("Setting flag to reset attempts....");
                    Customer.temp.number_of_mpin_attempts = 3;// Reseting Attemps
                    System.out.println("MPIN Validation reset! Number of attempts remaining for user"
                            + Customer.temp.username + " is:" + Customer.temp.number_of_mpin_attempts);
                    break;
                case 'N':
                    System.out.println("Exiting MPIN Invalidation Request Page");
                    return;
                default:
                    System.out.println("Wrong Choice!");
                    continue;
            }
        }
    }

}