import java.util.Random;

public class RandomIdGenerator {// Generates a unique Pseudo Random ID that is not present in the Linked List
    private static Random random = new Random();
    protected static long rid;

    protected static long RandomID() {
        while (true) {
            rid = random.nextInt(9_00_000) + 1_00_000;
            if (Customer.findAcc(rid))
                continue;
            else
                return rid;
        }
    }

}
