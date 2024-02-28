import java.util.*;

/*
 * This is the Customers' Portal
 */
public class Customer {
    protected static Node temp;
    private static Scanner sc4 = new Scanner(System.in);
    private static int call_c = 0;
    private static Scanner stringsc4 = new Scanner(System.in);// String is entered through different scanner object to
                                                              // avoid
    // mismatch
    private static String tr = "";
    private static String transaction_fetch = "";

    public static void menu2() {// Customer Available options
        while (true) {
            System.out.println("***CUSTOMER MENU***");
            System.out.println("1. Show Account Info");
            System.out.println("2. Make Transaction");
            System.out.println("3. Deposit Amount");
            System.out.println("4. Withdraw Amount");
            System.out.println("5. Transaction History");
            System.out.println("6. Support");
            System.out.println("7. Logout");
            Customer.cases_C();
            if (call_c == 1) {// Logout case
                call_c = 0;
                break;
            }
        }
        System.out.println("Auto Logging Off...");
    }

    private static void cases_C() {
        int choice2 = sc4.nextInt();
        switch (choice2) {
            case 1:
                Manager.print1(temp);// Printing the user info by calling print1() in Manager class
                break;
            case 2:
                Customer.do_payment();
                break;
            case 3:
                Customer.deposit(temp);// calls the deposit function
                break;
            case 4:
                Customer.withdraw(temp);// calls the withdraw function
                break;
            case 5:
                Transaction_History.queueprint(temp);
                break;
            case 6:
                System.out.println("Launching Support Page!");
                Customer.support_page(temp);
                break;
            case 7:
                call_c = 1;
                break;
            default:
                System.out.println("Wrong Choice!");
        }
    }

    protected static boolean findAcc(long idd) {// Iterates over the Linked-List to find if the account is present or
                                                // not
        temp = null;
        Node iterate = Manager.head;// Accesses the 'head' value from Manager class
        while (iterate != null)
            if (iterate.accno == idd) {
                temp = iterate;
                // // Debug
                // System.out.println(temp);
                // System.out.println(temp.mail_id);
                // System.out.println(temp.hash_pin);
                // //
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

    // This method is used for Mail-ID Login Auth
    protected static boolean find_mail_acc(String mail_id_of_user) {
        temp = null;
        Node iterate = Manager.head;// Accesses the 'head' value from Manager class
        while (iterate != null)
            if (iterate.mail_id.equals(mail_id_of_user)) {
                temp = iterate;
                // // Debug
                // System.out.println(temp);
                // System.out.println(temp.mail_id);
                // System.out.println(temp.hash_pin);
                // //
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

    protected static boolean matchPass(String hpin) {// This function verifies the password by generating taking the
                                                     // mpin:'hashvalue' given as input & matches it with the
                                                     // mpin:'hashvalue' for the useraccount stored in the LinkedList
        if (temp.hash_pin.equals(hpin))
            return true;
        else
            return false;
    }

    protected static void do_payment() {
        Node retain_original = temp;// variable taken to retain the original Node value so as to use it for
                                    // transferring amounts and updating balances for both
        while (true) {
            System.out.println("Enter Account Number to Transfer");
            long cust_accno = sc4.nextLong();// Reciever's Account Number
            if (findAcc(cust_accno)) {// Checks if the account is available in the Bank or not
                System.out.println("Enter amount to transfer");
                double amt = sc4.nextDouble();
                if (amt <= retain_original.balance) {// Checks if amount doesn't exceed available funds for the
                                                     // current user
                    // Authorisation
                    if (mpin_attempts_checker.check_attempts(retain_original)) {
                        while (true) {
                            System.out.println("Enter MPIN");
                            Secrets.encrypt(stringsc4.nextLine());
                            if (Secrets.hashedpin.equals(retain_original.hash_pin)
                                    && mpin_attempts_checker.check_attempts(retain_original)) {
                                System.out.println("You are authorised to make payments.");
                                retain_original.number_of_mpin_attempts = 3;
                                break;
                            } else if (mpin_attempts_checker.check_attempts(retain_original)) {
                                System.out.println("Wrong MPIN. Please ReEnter!");
                                System.out
                                        .println("Number of Attempts Left:"
                                                + (--retain_original.number_of_mpin_attempts));
                                if (Customer.temp.number_of_mpin_attempts == 0) {
                                    mpin_attempts_checker.print_error_statement();
                                    break;
                                }
                            } else {
                                mpin_attempts_checker.print_error_statement();
                            }
                        }
                    } else {
                        mpin_attempts_checker.print_error_statement();
                    }
                    // Auth Success!
                    retain_original.balance -= amt;// Deducts the transferred amount from current logged in user
                    temp.balance += amt;// Adds the transferred amount to the reciever's balance
                    System.out.println("Transaction Summary");// Prints the Transaction summary
                    tr = String.valueOf(retain_original.accno);// converts the account number of 'sender' to
                                                               // String
                    tr = tr.substring(0, 3) + "***";// Shows only the first 3 digits of the account no.
                    System.out.print("Successfully transferred " + amt + " from " + retain_original.username
                            + "(Acc No:" + tr + ")");
                    transaction_fetch = "Successfully transferred" + String.valueOf(amt) + " from "
                            + retain_original.username
                            + "(Acc No:" + tr + ")";// to be used to store history
                    tr = "";// makes tr null
                    tr = String.valueOf(temp.accno);// converts the account number of 'reciever' to String
                    tr = tr.substring(0, 3) + "***";// Shows only the first 3 digits of the account no.
                    System.out.println(" To " + temp.username + "(Acc No:" + tr + ")");
                    transaction_fetch += "To " + temp.username + "(Acc No:" + tr + ")";// to be used to store
                                                                                       // history
                    Transaction_History.hist(retain_original, transaction_fetch);// adds history to reciver's account
                    Transaction_History.hist(temp, transaction_fetch);// adds history to reciver's account
                    transaction_fetch = "";

                    tr = "";// makes tr null
                    // Manager.print1(retain_original);
                    // Manager.print1(temp);// Prints the transaction summary of the receiver.
                    // (Debug purposes
                    // only). Comment out for security purposes.
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
    }

    private static void deposit(Node k) {// case 3
        System.out.println("How much amount you want to deposit?");
        double dpamount = sc4.nextDouble();// stores deposit amount
        k.balance += dpamount;// adds the deposited amount to main balance
        transaction_fetch = "Deposited Amount Rs." + dpamount + "/- in Ac/No:" + String.valueOf(k.accno).substring(0, 3)
                + "*** by " + k.username;
        Transaction_History.hist(k, transaction_fetch);// Adds history for deposit
        transaction_fetch = "";
        while (true) {// if want to see details
            System.out.println("Do you want to see your details?Y/N");
            char ch3 = stringsc4.nextLine().charAt(0);
            if (ch3 == 'Y' || ch3 == 'y') {
                Manager.print1(k);
                break;
            } else if (ch3 == 'N' || ch3 == 'n')
                break;
            else {
                System.out.println("Enter right choice!");
                continue;
            }
        }
    }

    private static void withdraw(Node k) {// case 4

        while (true) {// asks for mpin before any withdrawal
            System.out.println("Enter MPIN");
            String w_pin = stringsc4.nextLine();
            Secrets.encrypt(w_pin);
            if (k.hash_pin.equals(Secrets.hashedpin) && mpin_attempts_checker.check_attempts(k)) {// checks if mpin is
                                                                                                  // correct or not
                k.number_of_mpin_attempts = 3;
                break;
            } else if (mpin_attempts_checker.check_attempts(k)) {
                System.out.println("Wrong MPIN. Please ReEnter!");
                System.out.println("Number of Attempts Left:" + (--k.number_of_mpin_attempts));
                if (k.number_of_mpin_attempts == 0) {
                    mpin_attempts_checker.print_error_statement();
                    break;
                } else
                    continue;
            } else {
                mpin_attempts_checker.print_error_statement();
            }
        }
        while (true) {
            System.out.println("How much amount you want to withdraw?");
            double withdraw_amt = sc4.nextDouble();
            if (withdraw_amt <= k.balance) {// checks if withdrawn amount exceeds main balance
                System.out.println("Withdrawn Amount:" + withdraw_amt);
                k.balance -= withdraw_amt;
                transaction_fetch = "Withdrawn Amount Rs." + withdraw_amt + "/- from Ac/No:"
                        + String.valueOf(k.accno).substring(0, 3)
                        + "*** by " + k.username;
                Transaction_History.hist(k, transaction_fetch);// adds history for withdrawn amount
                transaction_fetch = "";
                System.out.println("Available balance for user " + k.username + " is:" + k.balance);
                break;
            } else {
                System.out.println(
                        "Not Enough Money in your Account! Please ReEnter. To Exit Enter Withdrawal Amount as 0");
                continue;
            }
        }
    }

    private static void support_page(Node k) {// Customer Support
        int loopbreak = 0;// breaks the outer loop
        System.out.println("Welcome to Customer Support!");
        while (true) {
            System.out.println("***Support menu***");
            System.out.println("1. Change User MPIN");
            System.out.println("2. Change UserName");
            System.out.println("3. View Support Status");
            System.out.println("4. Exit Support Page");
            int support_ch = sc4.nextInt();
            switch (support_ch) {
                case 1:
                    while (true) {
                        System.out.println("Enter Current MPIN OR type Exit to EXIT!");
                        String oldpass = sc4.nextLine();
                        if (oldpass.equalsIgnoreCase("Exit"))
                            break;
                        Secrets.encrypt(oldpass);
                        if (Secrets.hashedpin.equals(temp.hash_pin)) {
                            System.out
                                    .println("User Authentication Complete. You are now authorised to change password");
                            Secrets.generate_mpin();
                            temp.hash_pin = Secrets.hashedpin;// stores new MPIN
                            System.out.println("MPIN Updated!");
                            break;
                        } else {
                            System.out.println("Enter Correct current MPIN");
                            continue;
                        }
                    }
                    break;
                case 2:
                    System.out.println("Request to change UserName");
                    System.out.println("Enter new username");
                    temp.query = stringsc4.nextLine();
                    System.out.println("Upload Aadhar ID");
                    temp.upload_file = stringsc4.nextLine();
                    temp.request = 1;
                    break;
                case 3:
                    System.out.println("SUPPORT REQUEST STATUS PAGE!");
                    if (temp.request == 2) {
                        System.out.println("Request Accepted!");
                        System.out.println("Your Username is changed to:" + temp.username);
                    } else if (temp.request == 0) {
                        System.out.println("Request Denied");
                        System.out.println(temp.query);// Prints any message by bank Manager
                    } else {
                        System.out.println("Request status Pending!");
                    }
                    break;
                case 4:
                    System.out.println("Exiting Support Page!");
                    loopbreak = 1;
                    break;
            }
            if (loopbreak == 1)
                break;
        }

    }

}
