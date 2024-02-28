/*
 * This java file checks if the Validatition attempts for MPIN exceeds "3 times" or not.
 */
public class mpin_attempts_checker {
    protected static boolean check_attempts(Node k) {
        if (k.number_of_mpin_attempts == 0) {
            return false;
        } else {
            return true;
        }

    }

    protected static void print_error_statement() {
        System.out.println(
                "Maximum MPIN validation attempts reached! Auto Requesting Manager to Re-Login");
        System.out.println("Auto Logging off for security!!");
        Login.whoami();
    }

}
