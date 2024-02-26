public class Test {
    static int pointer = 0;

    public static void test_accounts() {
        String usernames[] = new String[] { "Akash", "Sarwan", "Sujeet" };
        String unencrypt_pin[] = new String[] { "024680", "135790", "987654" };
        String en_pin[] = new String[3];// hashed pin is stored here
        long accounts_number[] = new long[3];
        for (int i = 0; i < en_pin.length; i++) {
            Secrets.encrypt(unencrypt_pin[i]);
            en_pin[i] = Secrets.hashedpin;
            RandomIdGenerator.RandomID();
            accounts_number[i] = RandomIdGenerator.rid;
        }
        Manager.n.username = usernames[pointer];
        Manager.n.accno = accounts_number[pointer];
        Manager.n.hash_pin = en_pin[pointer];
        pointer++;

    }

    protected static void present_in_LL() {// Normal LinkedList for customer account creation
        for (int j = 0; j < 3; j++) {
            if (Manager.head == null) {
                Manager.n = new Node();
                Manager.n.next = null;
                Manager.head = Manager.n;
                Test.test_accounts();
            } else {
                Node newnode = new Node();
                newnode.next = null;
                Manager.n.next = newnode;
                Manager.n = newnode;
                Test.test_accounts();
            }
        }
    }

}
