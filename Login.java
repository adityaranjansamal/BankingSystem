import java.util.*;

/*
 * Author: Aditya Ranjan Samal
 * Github ID: adityarnsamal
 * Telegram ID: adityarnsamal
 * Compiled On: Fedora Linux 39 Workstation Edition x64_86
 * Java(c) Version: 11
 * Font Used: JetBrains Mono
 */
/*
 * This is the Login Page For the Banking System.
 * This is the Central Control Unit.
 */
public class Login {
    static Scanner sc2 = new Scanner(System.in);
    static Scanner stringsc2 = new Scanner(System.in);// String is entered through different scanner object to avoid
                                                      // mismatch
    static boolean man_auth = false;
    static boolean cust_auth = false;

    public static void whoami() {// This specifies how the login should occur
        while (true) {// Infinite Loop Until Case 3 exit occurs
            System.out.println("***Access As***");
            System.out.println("1.Manager");
            System.out.println("2.Customer");
            System.out.println("3.Exit");
            System.out.println();
            int ch = sc2.nextInt();
            switch (ch) {
                case 1:
                    // Opening Portal
                    System.out.println("Welcome to Manager's Portal");
                    while (true)// while 0
                    {
                        System.out.println("1. Create Manager");
                        System.out.println("2. Login as Manager");
                        System.out.println("3. Return to Main Menu");
                        int man_ch = sc2.nextInt();
                        switch (man_ch) {
                            case 1:
                                Manager.man_data();
                                break;
                            case 2:
                                if (Manager.man_uid != 0L)
                                    man_auth = true;
                                break;
                            case 3:
                                Login.whoami();
                                break;
                            default:
                                System.out.println("Wrong Choice! Plaese ReEnter");
                                break;
                        }
                        if (man_auth && man_ch == 2)
                            break;// while 0 terminated!
                        else {
                            continue;
                        }
                    }
                    // Portal End
                    // Manager Login Authentication
                    System.out.println();
                    System.out.println("Manager Portal Login");// Logging in as bank Manager
                    while (true) {// while 1
                        System.out.println();
                        System.out.println("Enter UID, Enter -9999 to Exit to Main Menu");
                        long uid = sc2.nextLong();
                        if (uid == -9999) {
                            System.out.println("Returning to main menu");
                            Login.whoami();
                            return;
                        } else if (uid == Manager.man_uid) {// checks if uid matches with id stored in database
                            while (true) {// while 2
                                System.out.println("Enter MPIN");
                                String mpin = stringsc2.nextLine();
                                Secrets.encrypt(mpin);// generates hash value
                                if (Secrets.hashedpin.equals(Manager.man_mpin)) {// checks if the entered mpin hash
                                                                                 // value matches with the hash stored
                                                                                 // in the databse or not
                                    System.out.println("Manager Login Success!");
                                    System.out.println("Welcome Manager " + Manager.man_name + "!");
                                    Manager.menu();
                                    break;// while 2 loop break
                                } else {
                                    System.out.println("Wrong PIN. Please ReEnter");
                                    continue;
                                }

                            }
                            break; // while 1 break

                        } else {
                            System.out.println("Invalid UID");
                            continue;
                        }

                    }
                    break;// case 1 break

                case 2:
                    // Opening Portal
                    System.out.println("Welcome to Customer's Portal");
                    while (true)// while customer 0
                    {
                        System.out.println("1. Create customer account");
                        System.out.println("2. Login as Customer");
                        System.out.println("3. Return to Main Menu");
                        int custom_ch = sc2.nextInt();
                        switch (custom_ch) {
                            case 1:
                                Manager.createuser();
                                break;
                            case 2:
                                if (Manager.head != null)
                                    cust_auth = true;
                                break;
                            case 3:
                                Login.whoami();
                                break;
                            default:
                                System.out.println("Wrong Choice! Please ReEnter");
                                break;
                        }
                        if (cust_auth && custom_ch == 2)
                            break;// while customer 0 terminated!
                        else if (custom_ch == 1)
                            continue;
                        else {
                            System.out.println("No Account present in bank! Please create a Customer account");
                            continue;
                        }
                    }
                    // Portal End
                    System.out.println("Customer Portal Login");// Logging in as Customer
                    while (true) {// while 1
                        System.out.println("Enter Account Number. To Return to Main Menu Enter -9999");
                        long uid2 = sc2.nextLong();
                        if (uid2 == -9999) {
                            System.out.println("Returning to Main Menu");
                            Login.whoami();
                            return;
                        } else if (Customer.findAcc(uid2)) {// checks if uid2 matches with id stored in database
                            while (true) {// while 2
                                System.out.println("Enter MPIN");
                                String mpin = stringsc2.nextLine();
                                Secrets.encrypt(mpin);// checks if the entered mpin hash
                                                      // value matches with the hash stored
                                                      // in the databse or not
                                if ((Customer.matchPass(Secrets.hashedpin))) {
                                    System.out.println("Customer Login Success!");
                                    System.out.println("Welcome " + Customer.temp.username + "!");
                                    Customer.menu2();
                                    break;// while 2 break
                                } else {
                                    System.out.println("Wrong PIN. Please ReEnter");
                                    continue;// while 2 continue
                                }

                            }
                            break;// while 1 break

                        } else {
                            System.out.println("Account Number Mismatch! ReEnter Again!");
                            continue;// while 1 continue
                        }

                    }
                    break;
                case 3:
                    System.out.println("Exiting!");// Force exiting Banking Portal
                    System.exit(0);
                default:
                    System.out.println("Invalid Choice!");
            }
        }
    }

    public static void main(String[] args) {
        // System.out.println("====WELCOME TO THALA BANK====");
        faceprint.logo();// Prints the Bank Name
        // System.out.println("First Run! Create Manager First to invoke any
        // functions");
        // Manager.man_data();// calling this function at least once beacuse manager is
        // needed to create
        // customer accounts
        Login.whoami();
    }
}
