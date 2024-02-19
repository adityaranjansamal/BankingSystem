import java.util.*;

/*
 * This is the Customers' Portal
 */
public class Customer {
    static Node temp;
    static Scanner sc4 = new Scanner(System.in);
    static int call_c = 0;
    static Scanner stringsc4 = new Scanner(System.in);// String is entered through different scanner object to avoid
                                                      // mismatch

    public static void menu2() {// Customer Available options
        while (true) {
            System.out.println("***CUSTOMER MENU***");
            System.out.println("1. Show Account Info");
            System.out.println("2. Make Transaction");
            System.out.println("3.Logout");
            Customer.cases_C();
            if (call_c == 1) {// Logout case
                call_c = 0;
                break;
            }
        }
        System.out.println("Auto Logging Off...");
    }

    public static void cases_C() {
        int choice2 = sc4.nextInt();
        switch (choice2) {
            case 1:
                Manager.print1(temp);// Printing the user info by calling print1() in Manager class
                break;
            case 2:
                Node retain_original = temp;// variable taken to retain the original Node value so as to use it for
                                            // transferring amounts and updating balances for both
                while (true) {
                    System.out.println("Enter Account Number to Transfer");
                    long cust_accno = sc4.nextLong();// Reciever's Account Number
                    if (findAcc(cust_accno)) {// Checks if the account is available in the Bank or not
                        System.out.println("Enter amount to transfer");
                        double amt = sc4.nextDouble();
                        if (amt <= temp.balance) {// Checks if amount doesn't exceed available funds for the user
                            retain_original.balance -= amt;// Deducts the transferred amount from current logged in user
                            temp.balance += amt;// Adds the transferred amount to the reciever's balance
                            System.out.println("DataSheets");// Prints the Transaction summary
                            Manager.print1(retain_original);
                            Manager.print1(temp);
                        } else {
                            System.out.println("Not Enough Money");
                        }
                        temp = retain_original;// overwrites the temp variable with the loggedIn user Node
                        break;
                    } else {
                        System.out.println("Reciver Account Number is Invalid! ReEnter Again!");
                        continue;
                    }
                }
                break;
            case 3:
                call_c = 1;
                break;
            default:
                System.out.println("Wrong Choice!");
        }
    }

    public static boolean findAcc(long idd) {// Iterates over the Linked-List to find if the account is present or not
        temp = null;
        Node iterate = Manager.head;// Accesses the 'head' value from Manager class
        while (iterate != null)
            if (iterate.accno == idd) {
                temp = iterate;
                break;
            } else {
                iterate = iterate.next;
            }
        if (temp == null)
            return false;
        else {
            return true;
        }
    }

    public static boolean matchPass(String hpin) {// This function verifies the password by generating taking the
                                                  // mpin:'hashvalue' given as input & matches it with the
                                                  // mpin:'hashvalue' for the useraccount stored in the LinkedList
        if (temp.hash_pin.equals(hpin))
            return true;
        else
            return false;
    }

}
